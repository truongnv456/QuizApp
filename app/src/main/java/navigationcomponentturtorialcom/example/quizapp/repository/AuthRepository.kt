package navigationcomponentturtorialcom.example.quizapp.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AuthRepository() {
    //    val firebaseUserMutableLiveData = MutableLiveData<FirebaseUser>()
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun signUp(
        email: String, password: String,
        onComplete: (FirebaseUser?) -> Unit,
        onError: (String) -> Unit
    ) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val currentUser = firebaseAuth.currentUser
                    onComplete(currentUser)
                } else {
                    onError("Authentication failed")
                }
            }
    }

    fun signIn(
        email: String, password: String,
        onComplete: (FirebaseUser?) -> Unit,
        onError: (String) -> Unit
    ) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val currentUser = firebaseAuth.currentUser
                    onComplete(currentUser)
                } else {
                    onError("Authentication failed")
                }
            }
    }

    fun sendEmailToResetPassword(email: String?): Boolean {
        if (email != null && email.isNotEmpty()) {
            firebaseAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("SendEmailResetPassword", "Email sent.")
                    }
                }
            return true
        } else {
            return false
        }
    }


    fun signOut() {
        firebaseAuth.signOut()
    }
}


