package com.langit7.hondasalesforce.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class productvariant(
    @SerializedName("color")
    val color: String,
    @SerializedName("color_description")
    val colorDescription: String,
    @SerializedName("color_type")
    val colorType: productcolortype,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("title")
    val title: String
): Serializable