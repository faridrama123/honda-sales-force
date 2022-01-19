package com.langit7.hondasalesforce.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class productfeature(
    @SerializedName("body")
    val body: String,
    @SerializedName("created_date")
    val createdDate: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("product")
    val product: product,
    @SerializedName("title")
    val title: String,
    @SerializedName("color")
    val color: String,
    @SerializedName("label_color")
    val labelcolor: String,
    @SerializedName("text_align")
    val text_align: String,
    @SerializedName("image_align")
    val image_align: String,
    @SerializedName("customer_service_detail")
    val customer_service_detail: List<productfeature>,
    @SerializedName("complaint_handling_detail")
    val complaint_handling_detail: List<productfeature>

) : Serializable
