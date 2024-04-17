package cd.zgeniuscoders.eventos.views

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import cd.zgeniuscoders.eventos.R
import cd.zgeniuscoders.eventos.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater)

        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }


        binding.btnLogin.setOnClickListener {
            Intent(requireContext(), InterestActivity::class.java).apply {
                startActivity(this)
            }
        }

        return binding.root
    }

}