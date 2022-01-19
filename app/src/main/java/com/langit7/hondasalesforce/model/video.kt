package com.langit7.hondasalesforce.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class video(
    @SerializedName("category")
    val category: String,
    @SerializedName("created_date")
    val createdDate: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("post_date")
    val postDate: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("updated_date")
    val updatedDate: String,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("video_url")
    val videoUrl: String,
    @SerializedName("view_counter")
    val viewCounter: Int,
    @SerializedName("link_download")
    val linkDownload: String
) : Serializable


