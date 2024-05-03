package cd.zgeniuscoders.eventos.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cd.zgeniuscoders.eventos.R
import cd.zgeniuscoders.eventos.databinding.FragmentRegisterBinding
import cd.zgeniuscoders.eventos.repository.UserRepository
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException

class RegisterViewModel: ViewModel() {

    private val userRepository = UserRepository()
    private val _isRegistered = MutableLiveData<Boolean>()
    private val _error = MutableLiveData<String>()
    private val _errors = MutableLiveData<List<String>>()
    private var _isValidated = MutableLiveData<Boolean>()

    private val mAuth = FirebaseAuth.getInstance()

    val isRegistered: LiveData<Boolean> = _isRegistered
    val isValidated: LiveData<Boolean> = _isValidated
    val error: LiveData<String> = _error

    fun register(username:String,email:String,password:String){

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {

                val currentUser = mAuth.currentUser
                val uid = currentUser!!.uid

                val userMap = HashMap<String, Any>()
                userMap["id"] = uid
                userMap["username"] = username
                userMap["email"] = email
                userMap["photo"] = ""
                userMap["interests"] = emptyList<String>()

                userRepository.create(uid, userMap)
                _isRegistered.postValue(true)

            } else {
                _isRegistered.postValue(false)
                val exception = task.exception

                if (exception is FirebaseAuthException) {
                    val errorCode = exception.errorCode
                    when (errorCode) {
                        "ERROR_WEAK_PASSWORD" -> _error.postValue("Password is too weak.")
                        "ERROR_EMAIL_ALREADY_IN_USE" -> _error.postValue("Email address already in use.")
                        "ERROR_INVALID_EMAIL" -> _error.postValue("Invalid email address.")
                        else -> _error.postValue("An error occurred. Please try again later.")
                    }
                }else {
                    // Handle unknown exception
                    println("Sign up failed with unknown exception: ${exception!!.message}")
                }
            }
        }

    }

    fun validator(binding: FragmentRegisterBinding, context: Context){
        val email: TextInputEditText = binding.edtEmail
        val username: TextInputEditText = binding.edtUsername
        val password: TextInputEditText = binding.edtPassword

        val emailLayout: TextInputLayout = binding.layoutEdtEmail
        val passwordLayout: TextInputLayout = binding.layoutEdtPassword
        val usernameLayout: TextInputLayout = binding.layoutEdtUsername

        if (email.text!!.isEmpty()) {
            emailLayout.error = context.getString(R.string.this_email_field_cannot_be_empty)
            emailLayout.isErrorEnabled = true
            _isValidated.postValue(false)
        } else {
            _isValidated.postValue(true)
            emailLayout.isErrorEnabled = false
        }

        if (username.text!!.isEmpty()) {
            usernameLayout.error = context.getString(R.string.this_username_field_cannot_be_empty)
            usernameLayout.isErrorEnabled = true
            _isValidated.postValue(false)
        } else {
            _isValidated.postValue(true)
            usernameLayout.isErrorEnabled = false
        }

        if (password.text!!.isEmpty()) {
            passwordLayout.error = context.getString(R.string.this_password_field_cannot_be_empty)
            passwordLayout.isErrorEnabled = true
            _isValidated.postValue(false)
        } else {
            _isValidated.postValue(true)
            passwordLayout.isErrorEnabled = false
        }
    }
}