package com.langit7.hondasalesforce.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class surveyquestion(
    @SerializedName("body")
    val body: String,
    @SerializedName("desc_type_question")
    val descTypeQuestion: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("survey")
    val survey: survey,
    @SerializedName("survey_answer_question")
    val surveyAnswerQuestion: List<questionchoice>,
    @SerializedName("title")
    val title: String,
    @SerializedName("type_question")
    val typeQuestion: String
): Serializable

