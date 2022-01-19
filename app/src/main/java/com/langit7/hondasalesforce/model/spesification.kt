package com.langit7.hondasalesforce.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class spesification(
    @SerializedName("id")
    val id: String,
    @SerializedName("product")
    val product: product,
    @SerializedName("specification_category")
    val specification_category: List<specification_category>,
    @SerializedName("title")
    val title: String
):Serializable