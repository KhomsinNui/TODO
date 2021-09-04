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
import khomsin.nui.todo.databinding.FragmentLoginBinding
import khomsin.nui.todo.databinding.FragmentRegisterBinding
import khomsin.nui.todo.model.LoginModel
import khomsin.nui.todo.model.RegisterModel
import khomsin.nui.todo.viewmodel.TodoViewModel

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private var token: String? = null

    private var viewModel: TodoViewModel? = null

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate

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
            if (it.isNotEmpty() && token?.isNotEmpty() == true) {
                Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show()
                view?.findNavController()?.navigate(LoginFragmentDirections.actionLoginFragmentToTodoListFragment(token))
            }
        })
    }

    private fun setEvent() {
        binding.btnLogin.setOnClickListener {

            if (isValidateInformation()) {
                viewModel?.userLogin(LoginModel.Request().apply {
                    this.email = binding.edtEmail.text.toString()
                    this.password = binding.edtPassword.text.toString()
                })

                viewModel?.getUserLoginResult()?.observe(viewLifecycleOwner, {
                    it?.user?.let { model ->
                        token = it.token
                        viewModel?.addUser(model)
                    }
                })
            }
        }
    }

    private fun isValidateInformation(): Boolean {

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