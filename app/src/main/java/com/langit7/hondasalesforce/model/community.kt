package com.langit7.hondasalesforce.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class community(
    @SerializedName("address")
    val address: String,
    @SerializedName("city")
    val city: city,
    @SerializedName("community_detail")
    val communityDetail: List<Any>,
    @SerializedName("created_date")
    val createdDate: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("province")
    val province: province,
    @SerializedName("title")
    val title: String,
    @SerializedName("updated_date")
    val updatedDate: String,

    var isHeader: Boolean
) : Serializable



