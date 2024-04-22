package cd.zgeniuscoders.eventos.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cd.zgeniuscoders.eventos.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException

class RegisterViewModel: ViewModel() {

    private val userRepository = UserRepository()
    private val _isRegistered = MutableLiveData<Boolean>()
    private val _error = MutableLiveData<String>()

    private val mAuth = FirebaseAuth.getInstance()

    val isRegistered: LiveData<Boolean> = _isRegistered
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

}