package com.langit7.hondasalesforce.model

import com.google.gson.annotations.SerializedName

data class bengkelmodiftype(
    @SerializedName("body")
    val body: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("image_description")
    val imageDescription: String,
    @SerializedName("name")
    val name: String
)