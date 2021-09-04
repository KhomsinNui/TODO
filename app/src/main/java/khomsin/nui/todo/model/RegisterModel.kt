package khomsin.nui.todo.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

class RegisterModel {

    @Parcelize
    data class Request(
        @SerializedName("age")
        var age: Int? = null,
        @SerializedName("email")
        var email: String? = null,
        @SerializedName("name")
        var name: String? = null,
        @SerializedName("password")
        var password: String? = null
    ) : Parcelable

    @Parcelize
    open class Response(
        @SerializedName("token")
        var token: String? = null,
        @SerializedName("user")
        var user: User? = null
    ) : Parcelable {

        @Entity(tableName = "tbUser")
        @Parcelize
        data class User(
            @PrimaryKey(autoGenerate = true)
            @SerializedName("generate_id")
            var generateId: Int = 0,
            @SerializedName("age")
            var age: Int? = null,
            @SerializedName("createdAt")
            var createdAt: String? = null,
            @SerializedName("email")
            var email: String? = null,
            @SerializedName("_id")
            var id: String? = null,
            @SerializedName("name")
            var name: String? = null,
            @SerializedName("updatedAt")
            var updatedAt: String? = null,
            @SerializedName("__v")
            var v: Int? = null
        ) : Parcelable
    }
}