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
    @SerializedName("total_non_participant_sobat"   ) var totalNonParticipantSobat   : Int?    = null

)