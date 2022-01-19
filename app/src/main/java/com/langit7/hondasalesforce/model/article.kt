package com.langit7.hondasalesforce.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class article(
    @SerializedName("body")
    val body: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("created_date")
    val createdDate: String,
    @SerializedName("desc_category")
    val descCategory: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("post_date")
    val postDate: String,
    @SerializedName("summary")
    val summary: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("updated_date")
    val updatedDate: String,
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("is_read")
    var is_read: String
):Serializable