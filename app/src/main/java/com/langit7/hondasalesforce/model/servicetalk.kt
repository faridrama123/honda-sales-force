package com.langit7.hondasalesforce.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class servicetalk(
    @SerializedName("id")
    val id: String,
    @SerializedName("body")
    var body: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("created_date")
    val createdDate: String,
    @SerializedName("updated_date")
    val updatedDate: String,
    @SerializedName("sort_num")
    val sortnum : String,
    @SerializedName("service_talk_detail")
    val service_talk_detail : List<service_talk_detail>
):Serializable



