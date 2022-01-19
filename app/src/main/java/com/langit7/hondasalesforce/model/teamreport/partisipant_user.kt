package com.langit7.hondasalesforce.model.teamreport
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class partisipant_user(
    @SerializedName("id")
    val id: Int,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("profile_user")
    val profileUser: partisipant_user_profile,
): Serializable