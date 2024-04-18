package com.example.whatsappclone

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.animelist.common.Constants
import com.example.whatsappclone.presentation.chatScreen.chatScreen
import com.example.whatsappclone.presentation.chatlistscreen.ChatList
import com.example.whatsappclone.presentation.chatlistscreen.ChatListViewModel
import com.example.whatsappclone.presentation.signinScreen.SignInViewModel
import com.example.whatsappclone.presentation.signinScreen.otp
import com.example.whatsappclone.presentation.signinScreen.signIn

@Composable
fun navGraph(navController: NavHostController, signInViewModel: SignInViewModel,activity: Activity,chatListViewModel:ChatListViewModel) {
    NavHost(navController = navController, startDestination = Constants.SIGN_IN_ROUTE){
        composable(route = Constants.SIGN_IN_ROUTE){
signIn(viewModel = signInViewModel , activity = activity,navController)

        }
        composable(route = Constants.OTP_ROUTE){
            otp(viewModel = signInViewModel,navController)
        } 
        composable(route = Constants.CHAT_LIST_ROUTE){
           ChatList(applicationContext = activity,chatListViewModel,navController)
        }
        composable(route = Constants.CHAT_ROUTE){
           chatScreen(context = activity)
        }
    }
}