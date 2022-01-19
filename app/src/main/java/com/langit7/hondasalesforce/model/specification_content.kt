package com.langit7.hondasalesforce.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class specification_content (
    @SerializedName("body")
    val body: String
):Serializable