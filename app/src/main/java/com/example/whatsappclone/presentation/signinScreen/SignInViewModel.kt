package com.example.whatsappclone.presentation.signinScreen

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whatsappclone.domain.usecases.GetOtpUseCase
import com.example.whatsappclone.domain.usecases.VerifyOtpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
   private val getOtpUseCase: GetOtpUseCase,
   private val verifyOtpUseCase: VerifyOtpUseCase

): ViewModel() {



   fun getOtpCase(phoneNum:String,activity: Activity){
viewModelScope.launch {
   getOtpUseCase.invoke(phoneNum,activity)
}
   }
   fun verifyOtp(otp:String):Boolean{
       return verifyOtpUseCase.invoke(otp)
   }
}
