package com.example.whatsappclone.domain.usecases

import android.app.Activity
import com.example.whatsappclone.domain.repository.PhoneAuthRepository
import javax.inject.Inject

class GetOtpUseCase @Inject constructor(
    private val phoneAuthRepository: PhoneAuthRepository
) {
   suspend fun invoke(phoneNumber:String,activity: Activity){
     phoneAuthRepository.getPhoneOtp(phoneNumber,activity)
    }
}