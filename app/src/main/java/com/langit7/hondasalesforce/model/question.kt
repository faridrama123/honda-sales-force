package com.langit7.hondasalesforce.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class question(
    @SerializedName("answer_question")
    val answerQuestion: List<questionchoice>,
    @SerializedName("body")
    val body: String,
    @SerializedName("desc_type_question")
    val descTypeQuestion: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("quiz")
    val quiz: quiz,
    @SerializedName("title")
    val title: String,
    @SerializedName("type_question")
    val typeQuestion: String
):Serializable


