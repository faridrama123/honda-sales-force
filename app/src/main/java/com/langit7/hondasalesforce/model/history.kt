package com.langit7.hondasalesforce.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class history(
    @SerializedName("action")
    val action: String,
    @SerializedName("created_date")
    val createdDate: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("transactionid")
    val transactionid: String,
    @SerializedName("producttype")
    val type: String,
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("total_point")
    val total_point: String
):Serializable