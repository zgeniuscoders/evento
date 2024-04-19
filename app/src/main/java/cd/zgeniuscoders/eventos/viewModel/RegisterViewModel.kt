package cd.zgeniuscoders.eventos.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cd.zgeniuscoders.eventos.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth

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
                when (task.exception!!.message) {
                    "The email address is already in use by another account." -> {
                        _error.postValue("The email address is already in use by another account.")
                    }

                    "A network error (such as timeout, interrupted connection or unreachable host) has occurred." -> {
                        _error.postValue("A network error (such as timeout, interrupted connection or unreachable host) has occurred.")
                    }

                    else -> {
                        _error.postValue("Authentication failed")
                    }
                }
            }
        }

    }

}