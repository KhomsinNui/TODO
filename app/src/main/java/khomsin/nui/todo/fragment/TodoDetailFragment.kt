package khomsin.nui.todo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import khomsin.nui.todo.MainActivity
import khomsin.nui.todo.R
import khomsin.nui.todo.common.BaseFragment
import khomsin.nui.todo.databinding.FragmentTodoDetailBinding
import khomsin.nui.todo.databinding.FragmentTodoListBinding
import khomsin.nui.todo.model.AddTaskModel
import khomsin.nui.todo.model.TaskModel
import khomsin.nui.todo.viewmodel.TodoViewModel

class TodoDetailFragment : BaseFragment<FragmentTodoDetailBinding>() {

    private val args: TodoDetailFragmentArgs by navArgs()

    private var token: String? = null

    private var task: TaskModel.Data? = null

    private var viewModel: TodoViewModel? = null

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTodoDetailBinding
        get() = FragmentTodoDetailBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        token = args.token
        task = args.task

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        setEvent()
    }

    private fun setEvent(){
        binding.btnAction.setOnClickListener {
            if(isValidateDescription()){
                viewModel?.getAddTask(token,AddTaskModel.Request().apply {
                    this.description = binding.edtDescription.text.toString()
                })

                viewModel?.getAddTaskResult()?.observe(viewLifecycleOwner,{
                    it.data.let {
                        Toast.makeText(context, "Add Success", Toast.LENGTH_SHORT).show()
                        activity?.onBackPressed()
                    }
                })
            }
        }
    }

    private fun isValidateDescription() : Boolean{

        if(binding.edtDescription.text.toString().isEmpty()){
            Toast.makeText(context, "Please Enter Description", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}