package com.langit7.hondasalesforce.model.teamreport


import com.google.gson.annotations.SerializedName


data class MonitoringQualified (
    @SerializedName("total_qualified_regular"     ) var totalQualifiedRegular    : Int? = null,
    @SerializedName("total_non_qualified_regular" ) var totalNonQualifiedRegular : Int? = null,
    @SerializedName("total_qualified_wsp"         ) var totalQualifiedWsp        : Int? = null,
    @SerializedName("total_non_qualified_wsp"     ) var totalNonQualifiedWsp     : Int? = null,
    @SerializedName("total_qualified_bwsp"        ) var totalQualifiedBwsp       : Int? = null,
    @SerializedName("total_non_qualified_bwsp"    ) var totalNonQualifiedBwsp    : Int? = null,
    @SerializedName("total_qualified_sobat"       ) var totalQualifiedSobat      : Int? = null,
    @SerializedName("total_non_qualified_sobat"   ) var totalNonQualifiedSobat   : Int? = null
)