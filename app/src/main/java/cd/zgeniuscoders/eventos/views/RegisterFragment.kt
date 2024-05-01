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
import cd.zgeniuscoders.eventos.databinding.FragmentRegisterBinding
import cd.zgeniuscoders.eventos.viewModel.RegisterViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel

    private lateinit var email: TextInputEditText
    private lateinit var username: TextInputEditText
    private lateinit var password: TextInputEditText

    private lateinit var emailLayout: TextInputLayout
    private lateinit var passwordLayout: TextInputLayout
    private lateinit var usernameLayout: TextInputLayout

    private var isValid: Boolean = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater)

        registerViewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        email = binding.edtEmail
        password = binding.edtPassword
        username = binding.edtUsername

        emailLayout = binding.layoutEdtEmail
        passwordLayout = binding.layoutEdtPassword
        usernameLayout = binding.layoutEdtUsername

        binding.btnRegister.setOnClickListener {

            validateFiled()

            if (isValid) {

                registerViewModel.register(
                    username.text.toString(),
                    email.text.toString(),
                    password.text.toString()
                )

                registerViewModel.isRegistered.observe(viewLifecycleOwner) { isRegistered ->
                    if (isRegistered) {
                        Intent(requireContext(), InterestActivity::class.java).apply {
                            startActivity(this)
                        }
                    }
                }

                registerViewModel.error.observe(viewLifecycleOwner) { error ->
                    Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
                }

            }
        }

        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        return binding.root
    }

    private fun validateFiled() {
        if (email.text!!.isEmpty()) {
            emailLayout.error = getString(R.string.this_email_field_cannot_be_empty)
            emailLayout.isErrorEnabled = true
            isValid = false
        } else {
            isValid = true
            emailLayout.isErrorEnabled = false
        }

        if (username.text!!.isEmpty()) {
            usernameLayout.error = getString(R.string.this_username_field_cannot_be_empty)
            usernameLayout.isErrorEnabled = true
            isValid = false
        } else {
            isValid = true
            usernameLayout.isErrorEnabled = false
        }

        if (password.text!!.isEmpty()) {
            passwordLayout.error = getString(R.string.this_password_field_cannot_be_empty)
            passwordLayout.isErrorEnabled = true
            isValid = false
        } else {
            isValid = true
            passwordLayout.isErrorEnabled = false
        }
    }

}