package com.langit7.hondasalesforce.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class salestalk(
    @SerializedName("body")
    val body: String,
    @SerializedName("created_date")
    val createdDate: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("updated_date")
    val updatedDate: String,
    @SerializedName("user_id")
    val userId: Int
): Serializable