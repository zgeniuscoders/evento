package cd.zgeniuscoders.eventos.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

abstract class Repository {
    protected open val collection = "users"

    protected fun getCollections(): CollectionReference {
        return FirebaseFirestore.getInstance().collection(this.collection)
    }

    open fun all(): CollectionReference {
        return getCollections()
    }
    open fun find(id: String): DocumentReference {
        return getCollections().document(id)
    }

    fun create(id:String,data: HashMap<String, Any>): Task<Void> {
        return getCollections().document(id).set(data)
    }

    fun detele(id: String){
        find(id).delete()
    }

    fun update(id:String, data: HashMap<String,Any>): Task<Void> {
        return find(id).update(data)
    }

}