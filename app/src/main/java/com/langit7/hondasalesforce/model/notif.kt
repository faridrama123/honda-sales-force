package com.langit7.hondasalesforce.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class notif(
//    @SerializedName("article")
//    val article: article,
    @SerializedName("body")
    val body: String,
    @SerializedName("button_label")
    val buttonLabel: String,
    @SerializedName("created_date")
    val createdDate: String,
    @SerializedName("desc_type_notif")
    val descTypeNotif: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("is_read")
    var isRead: String,
    @SerializedName("product")
    val product: product,
    @SerializedName("redirect_url")
    val redirectUrl: String,
    @SerializedName("sender")
    val sender: Int,
    @SerializedName("sharetext")
    val sharetext: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("type_notif")
    val typeNotif: String,
    @SerializedName("updated_date")
    val updatedDate: String,
    @SerializedName("action_type")
    val actiontype: String
):Serializable

