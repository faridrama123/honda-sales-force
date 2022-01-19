package com.langit7.hondasalesforce.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class city(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
): Serializable