package com.langit7.hondasalesforce.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class province(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
): Serializable