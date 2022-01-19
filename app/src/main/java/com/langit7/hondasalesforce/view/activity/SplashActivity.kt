package com.langit7.hondasalesforce.view.activity

import android.annotation.TargetApi
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.core.app.ActivityCompat
import android.view.WindowManager
import com.google.gson.Gson
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.DialogUtil
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.Util.function.savePreverence
import com.langit7.hondasalesforce.model.version
import com.langit7.hondasalesforce.presenter.logic.MainPresenter
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectResponseInterface
import java.util.*
import androidx.annotation.RequiresApi


class SplashActivity : BaseActivity() {



    var i = 0
    var cangohome=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash)

//        Log.e("user", isLogin(this).toString())
        val msg=intent.getStringExtra("msg")

        if(msg!=null && msg.length>0)
            Toast(msg)

        savePreverence(ctx,"hasaddialog",false)

        ctx=this




        checkUpdate()

        if (
            ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED

        ) {


            if (Build.VERSION.SDK_INT >= 23) {
                askPermission()
            }

            //Toast.makeText(this,"You Have TO Accept GPS Permission For This APP",Toast.LENGTH_LONG).show();
            return
        } else {
            Handler().postDelayed({
                canwegohome()
            },3000)
        }


    }

    fun canwegohome(){

        if(cangohome){
            goHome()
        }else if(actvisible) {
//            Log.e("Splash","We Can't Go Home")
            Handler().postDelayed({
                canwegohome()
            }, 1000)
        }
    }

    internal fun checkUpdate() {

//        Log.e("api","Checking update")
        var pp= MainPresenter(ctx as BaseActivity,APIServices)

        pp.getVersion(object : ObjectResponseInterface<version>{
            override fun onSuccess(res: version) {
                try {
                    savePreverence(ctx,"settings", Gson().toJson(res))
                    val pInfo = ctx.packageManager.getPackageInfo(packageName, 0)
                    val version = pInfo.versionCode

                    if (res!!.lastVersion!= null) {
                        val serverversion = Integer.parseInt(res!!.lastVersion)

                        if (serverversion > version) {
                            DialogUtil.createUpdateDialog(ctx as BaseActivity).setOnDismissListener(DialogInterface.OnDismissListener {
                                try {
                                    val url = res.updateUrl + ""
                                    val i = Intent(Intent.ACTION_VIEW)
                                    i.data = Uri.parse(url)
                                    startActivity(i)
                                    finish()
                                } catch (e: Exception) {
                                }
                            })
                        } else {
                            cangohome=true
                        }

                    }
                } catch (e: Exception) {
                    Toast("Checking Update Failed, Please try again later")
                    finish()
                }
            }

            override fun onFailed(msg: String) {
                Toast("Checking Update Failed, Please try again later")
                finish()
            }

        })







    }




    fun goHome(){

        var prevver=function.getPreverence(ctx,"prevver")
        var version = 0
        var pinfo=ctx.packageManager.getPackageInfo(packageName, 0)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            version =   pinfo.getLongVersionCode().toInt() // avoid huge version numbers and you will be ok
        } else {
            version = pinfo.versionCode
        }

        if(prevver=="" || version!=prevver.toInt()){
            savePreverence(ctx, "user", "")
            savePreverence(ctx, "token", "")
        }
        savePreverence(ctx,"prevver",version.toString())


        if(function.isLogin(ctx)) {

            startActivity(Intent(ctx, MainActivity::class.java))
        }else {
            startActivity(Intent(ctx, LoginActivity::class.java))
        }
        finish()
    }

    override fun onResume() {
        super.onResume()
//        mTracker.setScreenName("Splash_Screen")
//        mTracker.send(HitBuilders.ScreenViewBuilder().build())
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)


        if (requestCode == REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS) {

//            for(a in grantResults){
//                Log.e("PERMISSION", a.toString());
//            }
            if (grantResults.size>0 && grantResults.get(0) == 0) {
                android.widget.Toast.makeText(ctx, "Permission Granted", android.widget.Toast.LENGTH_SHORT).show()
                Handler().postDelayed({
                    canwegohome()
                },1000)
            } else {
                finish()
            }
        }
    }


    private val REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 124

    @TargetApi(Build.VERSION_CODES.M)
    private fun askPermission() {
        val permissionsNeeded = ArrayList<String>()

        val permissionsList = ArrayList<String>()
        if (!addPermission(permissionsList, "android.permission.ACCESS_COARSE_LOCATION"))
            permissionsNeeded.add("Read Network GPS")

        if (!addPermission(permissionsList, "android.permission.ACCESS_FINE_LOCATION"))
            permissionsNeeded.add("Read Device GPS")

        if (!addPermission(permissionsList, "android.permission.WRITE_EXTERNAL_STORAGE"))
            permissionsNeeded.add("Read Image Media")

        if (!addPermission(permissionsList, "android.permission.CAMERA"))
            permissionsNeeded.add("Use Device Camera For Scan QR")
        if (permissionsList.size > 0) {
            //            if (permissionsNeeded.size() > 0) {
            //                // Need Rationale
            //                String message = "You need to grant access to " + permissionsNeeded.get(0);
            //                for (int i = 1; i < permissionsNeeded.size(); i++)
            //                    message = message + ", " + permissionsNeeded.get(i);
            //                showMessageOKCancel(message,
            //                        new DialogInterface.OnClickListener() {
            //                            @Override
            //                            public void onClick(DialogInterface dialog, int which) {
            //                                requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
            //                                        REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
            //                            }
            //                        });
            //                return;
            //            }
//            Log.e("PERMISSION", "asking permission")
            requestPermissions(permissionsList.toTypedArray(),
                REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS)
            return
        }


        //insertDummyContact();
    }


    @RequiresApi(Build.VERSION_CODES.M)
    private fun addPermission(permissionsList: MutableList<String>, permission: String): Boolean {
        if (checkSelfPermission(permission) !== PackageManager.PERMISSION_GRANTED) {
            permissionsList.add(permission)
            // Check for Rationale Option
            if (!shouldShowRequestPermissionRationale(permission))
                return false
        }
        return true
    }


}
