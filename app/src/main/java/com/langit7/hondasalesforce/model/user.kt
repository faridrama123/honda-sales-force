package com.langit7.hondasalesforce.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class user(
    @SerializedName("email")
    val email: String,
    @SerializedName("first_name")
    var firstName: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("profile_user")
    val profileUser: userprofile,
    @SerializedName("total_insert_data")
    val totalInsertData: String,
    @SerializedName("total_login_apps")
    val totalLoginApps: String,
    @SerializedName("total_read_sales_talk")
    val totalReadSalesTalk: String,
    @SerializedName("total_watch_video")
    val totalWatchVideo: String,
    @SerializedName("total_quiz")
    val total_quiz: String,
    @SerializedName("total_quiz_passed")
    val total_quiz_passed: String,
    @SerializedName("total_quiz_failed")
    val total_quiz_failed: String,
    @SerializedName("total_survey")
    val total_survey: String,
    @SerializedName("total_read_product")
    val total_read_product: String,
    @SerializedName("total_read_general_knowledge")
    val total_read_general_knowledge: String,
    @SerializedName("total_read_article")
    val total_read_article: String,
    @SerializedName("total_share_prospect_collection")
    val total_share_prospect_collection: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("message")
    val message: String


):Serializable


