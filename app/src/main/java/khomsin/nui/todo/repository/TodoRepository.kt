package khomsin.nui.todo.repository

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import khomsin.nui.todo.api.RetrofitInstance
import khomsin.nui.todo.database.TodoInstance
import khomsin.nui.todo.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodoRepository(private val context: Context, private val db: TodoInstance) {

    private val apiService = RetrofitInstance.API_SERVICE

    private val getRegisterResponseLiveData: MutableLiveData<RegisterModel.Response> = MutableLiveData()
    private val getLoginResponseLiveData: MutableLiveData<LoginModel.Response> = MutableLiveData()
    private val getAllTaskResponseLiveData: MutableLiveData<TaskModel> = MutableLiveData()
    private val getAddTaskModelResponseLiveData: MutableLiveData<AddTaskModel.Response> = MutableLiveData()
    private val getDeleteTaskModelResponseLiveData: MutableLiveData<DeleteTaskModel> = MutableLiveData()
    private val getUpdateTaskModelResponseLiveData: MutableLiveData<UpdateTaskModel.Response> = MutableLiveData()
    private val getLogOutTaskModelResponseLiveData: MutableLiveData<LogOutModel> = MutableLiveData()

    fun userRegister(data: RegisterModel.Request) {
        apiService.getRegisterResponse(data).enqueue(
            object : Callback<RegisterModel.Response> {
                override fun onResponse(
                    call: Call<RegisterModel.Response>,
                    model: Response<RegisterModel.Response>
                ) {
                    if (model.isSuccessful) {
                        getRegisterResponseLiveData.postValue(model.body())
                    } else {
                        Toast.makeText(context, model.message(), Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<RegisterModel.Response>, t: Throwable) {
                    getRegisterResponseLiveData.postValue(null)
                    Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                }
            }
        )
    }

    fun userLogin(data: LoginModel.Request) {
        apiService.getLoginResponse(data).enqueue(
            object : Callback<LoginModel.Response> {
                override fun onResponse(
                    call: Call<LoginModel.Response>,
                    model: Response<LoginModel.Response>
                ) {
                    if (model.isSuccessful) {
                        getLoginResponseLiveData.postValue(model.body())
                    } else {
                        Toast.makeText(context, model.message(), Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<LoginModel.Response>, t: Throwable) {
                    getLoginResponseLiveData.postValue(null)
                    Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                }
            }
        )
    }

    fun getAllTask(token: String?) {
        apiService.getAllTaskResponse(getHeader(token)).enqueue(
            object : Callback<TaskModel> {
                override fun onResponse(
                    call: Call<TaskModel>,
                    model: Response<TaskModel>
                ) {
                    if (model.isSuccessful) {
                        getAllTaskResponseLiveData.postValue(model.body())
                    } else {
                        Toast.makeText(context, model.message(), Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<TaskModel>, t: Throwable) {
                    getAllTaskResponseLiveData.postValue(null)
                    Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                }
            }
        )
    }

    fun getAddTask(token: String?, data: AddTaskModel.Request) {
        apiService.getAddTaskResponse(getHeader(token), data).enqueue(
            object : Callback<AddTaskModel.Response> {
                override fun onResponse(
                    call: Call<AddTaskModel.Response>,
                    model: Response<AddTaskModel.Response>
                ) {
                    if (model.isSuccessful) {
                        getAddTaskModelResponseLiveData.postValue(model.body())
                    } else {
                        Toast.makeText(context, model.message(), Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<AddTaskModel.Response>, t: Throwable) {
                    getAddTaskModelResponseLiveData.postValue(null)
                    Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                }
            }
        )
    }

    fun getDeleteTask(token: String?, id: String?) {
        apiService.getDeleteTaskResponse(getHeader(token), id).enqueue(
            object : Callback<DeleteTaskModel> {
                override fun onResponse(
                    call: Call<DeleteTaskModel>,
                    model: Response<DeleteTaskModel>
                ) {
                    if (model.isSuccessful) {
                        getDeleteTaskModelResponseLiveData.postValue(model.body())
                    } else {
                        Toast.makeText(context, model.message(), Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<DeleteTaskModel>, t: Throwable) {
                    getDeleteTaskModelResponseLiveData.postValue(null)
                    Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                }
            }
        )
    }

    fun getUpdateTask(token: String?, id: String?,data : UpdateTaskModel.Request) {
        apiService.getUpdateTaskResponse(getHeader(token), id,data).enqueue(
            object : Callback<UpdateTaskModel.Response> {
                override fun onResponse(
                    call: Call<UpdateTaskModel.Response>,
                    model: Response<UpdateTaskModel.Response>
                ) {
                    if (model.isSuccessful) {
                        getUpdateTaskModelResponseLiveData.postValue(model.body())
                    } else {
                        Toast.makeText(context, model.message(), Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<UpdateTaskModel.Response>, t: Throwable) {
                    getUpdateTaskModelResponseLiveData.postValue(null)
                    Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                }
            }
        )
    }

    fun getLogOut(token: String?) {
        apiService.getLogOutResponse(getHeader(token)).enqueue(
            object : Callback<LogOutModel> {
                override fun onResponse(
                    call: Call<LogOutModel>,
                    model: Response<LogOutModel>
                ) {
                    if (model.isSuccessful) {
                        getLogOutTaskModelResponseLiveData.postValue(model.body())
                    } else {
                        Toast.makeText(context, model.message(), Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<LogOutModel>, t: Throwable) {
                    getLogOutTaskModelResponseLiveData.postValue(null)
                    Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                }
            }
        )
    }

    fun getUserRegisterResult(): LiveData<RegisterModel.Response> {
        return getRegisterResponseLiveData
    }

    fun getUserLoginResult(): LiveData<LoginModel.Response> {
        return getLoginResponseLiveData
    }

    fun getAllTaskResult(): LiveData<TaskModel> {
        return getAllTaskResponseLiveData
    }

    fun getAddTaskResult(): LiveData<AddTaskModel.Response> {
        return getAddTaskModelResponseLiveData
    }

    fun getDeleteTaskResult(): LiveData<DeleteTaskModel> {
        return getDeleteTaskModelResponseLiveData
    }

    fun getUpdateTaskResult(): LiveData<UpdateTaskModel.Response> {
        return getUpdateTaskModelResponseLiveData
    }

    fun getLogOutResult(): LiveData<LogOutModel> {
        return getLogOutTaskModelResponseLiveData
    }

    suspend fun addUser(data: RegisterModel.Response.User) = db.getTodoDatabase().addUser(data)
    suspend fun deleteUser(data: RegisterModel.Response.User) = db.getTodoDatabase().deleteUser(data)

    fun getUser() = db.getTodoDatabase().getUser()

    private fun getHeader(token: String?): Map<String, String> {
        val headerMap = mutableMapOf<String, String>()
        headerMap["Authorization"] = "$token"
        return headerMap
    }
}