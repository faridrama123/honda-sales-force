package com.langit7.hondasalesforce.model.teamreport
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class partisipant_user_profile(
    @SerializedName("id")
    val id: Int,
    @SerializedName("hondaid")
    val hondaid: String,
    @SerializedName("position")
    val position: String,
): Serializable