package com.langit7.hondasalesforce.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class leaderboard(
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("point_total")
    val pointTotal: String,
    @SerializedName("user_position")
    val userPosition: String,
    @SerializedName("user_medal_type")
    val medal_type: String,
    @SerializedName("dealer_name")
    val dealer_name: String,
    @SerializedName("city_name")
    val city_name: String,
    @SerializedName("user_chosen_medal_type")
    val user_chosen_medal_type: String,
    @SerializedName("code_dealer")
    val code_dealer: String


):Serializable