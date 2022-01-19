package com.langit7.hondasalesforce.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class survey(
    @SerializedName("desc_is_active")
    val descIsActive: String,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("end_date")
    val endDate: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("is_active")
    val isActive: String,
    @SerializedName("start_date")
    val startDate: String,
    @SerializedName("title")
    val title: String
):Serializable