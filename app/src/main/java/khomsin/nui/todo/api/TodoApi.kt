package khomsin.nui.todo.api

import khomsin.nui.todo.model.*
import retrofit2.Call
import retrofit2.http.*

interface TodoApi {

    @Headers("Content-Type: application/json")
    @POST("user/register")
    fun getRegisterResponse(@Body data: RegisterModel.Request): Call<RegisterModel.Response>

    @Headers("Content-Type: application/json")
    @POST("user/login")
    fun getLoginResponse(@Body data: LoginModel.Request): Call<LoginModel.Response>

    @Headers("Content-Type: application/json")
    @GET("task")
    fun getAllTaskResponse(@HeaderMap headers: Map<String, String>): Call<TaskModel>

    @Headers("Content-Type: application/json")
    @POST("task")
    fun getAddTaskResponse(@HeaderMap headers: Map<String, String>, @Body data: AddTaskModel.Request): Call<AddTaskModel.Response>

    @Headers("Content-Type: application/json")
    @DELETE("task/{id}")
    fun getDeleteTaskResponse(@HeaderMap headers: Map<String, String>, @Path("id") id: String?): Call<DeleteTaskModel>

    @Headers("Content-Type: application/json")
    @PUT("task/{id}")
    fun getUpdateTaskResponse(@HeaderMap headers: Map<String, String>, @Path("id") id: String?, @Body data: UpdateTaskModel.Request): Call<UpdateTaskModel.Response>

    @Headers("Content-Type: application/json")
    @POST("user/logout")
    fun getLogOutResponse(@HeaderMap headers: Map<String, String>): Call<LogOutModel>

}