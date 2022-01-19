package com.langit7.hondasalesforce.model
import com.google.gson.annotations.SerializedName


data class vaccinereason(
    @SerializedName("body")
    val body: String,
    @SerializedName("created_date")
    val createdDate: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String
)
