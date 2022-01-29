
import com.google.gson.annotations.SerializedName

data class NosAudit (

    @SerializedName("title"     ) var title    : String? = null,
    @SerializedName("sub_title" ) var subTitle : String? = null,
    @SerializedName("data" ) var data : String? = null,
    @SerializedName("answer" ) var answer : Int? = null


)