package khomsin.nui.todo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import khomsin.nui.todo.MainActivity
import khomsin.nui.todo.R
import khomsin.nui.todo.adapter.TodoListAdapter
import khomsin.nui.todo.common.BaseFragment
import khomsin.nui.todo.databinding.FragmentLoginBinding
import khomsin.nui.todo.databinding.FragmentTodoListBinding
import khomsin.nui.todo.viewmodel.TodoViewModel
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.snackbar.Snackbar
import khomsin.nui.todo.model.TaskModel
import khomsin.nui.todo.model.UpdateTaskModel


class TodoListFragment : BaseFragment<FragmentTodoListBinding>() {

    private val args: TodoListFragmentArgs by navArgs()

    private var token: String? = null

    private var viewModel: TodoViewModel? = null

    private lateinit var todoListAdapter: TodoListAdapter

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTodoListBinding
        get() = FragmentTodoListBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        token = args.token
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        initial()
        setupRecyclerView()
        setEvent()
    }

    private fun initial(){
        viewModel?.getAllTaskResult()?.observe(viewLifecycleOwner, {
            if (it?.data != null) {
                todoListAdapter.differ.submitList(it.data)
            }
        })
    }

    private fun setEvent(){
        binding.ftnAdd.setOnClickListener {
            view?.findNavController()?.navigate(TodoListFragmentDirections.actionTodoListFragmentToTodoDetailFragment(token))
        }

        binding.btnLogOut.setOnClickListener {
            viewModel?.getLogOut(token)
            viewModel?.getLogOutResult()?.observe(viewLifecycleOwner, {
                if (it.success == true) {
                    view?.findNavController()?.navigate(TodoListFragmentDirections.actionTodoListFragmentToLoginFragment())
                }
            })
        }
    }

    private fun setupRecyclerView() {

        todoListAdapter = TodoListAdapter(object : TodoListAdapter.SetOnItemClickListener{
            override fun onItemDelete(data: TaskModel.Data) {
                activity?.let {
                    AlertDialog.Builder(it).apply {
                        setTitle("Delete Task")
                        setMessage("Are you sure want to delete this Task?")
                        setPositiveButton(
                            "Delete"
                        ) { p0, p1 ->
                            viewModel?.getDeleteTask(token,data.id)
                            viewModel?.getDeleteTaskResult()?.observe(viewLifecycleOwner, {
                                if (it.success == true) {
                                    Toast.makeText(context, "Delete Success", Toast.LENGTH_SHORT).show()
                                    viewModel?.getAllTask(token)
                                }
                            })
                        }
                        setNegativeButton("Cancel", null)
                    }.create().show()
                }
            }

            override fun onItemUpdate(data: TaskModel.Data) {
                activity?.let {
                    AlertDialog.Builder(it).apply {
                        setTitle("Update Task")
                        setMessage("Are you sure want to Update this Task?")
                        setPositiveButton(
                            "Update"
                        ) { p0, p1 ->
                            viewModel?.getUpdateTask(token,data.id, UpdateTaskModel.Request().apply {
                                this.completed = true
                            })
                            viewModel?.getUpdateTaskResult()?.observe(viewLifecycleOwner, {
                                if (it.success == true) {
                                    Toast.makeText(context, "Update Success", Toast.LENGTH_SHORT).show()
                                    viewModel?.getAllTask(token)
                                }
                            })
                        }
                        setNegativeButton("Cancel", null)
                    }.create().show()
                }
            }
        })

        binding.rvList.apply {
            layoutManager = LinearLayoutManager(activity)

            addItemDecoration(object : DividerItemDecoration(context, LinearLayout.VERTICAL) {})

            setHasFixedSize(true)
            adapter = todoListAdapter
        }

        viewModel?.getAllTask(token)
    }

}