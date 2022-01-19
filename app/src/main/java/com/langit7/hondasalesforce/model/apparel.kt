package com.langit7.hondasalesforce.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class apparel(
    @SerializedName("apparel_description")
    val apparelDescription: List<appareldesc>,
//    @SerializedName("apparel_detail")
//    val apparelDetail: List<Any>,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("size")
    val size: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("apparel_detail")
    val apparel_detail: MutableList<appareldetail>,
    @SerializedName("producttype")
    val type: String
):Serializable

