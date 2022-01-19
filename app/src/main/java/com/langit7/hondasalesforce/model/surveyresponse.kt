package com.langit7.hondasalesforce.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class surveyresponse(
    @SerializedName("created_date")
    val createdDate: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("survey")
    val survey: survey,
    @SerializedName("title")
    val title: String,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("total_point")
    val total_point: String
):Serializable

