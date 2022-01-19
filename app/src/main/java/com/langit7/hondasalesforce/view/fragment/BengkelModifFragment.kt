package com.langit7.hondasalesforce.view.fragment

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.model.bengkelmodif
import com.langit7.hondasalesforce.model.region
import com.langit7.hondasalesforce.presenter.viewInterface.DataListInterface
import com.langit7.hondasalesforce.view.activity.GeneralKnowledgeActivity


class BengkelModifFragment : Fragment(), BaseFragmentInterface {

    override var title: String = ""

    lateinit var act: GeneralKnowledgeActivity;
    lateinit var lsData: List<bengkelmodif>
    lateinit var lsData_: ArrayList<bengkelmodif>

    lateinit var llc: LinearLayout


    lateinit var spprovince: Spinner
    lateinit var spcity: Spinner
//    lateinit var spservice:Spinner
//    lateinit var sptype:Spinner

    var search = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_bengkel_modifikasi, container, false)

        act = activity as GeneralKnowledgeActivity

        llc = rootView.findViewById<LinearLayout>(R.id.llcontainer)
        val exlayout = rootView.findViewById<RelativeLayout>(R.id.exlayout)
//        val imgfilter=rootView.findViewById<ImageView>(R.id.imgfilter)
        val tsave = rootView.findViewById<TextView>(R.id.tsave)
        val tclear = rootView.findViewById<TextView>(R.id.tclear)
//        val etsearch=rootView.findViewById<EditText>(R.id.etsearch)
        spprovince = rootView.findViewById<Spinner>(R.id.spprovince)
        spcity = rootView.findViewById<Spinner>(R.id.spcity)
//        spservice=rootView.findViewById<Spinner>(R.id.spservice)
//        sptype=rootView.findViewById<Spinner>(R.id.sptype)
//        val tfilter = rootView.findViewById<TextView>(R.id.tfilter)
//        imgfilter.visibility = View.GONE
//        tfilter.visibility = View.GONE


        var adp1 = ArrayAdapter<String>(act, android.R.layout.simple_list_item_1, act.lssprovince)
        spprovince.adapter = adp1

        var adp2 = ArrayAdapter<String>(act, android.R.layout.simple_list_item_1, act.lsscity)
        spcity.adapter = adp2

//        var adp3=ArrayAdapter<String>(act,android.R.layout.simple_list_item_1,act.lssservce)
//        spservice.adapter=adp3
//
//        var adp4=ArrayAdapter<String>(act,android.R.layout.simple_list_item_1,act.lsstype)
//        sptype.adapter=adp4

//        imgfilter.setOnClickListener{
//            exlayout.toggle()
//        }


        spprovince.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p2 > 0) {
                    act.presenter.getCity(act.lsprovince.get(p2 - 1).id.toString(), object : DataListInterface<region> {
                        override fun onGetDataSuccess(res: List<region>) {
                            act.lscity.clear()
                            act.lscity.addAll(res)

                            act.lsscity.clear()
                            act.lsscity.add("Semua Kota")
                            for (c in act.lscity) {
                                act.lsscity.add(c.name)
                            }

                            adp2.notifyDataSetChanged()
                        }

                        override fun onGetDataFailed(msg: String) {
                            act.lsscity.clear()
                            act.lsscity.add("Semua Kota")
                            adp2.notifyDataSetChanged()
                        }

                    })
                } else {
                    act.lsscity.clear()
                    act.lsscity.add("Semua Kota")
                    adp2.notifyDataSetChanged()
                }
            }


        }

        tclear.setOnClickListener {
            spprovince.setSelection(0)
            spcity.setSelection(0)
//            spservice.setSelection(0)
//            sptype.setSelection(0)
        }

        tsave.setOnClickListener {
            filter()
        }

//        etsearch.addTextChangedListener(object: TextWatcher {
//            override fun afterTextChanged(p0: Editable?) {
//
//            }
//
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                search=p0.toString()
//                filter()
//            }
//
//        })

        filter()

        return rootView
    }

    private fun DialogBengkel(): Dialog {
        val vg: ViewGroup? = null
        val view = act.layoutInflater.inflate(R.layout.dialog_yes, vg)
        val dialog = Dialog(act)
        dialog.setCancelable(true)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

        val tm = view.findViewById(R.id.tdesc) as TextView
        val ty = view.findViewById(R.id.tyes) as TextView
//        val tn = view.findViewById(R.id.tno) as TextView

        tm.setText("Maaf belum ditemukan bengkel modifikasi diarea anda")
        ty.setText("OK")

        ty.setOnClickListener {
            dialog.dismiss()
        }

        dialog.setContentView(view)
        dialog.show()
        return dialog
    }

    fun filter() {

        lsData_.clear()


        for (d in lsData) {

            var include = true

            if (spprovince.selectedItemPosition > 0 && include) {
                var id = act.lsprovince.get(spprovince.selectedItemPosition - 1).id
                include = d.province.id.equals(id)
            }

            if (spcity.selectedItemPosition > 0 && include) {
                var id = act.lscity.get(spcity.selectedItemPosition - 1).id
                include = d.city.id.equals(id)
            }


//            if(sptype.selectedItemPosition>0&& include){
//                var id=act.lstype.get(sptype.selectedItemPosition-1).id
//                include = d.category.id.equals(id)
//            }
//
//            if(spservice.selectedItemPosition>0&& include){
//                var id=act.lsservce.get(spservice.selectedItemPosition-1).id
//                include = d.serviceType.id.equals(id)
//            }


            if (include) {
                include = d.title.toLowerCase().contains(search.toLowerCase()) ||
                        d.address.toLowerCase().contains(search.toLowerCase())
            }

            if (include)
                lsData_.add(d)

        }

        if (lsData_ != null && lsData_.size >0) {
            llc.removeAllViews()
            for (com in lsData_) {

                var v = layoutInflater.inflate(R.layout.item_bengkel_modifikasi, null)
                var ttitle = v.findViewById<TextView>(R.id.ttitle)
                var taddr = v.findViewById<TextView>(R.id.taddr)
                var tcity = v.findViewById<TextView>(R.id.tcity)
                var tprovince = v.findViewById<TextView>(R.id.tprovince)
                var tphone = v.findViewById<TextView>(R.id.tphone)

                ttitle.setText(com.title)
                taddr.setText(com.address)
                tcity.setText(com.city.name)
                tprovince.setText(com.province.name)

                var phone = ""
                var email = ""

                for (d in com.modificationWorkshopDetail) {
                    if (d.phone.length > 0)
                        if (phone.length > 0)
                            phone = phone + ", " + d.phone
                        else
                            phone = d.phone


                    if (d.email.length > 0)
                        if (email.length > 0)
                            email = email + ", " + d.email
                        else
                            email = d.email


                }

                tphone.setText(phone + "\n" + email)

                llc.addView(v)

            }
        } else {
            DialogBengkel()
        }

    }

    companion object {
        fun Instantiate(tit: String, lsartc: List<bengkelmodif>): BengkelModifFragment {
            val sd = BengkelModifFragment()
            sd.title = tit
            sd.lsData = lsartc
            sd.lsData_ = ArrayList()
            return sd
        }
    }

}

