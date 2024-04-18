package com.example.whatsappclone.domain.repository

import android.app.Activity

interface PhoneAuthRepository {
   suspend fun getPhoneOtp(phoneNumber:String, activity: Activity)
    fun verifyOtp(otp:String):Boolean
}