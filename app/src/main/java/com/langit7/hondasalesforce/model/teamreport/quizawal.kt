package com.langit7.hondasalesforce.model.teamreport
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class quizawal(
    @SerializedName("title")
    val title: String,
    @SerializedName("semester")
    val semester: String,
    @SerializedName("year")
    val year: String,
    @SerializedName("quiz")
    val quiz: List<quizawal_detail>
): Serializable