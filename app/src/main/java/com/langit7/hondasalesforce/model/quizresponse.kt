package com.langit7.hondasalesforce.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class quizresponse(
    @SerializedName("created_date")
    val createdDate: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("quiz")
    val quiz: quiz,
    @SerializedName("score")
    val score: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("is_pass_quiz")
    val ispassquiz: String,
    @SerializedName("desc_is_pass_quiz")
    val descispassquiz: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("total_point")
    val total_point: String
):Serializable

