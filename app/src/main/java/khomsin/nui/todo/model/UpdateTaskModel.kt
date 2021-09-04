package khomsin.nui.todo.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

class UpdateTaskModel {

    @Parcelize
    data class Request(
        @SerializedName("completed")
        var completed: Boolean? = null
    ) : Parcelable

    @Parcelize
    data class Response(
        @SerializedName("message")
        var message: String? = null,
        @SerializedName("success")
        var success: Boolean? = null
    ) : Parcelable

}