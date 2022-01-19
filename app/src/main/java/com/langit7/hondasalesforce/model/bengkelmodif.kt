package com.langit7.hondasalesforce.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class bengkelmodif(
    @SerializedName("address")
    val address: String,
    @SerializedName("category")
    val category: bengkelmodiftype,
    @SerializedName("city")
    val city: city,
    @SerializedName("created_date")
    val createdDate: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("modification_workshop_detail")
    val modificationWorkshopDetail: List<bengkelmodifdetail>,
    @SerializedName("province")
    val province: province,
    @SerializedName("service_type")
    val serviceType: bengkelmodifservice,
    @SerializedName("title")
    val title: String,
    @SerializedName("updated_date")
    val updatedDate: String
):Serializable









