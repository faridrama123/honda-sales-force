package com.langit7.hondasalesforce.model.teamreport

import com.google.gson.annotations.SerializedName


data class PartisipantDetail (

    @SerializedName("quiz_name"                     ) var quizName                   : String? = null,
    @SerializedName("description"                   ) var description                : String? = null,
    @SerializedName("start_date"                    ) var startDate                  : String? = null,
    @SerializedName("end_date"                      ) var endDate                    : String? = null,
    @SerializedName("total_participant_regular"     ) var totalParticipantRegular    : Int?    = null,
    @SerializedName("total_non_participant_regular" ) var totalNonParticipantRegular : Int?    = null,
    @SerializedName("total_participant_wsp"         ) var totalParticipantWsp        : Int?    = null,
    @SerializedName("total_non_participant_wsp"     ) var totalNonParticipantWsp     : Int?    = null,
    @SerializedName("total_participant_bwsp"        ) var totalParticipantBwsp       : Int?    = null,
    @SerializedName("total_non_participant_bwsp"    ) var totalNonParticipantBwsp    : Int?    = null,
    @SerializedName("total_participant_sobat"       ) var totalParticipantSobat      : Int?    = null,
    @SerializedName("total_non_participant_sobat"   ) var totalNonParticipantSobat   : Int?    = null,
    @SerializedName("label_regular"                 ) var labelRegular               : String? = null,
    @SerializedName("label_regular_second"          ) var labelRegularSecond         : String? = null,
    @SerializedName("label_wsp"                     ) var labelWsp                   : String? = null,
    @SerializedName("label_wsp_second"              ) var labelWspSecond             : String? = null,
    @SerializedName("label_bwsp"                    ) var labelBwsp                  : String? = null,
    @SerializedName("label_bwsp_second"             ) var labelBwspSecond            : String? = null,
    @SerializedName("label_sobat"                   ) var labelSobat                 : String? = null,
    @SerializedName("label_sobat_second"            ) var labelSobatSecond           : String? = null,
    @SerializedName("is_show_regular"               ) var isShowRegular              : String? = null,
    @SerializedName("desc_is_show_regular"          ) var descIsShowRegular          : String? = null,
    @SerializedName("is_show_wsp"                   ) var isShowWsp                  : String? = null,
    @SerializedName("desc_is_show_wsp"              ) var descIsShowWsp              : String? = null,
    @SerializedName("is_show_bwsp"                  ) var isShowBwsp                 : String? = null,
    @SerializedName("desc_is_show_bwsp"             ) var descIsShowBwsp             : String? = null,
    @SerializedName("is_show_sobat"                 ) var isShowSobat                : String? = null,
    @SerializedName("desc_is_show_sobat"            ) var descIsShowSobat            : String? = null

)