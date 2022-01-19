package com.langit7.hondasalesforce.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class cuspartimage(
    @SerializedName("image")
    val image: String
): Serializable