package com.langit7.hondasalesforce.model
import com.google.gson.annotations.SerializedName


data class videocat(
    @SerializedName("created_date")
    val createdDate: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("order")
    val order: Int,
    @SerializedName("title")
    val title: String
)