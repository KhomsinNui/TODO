package khomsin.nui.todo.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import khomsin.nui.todo.repository.TodoRepository

class TodoViewModelProvider(
    private val app: Application,
    private val todoRepository: TodoRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TodoViewModel(app,todoRepository) as T
    }
}