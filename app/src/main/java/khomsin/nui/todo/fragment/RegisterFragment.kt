package khomsin.nui.todo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import khomsin.nui.todo.MainActivity
import khomsin.nui.todo.R
import khomsin.nui.todo.common.BaseFragment
import khomsin.nui.todo.databinding.FragmentRegisterBinding
import khomsin.nui.todo.model.RegisterModel
import khomsin.nui.todo.viewmodel.TodoViewModel

class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {

    private var token: String? = null

    private var viewModel: TodoViewModel? = null

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRegisterBinding
        get() = FragmentRegisterBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        initial()
        setEvent()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("token", token)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        token = savedInstanceState?.getString("token")
    }

    private fun initial() {

        viewModel?.getUser()?.observe(viewLifecycleOwner, {
            if (token?.isNotEmpty() == true) {
                Toast.makeText(context, "Register Success", Toast.LENGTH_SHORT).show()
                view?.findNavController()?.navigate(RegisterFragmentDirections.actionRegisterFragmentToTodoListFragment(token))
            } else if (it.isNotEmpty()) {
                view?.findNavController()?.navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
            }
        })

        viewModel?.getUserRegisterResult()?.observe(viewLifecycleOwner, {
            it?.user?.let { model ->
                token = it.token
                viewModel?.addUser(model)
            }
        })
    }

    private fun setEvent() {
        binding.btnRegister.setOnClickListener {
            if (isValidateInformation()) {
                viewModel?.userRegister(RegisterModel.Request().apply {
                    this.age = binding.edtAge.text.toString().toInt()
                    this.email = binding.edtEmail.text.toString()
                    this.name = binding.edtName.text.toString()
                    this.password = binding.edtPassword.text.toString()
                })
            }
        }

        binding.btnLogin.setOnClickListener {
            view?.findNavController()?.navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
        }
    }

    private fun isValidateInformation(): Boolean {

        if (binding.edtName.text.toString().isEmpty()) {
            Toast.makeText(context, "Please Enter Name", Toast.LENGTH_SHORT).show()
            return false
        }

        if (binding.edtAge.text.toString().isEmpty()) {
            Toast.makeText(context, "Please Enter Age", Toast.LENGTH_SHORT).show()
            return false
        }

        if (binding.edtEmail.text.toString().isEmpty()) {
            Toast.makeText(context, "Please Enter Email", Toast.LENGTH_SHORT).show()
            return false
        }

        if (binding.edtPassword.text.toString().isEmpty()) {
            Toast.makeText(context, "Please Enter Password", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}