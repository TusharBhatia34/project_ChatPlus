package com.example.whatsappclone.presentation.chatlistscreen

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whatsappclone.domain.usecases.ChatScreenUseCase
import com.example.whatsappclone.domain.usecases.ContactListUseCase
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.getstream.chat.android.models.InitializationState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
@OptIn(ExperimentalPermissionsApi::class)
@HiltViewModel
class ChatListViewModel @Inject constructor (
   private val chatScreenUseCase: ChatScreenUseCase,
    private val contactListUseCase: ContactListUseCase

):ViewModel () {
    private val _initializationState = MutableStateFlow(InitializationState.INITIALIZING) // Use a MutableStateFlow to hold the collected value
    private val _contacts:MutableStateFlow<List<Triple<Long,String,String>>> = MutableStateFlow(emptyList()) // Use a MutableStateFlow to hold the collected value


    lateinit var currentUserId:String
    lateinit var context:Context
    lateinit var permissionState: PermissionState

    fun initialisation(userId:String,appContext: Context,permissionState: PermissionState){
        currentUserId = userId
        context = appContext
        this.permissionState = permissionState
    }


    var initializationState:StateFlow<InitializationState> = _initializationState.flatMapLatest {
        chatScreenUseCase.invoke(context,currentUserId)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(),InitializationState.NOT_INITIALIZED)

    var contacts: StateFlow<List<Triple<Long, String, String>>> = _contacts.flatMapLatest {
        contactListUseCase.invoke(context,permissionState)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

}