package com.langit7.hondasalesforce.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class cuspartetail(
    @SerializedName("body")
    val body: String,
    @SerializedName("title")
    val title: String
): Serializable