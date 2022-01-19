package com.langit7.hondasalesforce.model

import com.google.gson.annotations.SerializedName

open class baseresponse<T>{
    @SerializedName("data") val data: T
    @SerializedName("message") val message: String
    @SerializedName("statuscode") val statuscode: Int
    @SerializedName("xs") val xs: String

    constructor(data: T, message: String, statuscode: Int, xs: String) {
        this.data = data
        this.message = message
        this.statuscode = statuscode
        this.xs = xs
    }
}