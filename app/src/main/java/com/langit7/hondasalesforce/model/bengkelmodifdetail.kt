package com.langit7.hondasalesforce.model

import com.google.gson.annotations.SerializedName

data class bengkelmodifdetail(
    @SerializedName("email")
    val email: String,
    @SerializedName("phone")
    val phone: String
)