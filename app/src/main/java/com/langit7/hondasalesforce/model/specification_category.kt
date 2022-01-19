package com.langit7.hondasalesforce.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class specification_category (
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("specification_content")
    val specification_content : List<specification_content>
):Serializable