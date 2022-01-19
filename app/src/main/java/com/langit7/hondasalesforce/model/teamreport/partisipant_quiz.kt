package com.langit7.hondasalesforce.model.teamreport


import com.google.gson.annotations.SerializedName


data class PartisipantQuiz (

    @SerializedName("id"           ) var id          : Int?    = null,
    @SerializedName("title"        ) var title       : String? = null,
    @SerializedName("score"        ) var score       : Int?    = null,
    @SerializedName("user_id"      ) var userId      : PartisipantQuizUserId? = PartisipantQuizUserId(),
    @SerializedName("created_date" ) var createdDate : String? = null

)

data class PartisipantQuizProfileUser (

    @SerializedName("id"       ) var id       : Int?    = null,
    @SerializedName("hondaid"  ) var hondaid  : String? = null,
    @SerializedName("position" ) var position : String? = null

)
data class PartisipantQuizUserId (

    @SerializedName("id"           ) var id          : Int?         = null,
    @SerializedName("first_name"   ) var firstName   : String?      = null,
    @SerializedName("last_name"    ) var lastName    : String?      = null,
    @SerializedName("username"     ) var username    : String?      = null,
    @SerializedName("email"        ) var email       : String?      = null,
    @SerializedName("profile_user" ) var profileUser : PartisipantQuizProfileUser? = PartisipantQuizProfileUser()

)