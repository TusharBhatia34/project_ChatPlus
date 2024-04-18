package com.example.whatsappclone.data.repository

import android.app.Activity
import com.example.whatsappclone.domain.repository.PhoneAuthRepository
import com.google.firebase.Firebase
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.firestore
import java.util.concurrent.TimeUnit

class phoneAuthRepositoryImp:PhoneAuthRepository {
    private lateinit var storedVerificationId:String
   private val auth =FirebaseAuth.getInstance()
    override suspend fun getPhoneOtp(phoneNumber: String, activity: Activity) {


        val callbacks =object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override  fun onVerificationCompleted(credential: PhoneAuthCredential) {

//                credential.smsCode?.let { verifyOtp(it,phoneNumber) }
            }

            override fun onVerificationFailed(e: FirebaseException) {
                if (e is FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                } else if (e is FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                } else if (e is FirebaseAuthMissingActivityForRecaptchaException) {
                    // reCAPTCHA verification attempted with null Activity
                }
            }

            override fun onCodeSent(verificationId: String , token: PhoneAuthProvider.ForceResendingToken) {
                storedVerificationId = verificationId
                var resentToken = token
            }

        }

        var options =  PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber("+91"+ phoneNumber) // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(activity)
            .setCallbacks(callbacks)
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    override fun verifyOtp(otp:String):Boolean {
val credential = PhoneAuthProvider.getCredential(storedVerificationId,otp)
        auth.signInWithCredential(credential)
            .addOnCompleteListener{ task->
                if(task.isSuccessful){
                    val currentUser = FirebaseAuth.getInstance().currentUser
                    val userPhoneNumber = currentUser?.phoneNumber
                    val uid = currentUser?.uid
                    if (uid!=null){
                        val userData = hashMapOf(
                            "phoneNum" to userPhoneNumber
                        )
                        Firebase.firestore.collection("phone numbers").document(uid).set(userData)
                    }
                }

            }
        return storedVerificationId==otp
    }
}