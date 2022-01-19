package com.langit7.hondasalesforce.model.teamreport
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class quizawal_detail(
    @SerializedName("quiz_name")
    val quizName: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("start_date")
    val startDate: String,
    @SerializedName("end_date")
    val endDate: String,
): Serializable