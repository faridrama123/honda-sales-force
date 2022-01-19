package com.langit7.hondasalesforce

import android.app.Application
import android.content.res.Configuration
import android.graphics.Typeface
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import android.util.Log
import com.langit7.hondasalesforce.Util.function.savePreverence
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump
import io.realm.Realm
import io.realm.RealmConfiguration
import android.text.TextUtils
import com.google.firebase.messaging.FirebaseMessaging
import java.util.*


class HSFApplication : MultiDexApplication() {






    override fun onCreate() {
        super.onCreate()

        MultiDex.install(this);

        ViewPump.init(ViewPump.builder()
                .addInterceptor(CalligraphyInterceptor(
                        CalligraphyConfig.Builder()
                                .setDefaultFontPath("fonts/Lato.ttf")
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build())





        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            if(it.isComplete){

                try {
                    it.result?.let{
                        savePreverence(this,"fcmid",it)
                    }
                } catch (e: Exception) {
                }
//                Log.e("FCMID",deviceToken)
            }
        }


        Realm.init(applicationContext)
        var config= RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build()
        Realm.setDefaultConfiguration(config)


        val cfg = Configuration()
        if (!TextUtils.isEmpty("en"))
            cfg.locale = Locale("en")
        else
            cfg.locale = Locale.getDefault()

        getResources().updateConfiguration(cfg, null)


    }

    /**
     * Gets the default [Tracker] for this [Application].
     * @return tracker
     */
//    @Synchronized
//    fun getDefaultTracker(): Tracker {
//        // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
//
//        sTracker = sAnalytics!!.newTracker(R.xml.global_tracker)
//
//
//        return sTracker
//    }

}