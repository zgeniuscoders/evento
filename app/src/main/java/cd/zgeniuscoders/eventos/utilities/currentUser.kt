package cd.zgeniuscoders.eventos.utilities

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

fun getCurrentUser(): FirebaseUser? {
    return FirebaseAuth.getInstance().currentUser
}