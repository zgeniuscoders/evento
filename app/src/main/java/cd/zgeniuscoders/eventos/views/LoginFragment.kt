package cd.zgeniuscoders.eventos.views

import android.content.Context
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
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

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
        val btnLogin: MaterialButton = binding.btnLogin

        val emailEdt : TextInputEditText = binding.edtEmail
        val passwordEdt : TextInputEditText = binding.edtPassword

        var hasError = true
        btnLogin.setOnClickListener {

            btnLogin.isEnabled = false

            val email = emailEdt.text
            val password = passwordEdt.text

            if (email!!.isEmpty()) {

                hasError = true
                emailLayout.isErrorEnabled = true
                emailLayout.error = getString(R.string.this_email_field_cannot_be_empty)
                btnLogin.isEnabled = true

            } else {
                btnLogin.isEnabled = false
                emailLayout.isErrorEnabled = false
                hasError = false
            }

            if (password!!.isEmpty()) {

                hasError = true
                passwordLayout.isErrorEnabled = true
                passwordLayout.error = getString(R.string.this_password_field_cannot_be_empty)
                btnLogin.isEnabled = true

            } else {
                btnLogin.isEnabled = false
                passwordLayout.isErrorEnabled = false
                hasError = false
                passwordEdt.setText("")
            }

            if (!hasError) {

                loginViewModel.login(email.toString(), password.toString())
                loginViewModel.isLogged.observe(viewLifecycleOwner) { isLogged ->
                    if (isLogged) {

                        val sharePref =
                            requireContext().getSharedPreferences("auth", Context.MODE_PRIVATE)
                        val sharePrefEditor = sharePref.edit()
                        sharePrefEditor.putBoolean("isLogged", true)
                        sharePrefEditor.apply()

                        Intent(requireContext(), MainActivity::class.java).apply {
                            startActivity(this)
                        }

                    }
                }

                loginViewModel.error.observe(viewLifecycleOwner) { error ->
                    btnLogin.isEnabled = true
                    Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
                }

            }else{
                btnLogin.isEnabled = true
            }


        }

        return binding.root
    }

}