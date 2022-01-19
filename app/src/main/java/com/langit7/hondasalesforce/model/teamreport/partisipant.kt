package com.langit7.hondasalesforce.model.teamreport
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class partisipant(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("score")
    val score: Double,
    @SerializedName("created_date")
    val createdDate: String,
    @SerializedName("user_id")
    val user_id: partisipant_user,
): Serializable