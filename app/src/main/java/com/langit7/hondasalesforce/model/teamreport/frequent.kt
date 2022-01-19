package com.langit7.hondasalesforce.model.teamreport
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class frequent(
    @SerializedName("total_success_login")
    val totalSuccessLogin: Int,
    @SerializedName("total_failed_login")
    val totalFailedLogin: Int,
    @SerializedName("total_fu")
    val totalFu: Int,
    @SerializedName("total_nfu")
    val totalNfu: Int,
): Serializable