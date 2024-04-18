package com.example.whatsappclone.domain.repository

import android.content.Context
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import io.getstream.chat.android.models.InitializationState
import kotlinx.coroutines.flow.StateFlow

interface ChatScreenRepository {

suspend fun toGetStateOfChatScreen(applicationContext: Context,currentUserId:String): StateFlow<InitializationState>

@OptIn(ExperimentalPermissionsApi::class)
suspend fun toGetContactsList(context: Context, permissionState: PermissionState):StateFlow<List<Triple<Long,String,String>>>
}