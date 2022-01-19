package com.langit7.hondasalesforce.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class productcolortype(
    @SerializedName("created_date")
    val createdDate: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String
): Serializable