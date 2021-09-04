package khomsin.nui.todo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import khomsin.nui.todo.model.AddTaskModel
import khomsin.nui.todo.model.LoginModel
import khomsin.nui.todo.model.RegisterModel
import khomsin.nui.todo.model.UpdateTaskModel
import khomsin.nui.todo.repository.TodoRepository
import kotlinx.coroutines.launch

class TodoViewModel(
    app: Application,
    private val todoRepository: TodoRepository
) : AndroidViewModel(app) {

    fun userRegister(data : RegisterModel.Request) = todoRepository.userRegister(data)

    fun getUserRegisterResult() = todoRepository.getUserRegisterResult()

    fun userLogin(data : LoginModel.Request) = todoRepository.userLogin(data)

    fun getUserLoginResult() = todoRepository.getUserLoginResult()

    fun getAllTask(token : String?) = todoRepository.getAllTask(token)

    fun getAllTaskResult() = todoRepository.getAllTaskResult()

    fun getAddTask(token: String?,data : AddTaskModel.Request) = todoRepository.getAddTask(token,data)

    fun getAddTaskResult() = todoRepository.getAddTaskResult()

    fun getDeleteTask(token: String?,id : String?) = todoRepository.getDeleteTask(token,id)

    fun getDeleteTaskResult() = todoRepository.getDeleteTaskResult()

    fun getUpdateTask(token: String?,id : String?,data : UpdateTaskModel.Request) = todoRepository.getUpdateTask(token,id,data)

    fun getUpdateTaskResult() = todoRepository.getUpdateTaskResult()

    fun getLogOut(token: String?) = todoRepository.getLogOut(token)

    fun getLogOutResult() = todoRepository.getLogOutResult()

    fun addUser(data : RegisterModel.Response.User) = viewModelScope.launch {
        todoRepository.addUser(data)
    }

    fun deleteUser(data : RegisterModel.Response.User) = viewModelScope.launch {
        todoRepository.deleteUser(data)
    }

    fun getUser() = todoRepository.getUser()

}