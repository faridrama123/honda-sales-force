package com.langit7.hondasalesforce.model
import com.google.gson.annotations.SerializedName


data class rating(
    @SerializedName("created_date")
    val createdDate: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("rating")
    val rating: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("updated_date")
    val updatedDate: String,
    @SerializedName("user_id")
    val userId: Any
)