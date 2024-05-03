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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater)

        registerViewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        email = binding.edtEmail
        password = binding.edtPassword
        username = binding.edtUsername

        binding.btnRegister.setOnClickListener {

            registerViewModel.validator(binding,requireContext())
            registerViewModel.isValidated.observe(viewLifecycleOwner){isValid ->
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
        }

        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        return binding.root
    }


}