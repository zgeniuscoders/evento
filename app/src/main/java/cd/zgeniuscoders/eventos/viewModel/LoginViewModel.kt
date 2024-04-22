package cd.zgeniuscoders.eventos.viewModel

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cd.zgeniuscoders.eventos.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException

class LoginViewModel : ViewModel() {

    private val _user = MutableLiveData<User>()
    private val _isLogged = MutableLiveData<Boolean>()
    private val _error = MutableLiveData<String>()

    private val mAuth = FirebaseAuth.getInstance()

    val isLogged: LiveData<Boolean> = _isLogged
    val error: LiveData<String> = _error

    fun login(email: String, password: String){

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener {task ->
            if (task.isSuccessful){
                _isLogged.postValue(true)
            }else{
                _isLogged.postValue(false)
                val exception = task.exception
                if (exception is FirebaseAuthException) {
                    val errorCode = exception.errorCode

                    when (errorCode) {
                        "ERROR_INVALID_EMAIL" -> {
                            _error.postValue("Invalid email address.")
                        }
                        "ERROR_WRONG_PASSWORD" -> {
                            _error.postValue("Incorrect password.")
                        }
                        "ERROR_USER_NOT_FOUND" -> {
                            _error.postValue("User not found.")
                        }
                        "ERROR_USER_DISABLED" -> {
                            _error.postValue("User account is disabled.")

                        }
                        else -> {
                            _error.postValue("\"An error occurred. Please try again later.\"")
                        }
                    }

                } else {
                    // Handle unknown exception
                    println("Sign in failed with unknown exception: ${exception!!.message}")
                }
            }
        }

    }

}