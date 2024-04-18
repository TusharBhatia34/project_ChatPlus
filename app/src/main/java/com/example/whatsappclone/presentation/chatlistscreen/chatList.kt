package com.example.whatsappclone.presentation.chatlistscreen

import android.Manifest
import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.firebase.auth.FirebaseAuth
import io.getstream.chat.android.compose.ui.theme.ChatTheme
import io.getstream.chat.android.models.InitializationState


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ChatList(applicationContext:Context,viewModel: ChatListViewModel,navController: NavController) {

    val permissionState = rememberPermissionState(permission = Manifest.permission.READ_CONTACTS)



    val contacts = viewModel.contacts.collectAsState()

     val currentUserId by remember {
            mutableStateOf(FirebaseAuth.getInstance().currentUser?.uid.toString())
        }
    viewModel.initialisation(currentUserId,applicationContext,permissionState)


    // Observe the client connection state
    val clientInitialisationState by viewModel.initializationState.collectAsState()
   ChatTheme {
        when (clientInitialisationState) {
            InitializationState.COMPLETE -> {
                Box(modifier   = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    Text(text = "ChannelScreen")
                }
         chatListUIScreen(contacts.value, navController = navController)
                /*   ChannelsScreen(
                    title = "Hello",
                    isShowingSearch = true,
                    onItemClick = { channel ->
                        TODO()
                    },
                    onBackPressed = { }
                )*/
            }
            InitializationState.INITIALIZING -> {


             Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                 CircularProgressIndicator()
             }
            }
            InitializationState.NOT_INITIALIZED -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    CircularProgressIndicator()
                }
            }
        }
    }
}