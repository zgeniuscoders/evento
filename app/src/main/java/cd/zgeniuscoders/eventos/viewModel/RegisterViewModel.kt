package cd.zgeniuscoders.eventos.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cd.zgeniuscoders.eventos.repository.UserRepository
import cd.zgeniuscoders.eventos.utilities.generateKey

class RegisterViewModel: ViewModel() {

    private val userRepository = UserRepository()
    private val _isRegistered = MutableLiveData<Boolean>()
    private val _error = MutableLiveData<String>()

    val isRegistered: LiveData<Boolean> = _isRegistered
    val error: LiveData<String> = _error

    fun register(username:String,email:String,password:String){
        val userMap = HashMap<String, Any>()
        userMap["username"] = username
        userMap["email"] = email
        userMap["photo"] = ""

        userRepository.create(generateKey("users"), userMap)
    }


}