package com.langit7.hondasalesforce.model

import com.google.gson.annotations.SerializedName

data class bengkelmodifservice(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)