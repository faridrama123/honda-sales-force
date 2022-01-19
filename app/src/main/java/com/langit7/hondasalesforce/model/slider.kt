package com.langit7.hondasalesforce.model
import com.google.gson.annotations.SerializedName


data class slider(
    @SerializedName("created_date")
    val createdDate: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("slide_order")
    val slideOrder: Int,
    @SerializedName("first_redirect_url")
    val first_redirect_url: String
)