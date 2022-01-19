package com.langit7.hondasalesforce.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class promo(
    @SerializedName("body")
    val body: String,
    @SerializedName("created_date")
    val createdDate: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("point_needed")
    val pointNeeded: String,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("updated_date")
    val updatedDate: String,
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("validity_period")
    val validityPeriod: String
):Serializable