package com.langit7.hondasalesforce.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class grooming(
    @SerializedName("created_date")
    val createdDate: String,
    @SerializedName("grooming_sub_category")
    val groomingSubCategory: List<GroomingSubCategory>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String
):Serializable

data class GroomingSubCategory(
    @SerializedName("grooming_sub_category_grooming")
    val groomingSubCategoryGrooming: List<GroomingSubCategoryGrooming>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String
):Serializable

data class GroomingSubCategoryGrooming(
    @SerializedName("body")
    val body: String,
    @SerializedName("desc_image_align")
    val descImageAlign: String,
    @SerializedName("desc_text_align")
    val descTextAlign: String,
    @SerializedName("grooming_detail")
    val groomingDetail: List<GroomingDetail>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("image_align")
    val imageAlign: String,
    @SerializedName("order")
    val order: Int,
    @SerializedName("text_align")
    val textAlign: String,
    @SerializedName("title")
    val title: String
):Serializable

data class GroomingDetail(
    @SerializedName("body")
    val body: String,
    @SerializedName("created_date")
    val createdDate: String,
    @SerializedName("desc_image_align")
    val descImageAlign: String,
    @SerializedName("desc_text_align")
    val descTextAlign: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("image_align")
    val imageAlign: String,
    @SerializedName("text_align")
    val textAlign: String
):Serializable