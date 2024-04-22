package cd.zgeniuscoders.eventos.viewModel

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID

class UploadViewModel : ViewModel() {

    private val _error = MutableLiveData<String>()
    private val _imageUrl = MutableLiveData<String>()

    val error: LiveData<String> = _error
    val imageUrl: LiveData<String> = _imageUrl

    fun uploadImage(context: Context,img: Uri, path: String) {

        val fileName = UUID.randomUUID().toString() + ".jpg"
        val refStorage = FirebaseStorage
            .getInstance()
            .reference
            .child("$path/$fileName")


        refStorage.putFile(img)
            .addOnSuccessListener {
                it.storage.downloadUrl.addOnSuccessListener { image ->
                    _imageUrl.postValue(image!!.toString())
                }
            }.addOnFailureListener {
                _error.postValue("Something wrent wrong")
            }

    }

}