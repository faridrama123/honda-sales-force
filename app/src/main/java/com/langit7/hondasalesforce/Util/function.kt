package com.langit7.hondasalesforce.Util

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.icu.lang.UCharacter
import android.preference.PreferenceManager
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import java.text.SimpleDateFormat
import java.util.*
import com.google.gson.Gson
import com.langit7.hondasalesforce.model.user
import org.json.JSONObject
import android.text.Html
import android.os.Build
import android.provider.MediaStore
import android.text.Spanned
import android.util.*
import android.util.Base64
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.model.version
import com.langit7.hondasalesforce.presenter.logic.MainPresenter
import com.langit7.hondasalesforce.view.activity.BaseActivity
import java.io.*
import java.util.regex.Pattern


object function {
    fun getBitmapFromCaptureImageResult(ctx: Context, data: Intent) : File? {
        val thumbnail = data.extras!!.get("data") as Bitmap
        val bytes = ByteArrayOutputStream()
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes)

        val destination = File(
            ctx.cacheDir,
            System.currentTimeMillis().toString() + ".jpg")
        val fo: FileOutputStream
        try {
            destination.createNewFile()
            fo = FileOutputStream(destination)
            fo.write(bytes.toByteArray())
            fo.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return destination
    }
    fun getBitmapFromGalleryResult(ctx: Context, data: Intent?) : File? {
        var bm: Bitmap? = null
        var bmout: Bitmap?=null
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(ctx.applicationContext.contentResolver, data.data)
//                Log.e("before", bm.byteCount.toString())
                val bytes = ByteArrayOutputStream()
                bm.compress(Bitmap.CompressFormat.JPEG, 90, bytes)


                val destination = File(
                    ctx.cacheDir,
                    System.currentTimeMillis().toString() + ".jpg")
                val fo: FileOutputStream

                destination.createNewFile()
                fo = FileOutputStream(destination)
                fo.write(bytes.toByteArray())
                fo.close()

//                Log.e("path", destination.path)

                return destination
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }


        return null
//        return bmout!!
    }

    fun getMedalPoint(type:String):String{

        if(type.equals("1"))
            return ""
        if(type.equals("2"))
            return "500 Poin"
        if(type.equals("3"))
            return "1000 Poin"
        if(type.equals("4"))
            return "1500 Poin"
        if(type.equals("5"))
            return "2000 Poin"
        if(type.equals("6"))
            return "2500 Poin"

        return ""
    }
    fun getMedalName(type:String):String{

        if(type.equals("1"))
            return "Bronze"
        if(type.equals("2"))
            return "Silver"
        if(type.equals("3"))
            return "Gold"
        if(type.equals("4"))
            return "Platinum"
        if(type.equals("5"))
            return "Diamond"
        if(type.equals("6"))
            return "Legend"

        return ""
    }
    fun getMedalImageSmall(type:String):Int{

        if(type.equals("1"))
            return R.drawable.icon_bronze_small
        if(type.equals("2"))
            return R.drawable.icon_silver_small
        if(type.equals("3"))
            return R.drawable.icon_gold_small
        if(type.equals("4"))
            return R.drawable.icon_platinum_small
        if(type.equals("5"))
            return R.drawable.icon_diamond_small
        if(type.equals("6"))
            return R.drawable.icon_legend_small

        return R.drawable.icon_bronze_small
    }
    fun getMedalImageLarge(type:String):Int{

        if(type.equals("1"))
            return R.drawable.icon_bronze_big
        if(type.equals("2"))
            return R.drawable.icon_silver_big
        if(type.equals("3"))
            return R.drawable.icon_gold_big
        if(type.equals("4"))
            return R.drawable.icon_platinum_big
        if(type.equals("5"))
            return R.drawable.icon_diamond_big
        if(type.equals("6"))
            return R.drawable.icon_legend_big

        return R.drawable.icon_bronze_big
    }

    fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()

//        return Pattern.compile(
//            "^[A-Z0-9._%+-]+@"
//                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\.([0-1]?"
//                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\."
//                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\.([0-1]?"
//                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
//                    + "([a-zA-Z0-9]+[\w-]+\.)+[a-zA-Z]{2,4})$"
//        ).matcher(email).matches()
//        return Pattern.compile(
//            "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
//        ).matcher(email).matches()
    }

    fun fromHtml(html: String): Spanned {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(html)
        }
    }



    fun getAgeYear(first: Date, last: Date): String {
        val a = getCalendar(first)
        val b = getCalendar(last)

//        Log.e("substract data", dateToString(first, "yyyy MM dd") + "---" + dateToString(last, "yyyy MM dd"))


        var diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR)
        var month = 0
        if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH) || a.get(Calendar.MONTH) == b.get(Calendar.MONTH) && a.get(Calendar.DATE) > b.get(Calendar.DATE)) {
            diff--
            if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH)) {
                month = 12 - a.get(Calendar.MONTH) + b.get(Calendar.MONTH)
            }
        } else if (a.get(Calendar.MONTH) < b.get(Calendar.MONTH)) {
            month = b.get(Calendar.MONTH) - a.get(Calendar.MONTH)
        }


        var result = diff.toString() + " Tahun "

        if (month > 0)
            result = result + month.toString() + " Bulan"

        return result
    }

    fun getAgeMonth(first: Date, last: Date): String {
        val a = getCalendar(first)
        val b = getCalendar(last)
        var diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR)
        var month = 0
        if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH) || a.get(Calendar.MONTH) == b.get(Calendar.MONTH) && a.get(Calendar.DATE) > b.get(Calendar.DATE)) {
            diff--
            if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH)) {
                month = 12 - a.get(Calendar.MONTH) + b.get(Calendar.MONTH)
            }
        } else if (a.get(Calendar.MONTH) < b.get(Calendar.MONTH)) {
            month = b.get(Calendar.MONTH) - a.get(Calendar.MONTH)
        }


        month = month + diff * 12

        return month.toString() + " Bulan"
    }

    fun getCalendar(date: Date): Calendar {
        val cal = Calendar.getInstance(Locale.US)
        cal.time = date
        return cal
    }


    fun dateToString(date: Date?, format: String): String {

        if(date==null)
            return ""

        val formatter = SimpleDateFormat(format)
        var res=formatter.format(date)
        if(res==null)
            res=""
        return res
    }

    fun getToday():String{
        return  dateToString(Date(),"yyyyMMdd")
    }

    fun formatRupiah(`val`: String): String {

        try {
            return "Rp" + Character.toString(160.toChar()) + String.format("%,d", Integer.valueOf(`val`)).replace(",", ".")
        } catch (e: NumberFormatException) {
            return "Rp" + Character.toString(160.toChar()) + `val`
        }

    }
    fun formatNumber(`val`: String): String {

        try {
            return String.format("%,d", Integer.valueOf(`val`)).replace(",", ".")
        } catch (e: NumberFormatException) {
            return `val`
        }

    }
    fun parseDate(date: String, format: String): Date? {


        //String date = "2011/11/12 16:05:06";
        val sdf = SimpleDateFormat(format)
        try {
            return sdf.parse(date)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        return null
    }

    fun intToStringTime(number: Int): String {

        var n = number.toString()
        if (number < 10)
            n = "0$n"

        return n
    }

    fun setBackgroundColor(view: View, color: String) {
        view.setBackgroundColor(Color.parseColor(color))
    }

    fun converDateFormat(date: String, formatfrom: String, formatto: String): String {


        //String date = "2011/11/12 16:05:06";


        val sdf = SimpleDateFormat(formatfrom)
        var testDate: Date? = null
        try {
            testDate = sdf.parse(date)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        //ateFormat f = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT, Locale.getDefault());


        var formatter = SimpleDateFormat(formatto, Locale.getDefault())
        return formatter.format(testDate)
    }

    fun savePreverence(ctx: Context, key: String, value: String) {

//        var skey=key
//        if(getUser(ctx)!=null)
//            skey= getUser(ctx)!!.id+key

        val prefs = PreferenceManager.getDefaultSharedPreferences(ctx)
        prefs.edit().putString(key, value).apply()
    }

    fun getPreverence(ctx: Context, key: String): String {
//        var skey=key
//        if(getUser(ctx)!=null)
//            skey= getUser(ctx)!!.id+key


        val prefs = PreferenceManager.getDefaultSharedPreferences(ctx)
//        if(str.isEmpty())
        //            return null;
        //        else
        return prefs.getString(key, "")?:""

    }

    fun savePreverence(ctx: Context, key: String, value: Boolean) {
//        var skey=key
//        if(getUser(ctx)!=null)
//            skey= getUser(ctx)!!.id+key

        val prefs = PreferenceManager.getDefaultSharedPreferences(ctx)
        prefs.edit().putBoolean(key, value).apply()
    }

    fun getPreverenceBool(ctx: Context, key: String): Boolean {
//        var skey=key
//        if(getUser(ctx)!=null)
//            skey= getUser(ctx)!!.id+key

        val prefs = PreferenceManager.getDefaultSharedPreferences(ctx)
        return prefs.getBoolean(key, false)
    }

    fun dp2px(ct: Context, dp: Int): Int {
//        val scale = ct.resources.displayMetrics.density
//        return (dp * scale + 0.5f).toInt()

        val r = ct.resources
        val px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), r.getDisplayMetrics())
        return px.toInt()



    }

    //    public static String getBody(Response result) {
    //
    //        //Try to get response body
    //        BufferedReader reader = null;
    //        StringBuilder sb = new StringBuilder();
    //        try {
    //
    //            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));
    //
    //            String line;
    //
    //            try {
    //                while ((line = reader.readLine()) != null) {
    //                    sb.append(line);
    //                }
    //            } catch (IOException e) {
    //                e.printStackTrace();
    //            }
    //        } catch (IOException e) {
    //            e.printStackTrace();
    //        }
    //
    //
    //        return sb.toString();
    //
    //    }

    fun setListViewHeightBasedOnChildren(listView: ListView) {
        val listAdapter = listView.adapter ?: return

        val desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.width, View.MeasureSpec.UNSPECIFIED)
        var totalHeight = 0
        var view: View? = null

        for (i in 0 until listAdapter.count) {
            view = listAdapter.getView(i, view, listView)

            if (i == 0)
                view!!.layoutParams = ViewGroup.LayoutParams(desiredWidth,
                        ViewGroup.LayoutParams.MATCH_PARENT)

            view!!.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED)
            totalHeight += view.measuredHeight

        }

        val params = listView.layoutParams
        params.height = totalHeight + listView.dividerHeight * listAdapter.count

        listView.layoutParams = params
        listView.requestLayout()

    }


    fun isLogin(ctx: Context): Boolean {

        val user= getUser(ctx)

        return if ( user!=null &&user!!.username.length > 0) {
            true
        } else {
            false
        }
    }

    fun getUser(ctx: Context): user? {
        val prefs = PreferenceManager.getDefaultSharedPreferences(ctx)
        var u= prefs.getString("user", "")
        val user= Gson().fromJson<user>(u, user::class.java)
        if(user!=null)
        user.firstName=user.firstName.substring(0,1).toUpperCase() + user.firstName.substring(1,user.firstName.length)
        return user
    }

    fun getSettings(ctx: Context): version? {
        val u = getPreverence(ctx, "settings")
        val version= Gson().fromJson<version>(u, version::class.java)
        return version
    }


    fun doLogout(ctx: Context) {
        var act=ctx as BaseActivity
        var pr=MainPresenter(act,act.APIServices)
        pr.logout()
        savePreverence(ctx, "user", "")
        savePreverence(ctx, "token", "")
    }

    fun timeDifference(startDate: Date, endDate: Date): String {

        //milliseconds
        var different = endDate.time - startDate.time

        //        System.out.println("startDate : " + startDate);
        //        System.out.println("endDate : "+ endDate);
        //        System.out.println("different : " + different);

        val secondsInMilli: Long = 1000
        val minutesInMilli = secondsInMilli * 60
        val hoursInMilli = minutesInMilli * 60
        val daysInMilli = hoursInMilli * 24


        different = different % daysInMilli

        val elapsedHours = different / hoursInMilli
        different = different % hoursInMilli

        val elapsedMinutes = different / minutesInMilli
        different = different % minutesInMilli

        val elapsedSeconds = different / secondsInMilli

        //        System.out.printf(
        //                "%d days, %d hours, %d minutes, %d seconds%n",
        //                elapsedDays,
        //                elapsedHours, elapsedMinutes, elapsedSeconds);

        return intToStringTime(elapsedHours.toInt()) + ":" + intToStringTime(elapsedMinutes.toInt()) + ":" + intToStringTime(elapsedSeconds.toInt())

    }

    fun hourDifference(startDate: Date, endDate: Date): Float {

        //milliseconds
        val different = (endDate.time - startDate.time).toFloat()

        //        System.out.println("startDate : " + startDate);
        //        System.out.println("endDate : "+ endDate);
        //        System.out.println("different : " + different);

        val secondsInMilli = 1000f
        val minutesInMilli = secondsInMilli * 60
        val hoursInMilli = minutesInMilli * 60


        //        long elapsedDays = different / daysInMilli;
        //        different = different % daysInMilli;
        //
        //        long elapsedHours = different / hoursInMilli;
        //        different = different % hoursInMilli;
        //
        //        long elapsedMinutes = different / minutesInMilli;
        //        different = different % minutesInMilli;
        //
        //        long elapsedSeconds = different / secondsInMilli;

        //        System.out.printf(
        //                "%d days, %d hours, %d minutes, %d seconds%n",
        //                elapsedDays,
        //                elapsedHours, elapsedMinutes, elapsedSeconds);

        return different / hoursInMilli

    }
}