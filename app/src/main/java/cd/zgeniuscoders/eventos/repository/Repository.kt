package cd.zgeniuscoders.eventos.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

abstract class Repository {
    protected val collection = "users"

    private fun getCollections(): CollectionReference {
        return FirebaseFirestore.getInstance().collection(this.collection)
    }

    fun all(): CollectionReference {
        return getCollections()
    }
    fun find(id: String): DocumentReference {
        return getCollections().document(id)
    }

    fun create(id:String,data: HashMap<String, Any>): Task<Void> {
        return getCollections().document(id).set(data)
    }

    fun detele(id: String){
        find(id).delete()
    }

    fun update(id:String, data: HashMap<String,Any>){
        find(id).update(data)
    }

}