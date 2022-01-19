package com.langit7.hondasalesforce.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class dealer(
    @SerializedName("id")
    val id: String,
    @SerializedName("main_dealer")
    val mainDealer: String,
    @SerializedName("title")
    val title: String
):Serializable