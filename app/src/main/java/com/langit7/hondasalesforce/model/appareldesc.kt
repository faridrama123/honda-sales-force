package com.langit7.hondasalesforce.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class appareldesc(
    @SerializedName("body")
    val body: String,
    @SerializedName("id")
    val id: String
):Serializable