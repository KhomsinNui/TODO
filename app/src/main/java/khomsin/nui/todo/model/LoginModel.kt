package khomsin.nui.todo.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

class LoginModel {

    @Parcelize
    data class Request(
        @SerializedName("email")
        var email: String? = null,
        @SerializedName("password")
        var password: String? = null
    ) : Parcelable

    @Parcelize
    class Response : RegisterModel.Response(),Parcelable

}