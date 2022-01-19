package com.langit7.hondasalesforce.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class questionchoice(
    @SerializedName("body")
    val body: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("is_true")
    val isTrue: String,
    @SerializedName("title")
    val title: String
): Serializable
