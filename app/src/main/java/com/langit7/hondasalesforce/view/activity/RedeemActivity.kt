package com.langit7.hondasalesforce.view.activity

import android.app.Activity
import android.os.Bundle
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.DialogUtil
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.model.baseresponse
import com.langit7.hondasalesforce.model.promo
import com.langit7.hondasalesforce.model.redeem
import com.langit7.hondasalesforce.model.user
import com.langit7.hondasalesforce.presenter.logic.MainPresenter
import com.langit7.hondasalesforce.presenter.logic.PromoPresenter
import com.langit7.hondasalesforce.presenter.viewInterface.DataListInterface
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectListener
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectResponseInterface
import com.langit7.hondasalesforce.presenter.viewInterface.YesNoListener
import kotlinx.android.synthetic.main.action_bar.*
import kotlinx.android.synthetic.main.activity_redeem.*

class RedeemActivity : BaseActivity() {

    lateinit var lsPromo:ArrayList<promo>
    lateinit var presenter:PromoPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_redeem)

        lsPromo= ArrayList()
        presenter= PromoPresenter(this, APIServices)


        tactionbartitle.setText(getString(R.string.productknowledge))
        imgback.setOnClickListener({
            onBackPressed()
        })


        showLoadingDialog()
        presenter.getPromo(object : DataListInterface<promo>{
            override fun onGetDataSuccess(res: List<promo>) {
                dismisLoadingDialog()
                lsPromo.clear()
                lsPromo.addAll(res)
                renderPromo()
            }

            override fun onGetDataFailed(msg: String) {
                dismisLoadingDialog()
            }

        })
    }

    fun renderPromo(){
        llpromocontainer.removeAllViews()

        for(p in lsPromo){
            var v =layoutInflater.inflate(R.layout.item_promo,null)
            var ttitle=v.findViewById<TextView>(R.id.ttitle)
            var tdesc=v.findViewById<TextView>(R.id.tdesc)
            var tprice=v.findViewById<TextView>(R.id.tprice)
            var tredeem=v.findViewById<TextView>(R.id.tredeem)
            var img=v.findViewById<ImageView>(R.id.img)


            ttitle.setText(p.title)
            tdesc.setText(Html.fromHtml(p.body))
            tprice.setText(p.pointNeeded+" "+ getString(R.string.point))

            if(p.image.length>0)
                Glide.with(ctx).load(p.image).into(img)

            tredeem.setOnClickListener {
                ///show redeem dialog
//                Toast("redeem " + p.title)

                var usr= function.getUser(ctx)
                if(p.pointNeeded.toInt() > usr!!.profileUser.totalPoint.toInt())
                    DialogUtil.createYesDialog(ctx as Activity,"Poin anda tidak mencukupi!","Tutup")
                else
                    DialogUtil.createRedeemYesNoDialog(ctx as Activity,"Anda akan meredeem " +p.pointNeeded +" poin untuk "+ p.title +" lanjutkan?",p.image, object:YesNoListener{

                    override fun onYes() {
                        DialogUtil.createRedeemDialog(ctx as Activity,object :ObjectListener<String>{
                            override fun onOK(obj: String) {
                                showLoadingDialog()
                                presenter.redeemPromo(p.id,obj,object: ObjectResponseInterface<redeem>{
                                    override fun onSuccess(res: redeem) {
                                        dismisLoadingDialog()
                                        DialogUtil.createRedeemResultDialog(ctx as Activity,"Anda telah berhasil melakukan redeem " +res.promoid.pointNeeded +" poin untuk "+ res.promoid.title,res.promoid.image)

                                        var pres=MainPresenter(ctx as BaseActivity,APIServices)
                                        pres.getuser(object : ObjectResponseInterface<baseresponse<user>>{
                                            override fun onSuccess(res: baseresponse<user>) {
                                                function.savePreverence(ctx, "user", Gson().toJson(res.data))
                                            }

                                            override fun onFailed(msg: String) {

                                            }

                                        })

                                    }

                                    override fun onFailed(msg: String) {
                                        dismisLoadingDialog()
                                        Toast(msg)
                                    }

                                })
                            }

                        })
                    }

                    override fun onNo() {
                    }


                })
            }

            llpromocontainer.addView(v)
        }

    }
}
