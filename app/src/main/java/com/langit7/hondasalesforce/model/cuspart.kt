package com.langit7.hondasalesforce.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class cuspart(
    @SerializedName("created_date")
    val createdDate: String,
    @SerializedName("customizing_parts_detail")
    val customizingPartsDetail: List<cuspartetail>,
    @SerializedName("customizing_parts_image")
    val customizingPartsImage: List<cuspartimage>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("title")
    val title: String
):Serializable



