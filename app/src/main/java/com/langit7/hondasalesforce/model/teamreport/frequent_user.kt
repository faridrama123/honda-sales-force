package com.langit7.hondasalesforce.model.teamreport


import com.google.gson.annotations.SerializedName


data class FrequentUser (

    @SerializedName("id"           ) var id          : Int?    = null,
    @SerializedName("title"        ) var title       : String? = null,
    @SerializedName("user_id"      ) var userId      : UserId? = UserId(),
    @SerializedName("created_date" ) var createdDate : String? = null

)

data class UserId (

    @SerializedName("id"           ) var id          : Int?         = null,
    @SerializedName("first_name"   ) var firstName   : String?      = null,
    @SerializedName("last_name"    ) var lastName    : String?      = null,
    @SerializedName("username"     ) var username    : String?      = null,
    @SerializedName("email"        ) var email       : String?      = null,
    @SerializedName("profile_user" ) var profileUser : ProfileUser? = ProfileUser()

)


data class ProfileUser (

    @SerializedName("id"                       ) var id                     : Int?    = null,
    @SerializedName("hondaid"                  ) var hondaid                : String? = null,
    @SerializedName("position"                 ) var position               : String? = null,
    @SerializedName("phone"                    ) var phone                  : String? = null,
    @SerializedName("code"                     ) var code                   : String? = null,
    @SerializedName("total_point"              ) var totalPoint             : Int?    = null,
    @SerializedName("status_registration"      ) var statusRegistration     : String? = null,
    @SerializedName("medal_type"               ) var medalType              : String? = null,
    @SerializedName("chosen_medal_type"        ) var chosenMedalType        : String? = null,
    @SerializedName("dealer"                   ) var dealer                 : Dealer? = Dealer(),
    @SerializedName("image"                    ) var image                  : String? = null,
    @SerializedName("statusvaccine1"           ) var statusvaccine1         : String? = null,
    @SerializedName("statusvaccine2"           ) var statusvaccine2         : String? = null,
    @SerializedName("reasonvaccine1"           ) var reasonvaccine1         : String? = null,
    @SerializedName("reasonvaccine2"           ) var reasonvaccine2         : String? = null,
    @SerializedName("is_survey_rating"         ) var isSurveyRating         : String? = null,
    @SerializedName("token"                    ) var token                  : String? = null,
    @SerializedName("last_login_mobile"        ) var lastLoginMobile        : String? = null,
    @SerializedName("is_login"                 ) var isLogin                : String? = null,
    @SerializedName("desc_status_registration" ) var descStatusRegistration : String? = null,
    @SerializedName("parent_position"          ) var parentPosition         : String? = null,
    @SerializedName("desc_is_survey_rating"    ) var descIsSurveyRating     : String? = null,
    @SerializedName("desc_is_login"            ) var descIsLogin            : String? = null,
    @SerializedName("desc_medal_type"          ) var descMedalType          : String? = null,
    @SerializedName("desc_chosen_medal_type"   ) var descChosenMedalType    : String? = null

)




data class Dealer (

    @SerializedName("id"          ) var id         : Int?    = null,
    @SerializedName("title"       ) var title      : String? = null,
    @SerializedName("main_dealer" ) var mainDealer : String? = null

)