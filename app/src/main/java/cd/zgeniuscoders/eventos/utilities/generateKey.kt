package cd.zgeniuscoders.eventos.utilities

import com.google.firebase.firestore.FirebaseFirestore
fun generateKey(collection: String): String {
    return FirebaseFirestore.getInstance().collection(collection).document().id
}
