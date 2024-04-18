package com.example.whatsappclone


import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.whatsappclone.presentation.chatlistscreen.ChatList
import com.example.whatsappclone.presentation.chatlistscreen.ChatListViewModel
import com.example.whatsappclone.presentation.signinScreen.SignInViewModel
import com.example.whatsappclone.ui.theme.WhatsappCloneTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
val auth  = Firebase.auth
        setContent {

            WhatsappCloneTheme {

                val currentUser by remember {
                    mutableStateOf(auth.currentUser)
                }

                val signInViewModel = hiltViewModel<SignInViewModel>()
                val chatListViewModel = hiltViewModel<ChatListViewModel>()
                val activity: Activity = LocalContext.current as Activity

                val navController = rememberNavController()


               if (currentUser!=null){
                 ChatList(applicationContext = activity,chatListViewModel,navController)
                }
            else{

                    navGraph(navController = navController, signInViewModel, activity = activity,chatListViewModel)
               }

            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WhatsappCloneTheme {
    }
}