package com.langit7.hondasalesforce.API


import android.app.Activity
import android.content.Intent
import android.util.Log
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.model.*
import com.langit7.hondasalesforce.model.teamreport.*
import com.langit7.hondasalesforce.view.activity.MainActivity
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit


interface api {

    @GET("list-monitoring-quiz/")
    fun getQuizAwal(
        @Query("xs") xs: String,
        @Query("main_dealer") main_dealer: String,
    ): Observable<baseresponse<List<quizawal>>>

    @GET("list-participant-quiz/")
    fun getPartisipant(
        @Query("xs") xs: String,
        @Query("nitems") nitems: Int,
        @Query("page") page: Int,
        @Query("main_dealer") main_dealer: String,
        @Query("category") category: String,
        @Query("month") month: String,
        @Query("year") year: String,
        @Query("category_position") category_position: String,
        @Query("is_participant") is_participant: String,
    ): Observable<baseresponse<List<partisipant>>>

    @GET("list-participant-quiz/")
    fun getPartisipantQuizAndMonitor(
        @Query("xs") xs: String,
        @Query("nitems") nitems: Int,
        @Query("page") page: Int,
        @Query("main_dealer") main_dealer: String,
        @Query("category") category: String,
        @Query("month") month: String,
        @Query("year") year: String,
        @Query("category_position") category_position: String,
        @Query("is_participant") is_participant: String,
    ): Observable<baseresponse<List<PartisipantQuiz>>>


    @GET("list-participant-qualified/")
    fun getPartisipantQualified(
        @Query("xs") xs: String,
        @Query("nitems") nitems: Int,
        @Query("page") page: Int,
        @Query("main_dealer") main_dealer: String,
        @Query("category") category: String,
        @Query("semester") semester: String,
        @Query("year") year: String,
        @Query("category_position") category_position: String,
    ): Observable<baseresponse<List<ListParticipantQualified>>>

    @GET("list-monitoring-quiz-det/")
    fun getPartisipantDetail(
        @Query("xs") xs: String,
        @Query("main_dealer") main_dealer: String,
        @Query("category") category: String,
        @Query("month") month: String,
        @Query("year") year: String,

    ): Observable<baseresponse<PartisipantDetail>>

    @GET("list-frequent-user/")
    fun getFrequentUser(
        @Query("xs") xs: String,
        @Query("nitems") nitems: Int,
        @Query("page") page: Int,
        @Query("main_dealer") main_dealer: String,
        @Query("date") date: String,
        @Query("category") category: String,
    ): Observable<baseresponse<List<FrequentUser>>>

    @GET("list-monitoring-freq-user/")
    fun getFrequent(
        @Query("xs") xs: String,
        @Query("main_dealer") main_dealer: String,
        @Query("date") date: String,
    ): Observable<baseresponse<frequent>>

    @GET("list-monitoring-qualified/")
    fun getMonitoringQualified(
        @Query("xs") xs: String,
        @Query("main_dealer") main_dealer: String,
        @Query("semester") semester: String,
        @Query("year") date: String,
    ): Observable<baseresponse<MonitoringQualified>>

    @GET("list-vaccine-reason/")
    fun getVaccineReason(
        @Query("xs") xs: String,
    ): Observable<baseresponse<List<vaccinereason>>>

    @Multipart
    @PUT("list-user-profile/")
    fun editprofile(
        @Part("user_id") user_id: RequestBody,
        @Part("statusvaccine1") statusvaccine1: RequestBody?,
        @Part("statusvaccine2") statusvaccine2: RequestBody?,
        @Part("reasonvaccine1") reasonvaccine1: RequestBody?,
        @Part("reasonvaccine2") reasonvaccine2: RequestBody?,
        @Part image: MultipartBody.Part?
    ): Observable<user>



    @GET("detail-history-user-quiz/")
    fun getQuisHistoryDetail(
        @Query("xs") xs: String,
        @Query("page") page: String,
        @Query("nitems") nitems: String,
        @Query("user_quiz_answer_id") user_quiz_answer_id: String
    ): Observable<baseresponse<List<quizhistorydetail>>>

    @FormUrlEncoded
    @PUT("get-user/{id}/")
    fun updateAvatar( @Path("id") uid:String ,@Field("chosen_medal_type") chosen_medal_type: String): Observable<user>

    @GET("list-product-recommendation/")
    fun getProductRecomendation(
        @Query("xs") xs: String,
        @Query("page") page: String,
        @Query("nitems") nitems: String,
        @Query("gender") gender: String,
        @Query("age") age: String,
        @Query("is_know_product") is_know_product: String
    ): Observable<baseresponse<List<product>>>


    @GET("/list-history-leaderboard/")
    fun getLeaderboardHistory(
        @Query("xs") xs: String,
        @Query("page") page: String,
        @Query("nitems") nitems: String,
        @Query("user_id") user_id: String
    ): Observable<baseresponse<List<leaderboardhistory>>>

    @GET("/list-sales-program/")
    fun getSalesProgram(
        @Query("xs") xs: String,
        @Query("page") page: String,
        @Query("nitems") nitems: String
    ): Observable<baseresponse<List<salesprogram>>>

    @GET("/list-poster/")
    fun getListPoster(
        @Query("xs") xs: String,
        @Query("page") page: String,
        @Query("nitems") nitems: String
    ): Observable<baseresponse<List<salesprogram>>>


    @GET("/list-leaderboard/")
    fun getLeaderboard(
        @Query("xs") xs: String,
        @Query("page") page: String,
        @Query("nitems") nitems: String,
        @Query("main_dealer") main_dealer: String,
        @Query("dealer_id") dealer_id: String
    ): Observable<baseresponse<List<leaderboard>>>

    @GET("/list-network-operational-standard/")
    fun getNOS(
        @Query("xs") xs: String
    ): Observable<baseresponse<List<nos>>>

    @GET("/list-grooming-category/")
    fun getGrooming(
        @Query("xs") xs: String
    ): Observable<baseresponse<List<grooming>>>

    @GET("/list-salesmanship/")
    fun getSalesmanship(
        @Query("xs") xs: String
    ): Observable<baseresponse<List<productfeature>>>

    @GET("/list-customer-service/")
    fun getCS(
        @Query("xs") xs: String
    ): Observable<baseresponse<List<productfeature>>>

    @GET("/list-customer-service-leader/")
    fun getCSL(
        @Query("xs") xs: String
    ): Observable<baseresponse<List<productfeature>>>

    @GET("/list-complaint-handling/")
    fun getCH(
        @Query("xs") xs: String
    ): Observable<baseresponse<List<productfeature>>>

    @GET("/list-customer-relationship-management/")
    fun getCRM(
        @Query("xs") xs: String
    ): Observable<baseresponse<List<productfeature>>>


    @FormUrlEncoded
    @POST("/logout-user/")
    fun logout(
        @Field("user_id") user_id: String
    ): Observable<user>

    @FormUrlEncoded
    @POST("/list-survey-rating/")
    fun submitRating(
        @Field("rating") rating: String,
        @Field("user_id") user_id: String
    ): Observable<Response<rating>>

    @GET("/history-redeem/")
    fun getHistoryRedeem(
        @Query("xs") xs: String,
        @Query("user_id") user_id: String
    ): Observable<baseresponse<List<redeem>>>

    @GET("/list-variable/")
    fun getVersion(@Query("xs") xs: String): Observable<baseresponse<version>>

    @GET("/list-user/")
    fun getHID(@Query("hondaid") hondaid: String): Observable<user>

    @GET("/list-history-user-quiz/")
    fun getQuisHistory(@Query("xs") xs: String, @Query("user_id") user_id: String): Observable<baseresponse<List<quizresponse>>>

    @FormUrlEncoded
    @POST("/list-redeem-promo/")
    fun redeemPromo(
        @Field("promoid") promoid: String,
        @Field("user_id") user_id: String,
        @Field("redeem_code") redeem_code: String
    ): Observable<Response<redeem>>

    @GET("/list-redeem-promo/")
    fun getRedemmed(@Query("xs") xs: String, @Query("user_id") user_id: String): Observable<baseresponse<List<redeem>>>

    @GET("/list-promo/")
    fun getPromo(@Query("xs") xs: String, @Query("user_id") user_id: String): Observable<baseresponse<List<promo>>>


    @GET("/list-advertisement/")
    fun getAds(@Query("xs") xs: String): Observable<baseresponse<List<ads>>>


    @FormUrlEncoded
    @POST("/history-log/")
    fun saveHistory(
        @Field("category") category: String,
        @Field("user_id") user_id: String,
        @Field("transactionid") transactionid: String
    ): Observable<history>

    @FormUrlEncoded
    @POST("/page-counter/")
    fun saveCounter(
        @Field("category") category: String,
        @Field("user_id") user_id: String,
        @Field("transactionid") transactionid: String
    ): Observable<history>


    @FormUrlEncoded
    @PUT("/update-read-all-notif/")
    fun readAllNotif( @Field("user_id") status: String): Observable<baseresponse<String>>

    @FormUrlEncoded
    @PUT("/detail-notification/{id}/")
    fun readNotif(@Path("id") notifid: String, @Field("status") status: String): Observable<notif>

    @DELETE("/detail-notification/{id}/")
    fun deleteNotif(@Path("id") notifid: String): Observable<baseresponse<notif>>

    @GET("/list-notification/")
    fun getNotif(
        @Query("user_id") user_id: String,
        @Query("nitems") nitems: String,
        @Query("page") page: String,
        @Query("xs") xs: String
    ): Observable<baseresponse<List<notif>>>


    @GET("/detail-feature/{id}/")
    fun getFeature(
        @Path("id") productid: String,
        @Query("xs") xs: String,
        @Query("gender") gender: String,
        @Query("age") age: String,
        @Query("is_know_product") is_know_product: String
    ): Observable<baseresponse<List<productfeature>>>


    @GET("/list-sales-talk/")
    fun getSalesTalk(@Query("xs") xs: String, @Query("product") product: String): Observable<baseresponse<List<salestalk>>>

//    @GET("/list-notification/")
//    fun getNotif(@Query("xs") xs:String,@Query("user_id") user_id:String): Observable<baseresponse<List<not>>>


    @GET("/detail-user/{id}/")
    fun getUser(@Path("id") userid: String, @Query("xs") xs: String): Observable<baseresponse<user>>


    @FormUrlEncoded
    @POST("/forgot-password/")
    fun forgotpassword(
        @Field("email") email: String
    ): Observable<Response<baseresponse<user>>>

    @FormUrlEncoded
    @POST("/api-token-auth/")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("fcmid") fcmid: String
    ): Observable<Response<user>>


    @FormUrlEncoded
    @POST("/list-user/")
    fun register(
        @Field("fullname") fullname: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("hondaid") hondaid: String,
        @Field("phone") phone: String,
        @Field("position") position: String
    ): Observable<Response<user>>


    @GET("/list-slider/")
    fun getSlider(@Query("xs") xs: String): Observable<baseresponse<List<slider>>>


    @GET("/list-article/")
    fun getListArticle(@Query("xs") xs: String, @Query("user_id") user_id: String): Observable<baseresponse<List<article>>>


    @FormUrlEncoded
    @POST("/article-counter-read-user/")
    fun readArticle(
        @Field("user_id") user_id: String,
        @Field("article_id") article_id: String,
        @Field("is_read") is_read: String
    ): Observable<Response<article>>

    @GET("/detail-apparel/{id}/")
    fun getApparelbyId(@Path("id") id: String, @Query("xs") xs: String): Observable<baseresponse<apparel>>

 @GET("/get-apparel/")
    fun getApparelbyUrl(@Query("link_url") link_url: String): Observable<baseresponse<apparel>>


    @GET("/list-apparel-category/")
    fun getApparelCategory(@Query("xs") xs: String): Observable<baseresponse<List<apparelcategory>>>

    @GET("/list-apparel/{id}/")
    fun getApparelByCategory(
        @Path("id") id: String,
        @Query("xs") xs: String
    ): Observable<baseresponse<List<apparel>>>

    @GET("/list-category/")
    fun getProductCategory(@Query("xs") xs: String): Observable<baseresponse<List<productcategory>>>

    @GET("/list-product/")
    fun getProductByCategory(
        @Query("category") id: String,
        @Query("xs") xs: String
    ): Observable<baseresponse<List<product>>>


    @GET("/list-customizing-parts/")
    fun getCustomizingPatrs(@Query("xs") xs: String): Observable<baseresponse<List<cuspart>>>


    @GET("/list-community/")
    fun getCommunity(@Query("xs") xs: String): Observable<baseresponse<List<community>>>

    @GET("/list-service-talk/")
    fun getServiceTalk(@Query("xs") xs: String): Observable<baseresponse<List<servicetalk>>>

    @GET("/list-modification-workshop/")
    fun getBengkelModifikasi(@Query("xs") xs: String): Observable<baseresponse<List<bengkelmodif>>>


    @GET("/list-modification-workshop-producttype/")
    fun getBengkelModifikasiType(@Query("xs") xs: String): Observable<baseresponse<List<bengkelmodiftype>>>

    @GET("/list-modification-workshop-service/")
    fun getBengkelModifikasiService(@Query("xs") xs: String): Observable<baseresponse<List<bengkelmodifservice>>>

    @GET("/list-province/")
    fun getProvince(@Query("xs") xs: String): Observable<baseresponse<List<region>>>

    @GET("/list-city/")
    fun getCity(@Query("xs") xs: String, @Query("province") province: String): Observable<baseresponse<List<region>>>


    @GET("/list-video-category/")
    fun getVideoCat(@Query("xs") xs: String): Observable<baseresponse<List<videocat>>>

    @GET("/list-video/")
    fun getVideo(@Query("xs") xs: String): Observable<baseresponse<List<video>>>

    @GET("/list-quiz/")
    fun getQuiz(@Query("xs") xs: String, @Query("user_id") user_id: String): Observable<baseresponse<List<quiz>>>


    @GET("/list-question/")
    fun getQuestion(@Query("xs") xs: String, @Query("quiz_id") quiz_id: String): Observable<baseresponse<List<question>>>


    @FormUrlEncoded
    @POST("/submit-quiz/")
    fun submitQuiz(
        @Field("quiz_id") quiz_id: String,
        @Field("user_id") user_id: String,
        @Field("question_id[]") question_id: ArrayList<String>,
        @Field("answer[]") answer: ArrayList<String>
    ): Observable<Response<quizresponse>>


    @GET("/list-survey/")
    fun getSurvey(@Query("xs") xs: String, @Query("user_id") user_id: String): Observable<baseresponse<List<survey>>>


    @GET("/list-survey-question/")
    fun getSurveyQuestion(@Query("xs") xs: String, @Query("survey_id") quiz_id: String): Observable<baseresponse<List<surveyquestion>>>

    @GET("/detail-accessories/{product_id}/")
    fun getProductAccessories(@Path("product_id") product_id: String, @Query("xs") xs: String): Observable<baseresponse<List<productaccessories>>>


    @FormUrlEncoded
    @POST("/submit-survey/")
    fun submitSurvey(
        @Field("survey_id") survey_id: String,
        @Field("user_id") user_id: String,
        @Field("question_id[]") question_id: ArrayList<String>,
        @Field("answer[]") answer: ArrayList<String>
    ): Observable<Response<surveyresponse>>


    @GET("/list-specification/")
    fun getSpesification(@Query("xs") xs: String): Observable<baseresponse<List<spesification>>>

    @GET("/detail-specification/{id}")
    fun getSpesificationDetail(@Path("id") product_id: String, @Query("xs") xs: String): Observable<baseresponse<List<spesification>>>

    @GET("/list-position/")
    fun getposition(@Query("xs") xs: String, @Query("page") page: String): Observable<baseresponse<List<position>>>

    companion object {

        val BASEURL="https://myheromobileapps.com"
        val APPTOKEN="a536142424f486dbf95eb964e145345177bb7e99"

        fun create(act:Activity): api {

            var  client= okhttp3(act)

            val retrofit = Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create()
                )
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .baseUrl(BASEURL)
                .build()

            return retrofit.create(api::class.java)
        }


        private fun okhttp3(act: Activity): OkHttpClient {
            val httpClient = OkHttpClient().newBuilder()

            httpClient.connectTimeout(30000,TimeUnit.MILLISECONDS)
            httpClient.readTimeout(30000,TimeUnit.MILLISECONDS)
            httpClient.writeTimeout(30000,TimeUnit.MILLISECONDS)


            val interceptor = Interceptor { chain ->

                val request = chain.request().newBuilder()
                val token=function.getPreverence(act,"token")
                if(token.length>0)
                    request.addHeader("Authorization", "Token "+token)
                else
                    request.addHeader("Authorization", "Token "+ APPTOKEN)


                val res=chain.proceed(request.build())




                if(res.code()==403 && function.getPreverence(act,"token").length>0){

                    var body=""

                    body=res.body()!!.string()
                    if( body.equals("{\"message\":\"Invalid token\"}")) {
//                        Log.e("Session Expired", "exp")
//                    Log.e("FORBIDEN",res.body()!!.string())
                        //act.Toast("Sesi anda telah berakhir atau anda login di tempat lain")
//                    DialogUtil.createYesDialog(act,"Sesion Expired","ok")
                        function.doLogout(act)

                        var ii = Intent(act, MainActivity::class.java)
                        function.savePreverence(act, "hasaddialog", false)
                        ii.putExtra("logout",true)
                        ii.putExtra("msg", "Sesi anda telah habis atau anda login di tempat lain")
                        ii.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        act.startActivity(ii)
                        act.finish()
                    }

                }

                 return@Interceptor res

            }
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            httpClient.networkInterceptors().add(interceptor)
            httpClient.interceptors().add(logging)//loging


            return httpClient.build()
        }

//        private fun okhttp3(): OkHttpClient {
//            val httpClient = OkHttpClient().newBuilder()
//
//            httpClient.connectTimeout(120000,TimeUnit.MILLISECONDS)
//            httpClient.readTimeout(120000,TimeUnit.MILLISECONDS)
//            httpClient.writeTimeout(120000,TimeUnit.MILLISECONDS)
//
//            val interceptor = Interceptor { chain ->
//
//                val request = chain.request().newBuilder()
//                    request.addHeader("Authorization", "Token "+APPTOKEN)
//                val res=chain.proceed(request.build())
//                res
//
//            }
//            val logging = HttpLoggingInterceptor()
//            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
//
//            httpClient.networkInterceptors().add(interceptor)
//            httpClient.networkInterceptors().add(logging)
//
//
//
//            return httpClient.build()
//
//        }
    }
}