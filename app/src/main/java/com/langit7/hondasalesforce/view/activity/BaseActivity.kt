package com.langit7.hondasalesforce.view.activity

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import android.util.Log
import android.view.WindowManager
import com.langit7.hondasalesforce.API.api
import com.langit7.hondasalesforce.HSFApplication
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.model.user
import com.langit7.hondasalesforce.model.version

open class BaseActivity : AppCompatActivity() {
    lateinit var ctx: Context;
//    lateinit var mTracker: Tracker
    var dialog: Dialog?=null;
    val APIServices by lazy {
        api.create(this)
    }


    var actvisible=true
    var que=0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);


        ctx=this


        val application = application as HSFApplication
//        mTracker = application.getDefaultTracker()

    }


    fun Toast(msg: String?) {
        android.widget.Toast.makeText(ctx,msg, android.widget.Toast.LENGTH_LONG).show()
    }



    fun showLoadingDialog(){
        if(dialog==null || !dialog!!.isShowing)
        dialog=ProgressDialog.show(ctx,"Loading","Please Wait..")
        else
            que++

    }

    fun dismisLoadingDialog(){
        if(que==0) {
            if (dialog != null && dialog!!.isShowing)
                dialog!!.dismiss()
        }else{
            que--
    }

//        Log.e("QUE",que.toString())
    }


    fun getUser(): user? {
        return function.getUser(ctx)
    }

    fun getSettings(): version {
        return function.getSettings(ctx)!!
    }


    fun getFCMID(): String {
        return function.getPreverence(ctx,"fcmid")
    }


    override fun onStop() {
        super.onStop()
        actvisible=false
    }

    override fun onPause() {
        super.onPause()
        actvisible=false
    }

    override fun onDestroy() {
        super.onDestroy()
        actvisible=false
    }
}
