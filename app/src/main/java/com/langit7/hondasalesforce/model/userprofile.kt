package com.langit7.hondasalesforce.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class userprofile(
    @SerializedName("hondaid")
    val hondaid: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("position")
    val position: String,
    @SerializedName("parent_position")
    val parent_position: String,
    @SerializedName("total_point")
    val totalPoint: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("status_registration")
    val status_registration: String,
    @SerializedName("is_survey_rating")
    val is_survey_rating: String ,
    @SerializedName("token")
    val token: String,
    @SerializedName("is_login")
    val is_login: String,
    @SerializedName("medal_type")
    val medal_type: String,
    @SerializedName("chosen_medal_type")
    val chosen_medal_type: String,
    @SerializedName("desc_medal_type")
    val desc_medal_type: String,
    @SerializedName("dealer")
    val dealer: dealer,
    @SerializedName("image")
    val image: String?,
    @SerializedName("statusvaccine1")
    val statusvaccine1: String? ,
    @SerializedName("statusvaccine2")
    val statusvaccine2: String?

): Serializable