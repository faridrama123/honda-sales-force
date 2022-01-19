package com.langit7.hondasalesforce.view.activity

import android.app.Activity
import android.os.Bundle
import androidx.gridlayout.widget.GridLayout
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.google.gson.Gson
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.DialogUtil
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.model.user
import com.langit7.hondasalesforce.presenter.logic.MainPresenter
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectResponseInterface
import kotlinx.android.synthetic.main.action_bar.*
import kotlinx.android.synthetic.main.activity_change_avatar.*



class ChangeAvatarActivity : BaseActivity() {


    var selectedimage=1

    lateinit var presenter:MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_avatar)


        presenter= MainPresenter(this,APIServices)

        var u=getUser()

        tactionbartitle.setText("Ubah Avatar")
        imgback.setOnClickListener {
            onBackPressed()
        }




        if (u!=null) {

            selectedimage=u.profileUser.chosen_medal_type.toInt()
            renderavatar(u)
        }


        bsubmit.setOnClickListener {
            showLoadingDialog()
            presenter.updateAvatar(selectedimage.toString(),object : ObjectResponseInterface<user>{
                override fun onSuccess(res: user) {
                    dismisLoadingDialog()
                    function.savePreverence(ctx, "user", Gson().toJson(res))
                    finish()

                }

                override fun onFailed(msg: String) {
                    dismisLoadingDialog()
                    Toast(msg)
                }

            })

        }


    }


    fun renderavatar(u: user){


        gridavatar.columnCount=2
        gridavatar.removeAllViews()
        var m=u.profileUser.medal_type.toInt()
//        Log.e("m",m.toString())
        for (i in 1 .. 6) {
            var v = layoutInflater.inflate(R.layout.item_avatar, null)

            var img= v.findViewById<ImageView>(R.id.imgavatar)
            var ll= v.findViewById<LinearLayout>(R.id.llframe)
            var lldisable= v.findViewById<RelativeLayout>(R.id.lldisable)
            var tlevel= v.findViewById<TextView>(R.id.tlevel)
            var tscore= v.findViewById<TextView>(R.id.tscore)
            var imglock= v.findViewById<ImageView>(R.id.imglock)
            img.setImageResource(function.getMedalImageLarge(i.toString()))

            tscore.setText(function.getMedalPoint(i.toString()))
            tlevel.setText(function.getMedalName(i.toString()))

            if(i==selectedimage) {
                lldisable.visibility=View.GONE
                ll.setBackgroundResource(R.drawable.circle_white)
                imglock.setImageResource(R.drawable.icon_unlock)
                tscore.setTextColor(resources.getColor(R.color.black))
                tlevel.setTextColor(resources.getColor(R.color.black))
            }else if(i>m) {
                lldisable.visibility=View.VISIBLE
                ll.setBackgroundResource(R.drawable.circle_transparant)
                imglock.setImageResource(R.drawable.icon_locked)
                tscore.setTextColor(resources.getColor(R.color.gray))
                tlevel.setTextColor(resources.getColor(R.color.gray))

            }else {
                lldisable.visibility=View.GONE
                ll.setBackgroundResource(R.drawable.circle_semitransparant)
                imglock.setImageResource(R.drawable.icon_unlock)
                tscore.setTextColor(resources.getColor(R.color.black))
                tlevel.setTextColor(resources.getColor(R.color.black))
            }

            val lp = GridLayout.LayoutParams()
            lp.columnSpec = GridLayout.spec(
                GridLayout.UNDEFINED, 1f)
            v.setLayoutParams(lp)


            if(i<=m)
            v.setOnClickListener {
                selectedimage=i
                renderavatar(u)
            }
            else
            v.setOnClickListener {
                DialogUtil.createYesDialog(ctx as Activity,"Anda harus mencapai "+function.getMedalPoint(i.toString())+" untuk memilih avatar ini","OK")
            }

            gridavatar.addView(v)
//            Log.e("e", i.toString())
        }
    }
}
