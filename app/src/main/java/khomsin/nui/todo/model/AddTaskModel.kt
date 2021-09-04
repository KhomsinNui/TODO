package khomsin.nui.todo.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


class AddTaskModel {

    @Parcelize
    data class Request(
        @SerializedName("description")
        var description: String? = null
    ) : Parcelable

    @Parcelize
    data class Response(
        @SerializedName("data")
        var `data`: Data? = null,
        @SerializedName("success")
        var success: Boolean? = null
    ) : Parcelable {
        @Parcelize
        data class Data(
            @SerializedName("completed")
            var completed: Boolean? = null,
            @SerializedName("createdAt")
            var createdAt: String? = null,
            @SerializedName("description")
            var description: String? = null,
            @SerializedName("_id")
            var id: String? = null,
            @SerializedName("owner")
            var owner: String? = null,
            @SerializedName("updatedAt")
            var updatedAt: String? = null,
            @SerializedName("__v")
            var v: Int? = null
        ) : Parcelable
    }

}