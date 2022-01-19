package com.langit7.hondasalesforce.model
import com.google.gson.annotations.SerializedName


data class quizhistorydetail(
    @SerializedName("answer")
    val answer: questionchoice,
    @SerializedName("created_date")
    val createdDate: String,
    @SerializedName("essay_answer")
    val essayAnswer: String,
    @SerializedName("is_true")
    val isTrue: String,
    @SerializedName("question")
    val question: questionhistory,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("user_quiz_answer")
    val userQuizAnswer: Int
)

