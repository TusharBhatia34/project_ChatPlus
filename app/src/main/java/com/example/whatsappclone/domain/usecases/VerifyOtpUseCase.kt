package com.example.whatsappclone.domain.usecases

import com.example.whatsappclone.domain.repository.PhoneAuthRepository
import javax.inject.Inject

class VerifyOtpUseCase @Inject constructor(
    private val phoneAuthRepository: PhoneAuthRepository
) {
  fun invoke(otp:String):Boolean{
       return phoneAuthRepository.verifyOtp(otp)
    }
}