package com.langit7.hondasalesforce.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class productaccessories(
    @SerializedName("created_date")
    val createdDate: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("product")
    val product: product,
    @SerializedName("title")
    val title: String,
    @SerializedName("producttype")
    val type: String,
    @SerializedName("body")
    val body: String,
    @SerializedName("desc_price")
    val desc_price: String
):Serializable


