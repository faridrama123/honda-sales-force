package com.langit7.hondasalesforce.model.teamreport
import com.google.gson.annotations.SerializedName


data class ListParticipantQualified (

    @SerializedName("total_score"                   ) var totalScore                 : Int?    = null,
    @SerializedName("total_score_product_knowledge" ) var totalScoreProductKnowledge : Int?    = null,
    @SerializedName("count_quiz_product_knowledge"  ) var countQuizProductKnowledge  : Int?    = null,
    @SerializedName("total_score_nos"               ) var totalScoreNos              : Int?    = null,
    @SerializedName("count_quiz_nos"                ) var countQuizNos               : Int?    = null,
    @SerializedName("total_quiz_nos"                ) var totalQuizNos               : Int?    = null,
    @SerializedName("total_score_survey"            ) var totalScoreSurvey           : Int?    = null,
    @SerializedName("desc_total_score_survey"       ) var descTotalScoreSurvey       : String? = null,
    @SerializedName("count_survey"                  ) var countSurvey                : Int?    = null,
    @SerializedName("is_survey"                     ) var isSurvey                   : Int?    = null,
    @SerializedName("avg_score_product_knowledge"   ) var avgScoreProductKnowledge   : Double? = null,
    @SerializedName("avg_score_nos"                 ) var avgScoreNos                : Double? = null,
    @SerializedName("avg_score_survey"              ) var avgScoreSurvey             : Int?    = null,
    @SerializedName("total_minimal_score"           ) var totalMinimalScore          : Int?    = null,
    @SerializedName("score_qualified"               ) var scoreQualified             : Double? = null,
    @SerializedName("is_qualified"                  ) var isQualified                : String? = null,
    @SerializedName("desc_is_qualified"             ) var descIsQualified            : String? = null,
    @SerializedName("user_id"                       ) var userId                     : Int?    = null,
    @SerializedName("fullname"                      ) var fullname                   : String? = null,
    @SerializedName("hondaid"                       ) var hondaid                    : String? = null,
    @SerializedName("position"                      ) var position                   : String? = null,
    @SerializedName("main_dealer"                   ) var mainDealer                 : String? = null,
    @SerializedName("calc"                          ) var calc                       : Int?    = null

)