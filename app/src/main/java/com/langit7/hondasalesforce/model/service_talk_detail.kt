package com.langit7.hondasalesforce.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class service_talk_detail (

    @SerializedName("id")
    val id : String,
    @SerializedName("title")
    val title : String,
    @SerializedName("body")
    var body : String,
    @SerializedName("sort_num")
    val sort_num : String
):Serializable