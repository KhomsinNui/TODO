package khomsin.nui.todo.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UpdateTaskModel(
    @SerializedName("message")
    var message: String? = null,
    @SerializedName("success")
    var success: Boolean? = null
) : Parcelable