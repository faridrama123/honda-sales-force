package com.langit7.hondasalesforce.model
import com.google.gson.annotations.SerializedName


data class version(
    @SerializedName("id")
    val id: String,
    @SerializedName("last_version")
    val lastVersion: String,
    @SerializedName("update_url")
    val updateUrl: String,
    @SerializedName("is_survey_rating")
    val is_survey_rating: String,
    @SerializedName("days_register_login_rating")
    val days_register_login_rating: String,
    @SerializedName("desc_is_survey_rating")
    val desc_is_survey_rating: String

)