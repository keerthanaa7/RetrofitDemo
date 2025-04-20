import com.google.gson.annotations.SerializedName

data class AlbumItem(

    @SerializedName("id")
    val id: Int,

    val title: String,
    @SerializedName("userId")
    val userId: Int
)