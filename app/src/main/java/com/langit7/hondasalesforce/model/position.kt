package com.langit7.hondasalesforce.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class position(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("created_date")
    val createddate: String,
    @SerializedName("updated_date")
    val updateddate: String
): Serializable