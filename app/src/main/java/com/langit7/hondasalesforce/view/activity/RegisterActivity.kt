package com.langit7.hondasalesforce.view.activity

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.ArrayAdapter
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.model.position
import com.langit7.hondasalesforce.model.user
import com.langit7.hondasalesforce.presenter.logic.RegisterPresenter
import com.langit7.hondasalesforce.presenter.viewInterface.DataListInterface
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectResponseInterface
import com.langit7.hondasalesforce.presenter.viewInterface.RegisterInterface
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity(), RegisterInterface {
    override fun onRegisterSuccess(res: user) {
        dismisLoadingDialog()
        var ii = Intent(ctx, RegisterResultActivity::class.java)
        startActivity(ii)
        finish()
    }

    override fun onRegisterFailed(msg: String) {
        dismisLoadingDialog()
        Toast(msg)
    }

    var lsJabatan = ArrayList<String>()

    var passvis = false
    var passvis1 = false

    var usr:user? = null

    var emailvalid=false
    var hidvalid=false

    lateinit var adp:ArrayAdapter<String>

    lateinit var presenter: RegisterPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        lsJabatan.add("Pilih Jabatan")
//        lsJabatan.add("Kacab")
//        lsJabatan.add("Supervisor")
//        lsJabatan.add("Koordinator")
//        lsJabatan.add("Big Wing SP")
//        lsJabatan.add("Wing SP")
//        lsJabatan.add("SWAT")
//        lsJabatan.add("Salesman")
//        lsJabatan.add("Sales Counter")


        presenter = RegisterPresenter(this, APIServices)

        showLoadingDialog()
        presenter.position(object : DataListInterface<position> {
            override fun onGetDataSuccess(res: List<position>) {

                dismisLoadingDialog()
                lsJabatan.clear()
                lsJabatan.add("Pilih Jabatan")
                for (q in res) {
                    lsJabatan.add(q.title)
                }
                adp.notifyDataSetChanged()
            }

            override fun onGetDataFailed(msg: String) {
                dismisLoadingDialog()
            }
        })

        adp = ArrayAdapter<String>(ctx, R.layout.item_spinner, lsJabatan)
        spjabatan.adapter = adp

        bsubmit.setOnClickListener {
            if(hidvalid&& emailvalid) {
                showLoadingDialog()
                presenter.Register(
                    etname.text.toString(),
                    etemail.text.toString(),
                    etpassword.text.toString(),
                    etconfpassword.text.toString(),
                    ethid.text.toString(),
                    etphone.text.toString(),
//                    lsJabatan.get(spjabatan.selectedItemPosition),
                    usr!!.profileUser.position,
                    this
                )
            }else{
                Toast("Lengkapi Kolom Isian")
            }
        }

        etpassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        imgeye.setOnClickListener {
            if (passvis) {
                etpassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                imgeye.setImageResource(R.drawable.icon_visible)
                passvis = false
            } else {
                etpassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL
                imgeye.setImageResource(R.drawable.icon_invisible)
                passvis = true
            }
        }
        etconfpassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        imgeye1.setOnClickListener {
            if (passvis1) {
                etconfpassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                imgeye1.setImageResource(R.drawable.icon_visible)
                passvis1 = false
            } else {
                etconfpassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL
                imgeye1.setImageResource(R.drawable.icon_invisible)
                passvis1 = true
            }
        }



        pbhdid.visibility= View.GONE
        ethid.setOnFocusChangeListener { view, focus ->
            var hid=ethid.text.toString()
            if(!focus &&hid.length>0){
                pbhdid.visibility= View.VISIBLE
                presenter.getHID(hid, object : ObjectResponseInterface<user>{
                    override fun onSuccess(res: user) {
                        pbhdid.visibility= View.GONE
                        usr=res
                        validatehdid()
                    }

                    override fun onFailed(msg: String) {
                        pbhdid.visibility= View.GONE
                        usr=null
                        tmsghdid.visibility=View.VISIBLE
                        tmsghdid.setTextColor(resources.getColor(R.color.red))
                        tmsghdid.setText("Gagal Validasi Honda ID, Silahkan cek koneksi internet")

                    }

                })
            }


        }

        etemail.setOnFocusChangeListener { view, focus ->

            if(!focus && etemail.text.toString().length>0){
                validateemail()
            }


        }

        tmsghdid.visibility=View.INVISIBLE
        tmsgemail.visibility=View.INVISIBLE
    }


    fun validatehdid(){
        hidvalid=false
        tjabatan.setText("Jabatan: -")
        if(usr!=null && usr!!.message.isNullOrEmpty()){
//            if(usr!!.profileUser.status_registration.equals("1")){
//                tmsghdid.visibility=View.VISIBLE
//                tmsghdid.setTextColor(resources.getColor(R.color.red))
//                tmsghdid.setText(getString(R.string.hidalreadyregistered))
//            }else{
                tmsghdid.visibility=View.VISIBLE
                tmsghdid.setTextColor(resources.getColor(R.color.green))
                tmsghdid.setText(getString(R.string.hidvalid))
                hidvalid=true
                tjabatan.setText("Jabatan: "+usr!!.profileUser.position)
//            }
        }else{
            tmsghdid.visibility=View.VISIBLE
            tmsghdid.setTextColor(resources.getColor(R.color.red))
            tmsghdid.setText(getString(R.string.hidinvalid))
        }
    }

    fun validateemail(){
        emailvalid=false
        if(usr!=null && etemail.text.toString().equals(usr!!.email,true)){
//            if(usr!!.profileUser.status_registration.equals("1")) {
//                tmsgemail.visibility = View.VISIBLE
//                tmsgemail.setTextColor(resources.getColor(R.color.red))
//                tmsgemail.setText(getString(R.string.emailalreadyregistered))
//            }else{
                tmsgemail.visibility = View.VISIBLE
                tmsgemail.setTextColor(resources.getColor(R.color.green))
                tmsgemail.setText(getString(R.string.emailvalid))
                emailvalid=true
//            }


        }else{
            tmsgemail.visibility=View.VISIBLE
            tmsgemail.setTextColor(resources.getColor(R.color.red))
            tmsgemail.setText(getString(R.string.emailinvalid))
        }
    }
}

