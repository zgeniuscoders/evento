package cd.zgeniuscoders.eventos.views

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import cd.zgeniuscoders.eventos.R
import cd.zgeniuscoders.eventos.databinding.FragmentLoginBinding
import cd.zgeniuscoders.eventos.viewModel.LoginViewModel

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater)

        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        val emailLayout = binding.layoutEdtEmail
        val passwordLayout = binding.layoutEdtPassword

        val email = binding.edtEmail.text
        val password = binding.edtPassword.text

        binding.btnLogin.setOnClickListener {

            if (email!!.isNotEmpty() && password!!.isNotEmpty()) {
                emailLayout.isErrorEnabled = false
                passwordLayout.isErrorEnabled = false

                loginViewModel.login(email.toString(), password.toString())
                loginViewModel.isLogged.observe(viewLifecycleOwner) { isLogged ->
                    if (isLogged) {
                        Intent(requireContext(), InterestActivity::class.java).apply {
                            startActivity(this)
                        }
                    }
                }

                loginViewModel.error.observe(viewLifecycleOwner) { error ->
                    Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
                }


            } else {
                emailLayout.isErrorEnabled = true
                emailLayout.error = getString(R.string.this_email_field_cannot_be_empty)

                passwordLayout.isErrorEnabled = true
                passwordLayout.error = getString(R.string.this_password_field_cannot_be_empty)

            }


        }

        return binding.root
    }

}