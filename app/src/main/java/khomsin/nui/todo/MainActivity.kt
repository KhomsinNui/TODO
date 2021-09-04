package khomsin.nui.todo

import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import khomsin.nui.todo.common.BaseActivity
import khomsin.nui.todo.database.TodoInstance
import khomsin.nui.todo.databinding.ActivityMainBinding
import khomsin.nui.todo.repository.TodoRepository
import khomsin.nui.todo.viewmodel.TodoViewModel
import khomsin.nui.todo.viewmodel.TodoViewModelProvider

class MainActivity : BaseActivity<ActivityMainBinding>() {

    lateinit var viewModel: TodoViewModel

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViewModel()
    }

    private fun setupViewModel() {

        val jobRepository = TodoRepository(this, TodoInstance(this))

        val viewModelProviderFactory = TodoViewModelProvider(application, jobRepository)

        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(TodoViewModel::class.java)

    }
}