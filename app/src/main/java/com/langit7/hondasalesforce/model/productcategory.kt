package com.langit7.hondasalesforce.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class productcategory(
    @SerializedName("background_color")
    val backgroundColor: String,
    @SerializedName("created_date")
    val createdDate: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: Any,
    @SerializedName("name")
    val name: String,
    @SerializedName("order")
    val order: String,
    var lsProduct:ArrayList<product>,
    var included:Boolean
):Serializable{


    fun getOrder():Int{
        var i=99

        try {
            i=order.toInt()
        } catch (e: Exception) {
        }

        return i
    }
}