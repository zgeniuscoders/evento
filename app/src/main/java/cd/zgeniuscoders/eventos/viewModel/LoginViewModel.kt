package cd.zgeniuscoders.eventos.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cd.zgeniuscoders.eventos.models.User

class LoginViewModel : ViewModel() {

    private val _user = MutableLiveData<User>()
    private val _isLogged = MutableLiveData<Boolean>()
    private val _error = MutableLiveData<String>()

    val isLogged: LiveData<Boolean> = _isLogged
    val error: LiveData<String> = _error

    fun login(email: String, password: String){

    }

}