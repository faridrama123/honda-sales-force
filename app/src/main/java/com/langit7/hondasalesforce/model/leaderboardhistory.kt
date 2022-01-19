package com.langit7.hondasalesforce.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class leaderboardhistory(
    @SerializedName("medal_type")
    val medal_type: String,
    @SerializedName("total_point")
    val total_point: String,
    @SerializedName("desc_created_date")
    val created_date: String,
    @SerializedName("desc_medal_type")
    val desc_medal_type: String

):Serializable