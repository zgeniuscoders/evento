package cd.zgeniuscoders.eventos.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cd.zgeniuscoders.eventos.repository.UserRepository
import cd.zgeniuscoders.eventos.utilities.getCurrentUser

class InterestViewModel : ViewModel() {

    private val userRepository = UserRepository()
    private val _isUpdated = MutableLiveData<Boolean>()

    val isUpdated: LiveData<Boolean> = _isUpdated

    fun addInterest(interests: List<String>){
        val hashMap = hashMapOf<String,Any>("interests" to interests)
        userRepository.update(getCurrentUser()!!.uid, hashMap).addOnSuccessListener {
            _isUpdated.postValue(true)
        }
    }
}