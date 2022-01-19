package com.langit7.hondasalesforce.model
import com.google.gson.annotations.SerializedName


data class ads(
    @SerializedName("created_date")
    val createdDate: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("redirect_url")
    val redirectUrl: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("updated_date")
    val updatedDate: String,
    @SerializedName("user_id")
    val userId: Int
)