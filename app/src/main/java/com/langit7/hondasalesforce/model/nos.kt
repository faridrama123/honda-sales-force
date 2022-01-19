package com.langit7.hondasalesforce.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class nos(
    @SerializedName("body")
    val body: String,
    @SerializedName("created_date")
    val createdDate: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("product")
    val product: product,
    @SerializedName("title")
    val title: String,
    @SerializedName("color")
    val color: String,
    @SerializedName("label_color")
    val labelcolor: String,
    @SerializedName("text_align")
    val text_align: String,
    @SerializedName("image_align")
    val image_align: String,
    @SerializedName("docs")
    val docs: String,
    @SerializedName("video_embed")
    val video_embed: String
) : Serializable
