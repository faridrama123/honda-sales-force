package com.langit7.hondasalesforce.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class product(
    @SerializedName("category")
    val category: productcategory,
    @SerializedName("color_type_product")
    val colorTypeProduct: List<productcolortype>,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image_feature")
    val imageFeature: String,
    @SerializedName("riding_comfort")
    val ridingComfort: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("is_new")
    val is_new: String,
    @SerializedName("variant_product")
    val variantProduct: List<productvariant>
):Serializable







