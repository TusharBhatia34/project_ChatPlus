package com.example.whatsappclone.domain.usecases

import android.content.Context
import com.example.whatsappclone.domain.repository.ChatScreenRepository
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class ContactListUseCase @Inject constructor(
    private val chatScreenRepository: ChatScreenRepository
) {
    @OptIn(ExperimentalPermissionsApi::class)
    suspend fun invoke(
        context: Context,
        permissionState: PermissionState
    ) :StateFlow<List<Triple<Long,String,String>>>{
        return chatScreenRepository.toGetContactsList(context,permissionState)
    }
}