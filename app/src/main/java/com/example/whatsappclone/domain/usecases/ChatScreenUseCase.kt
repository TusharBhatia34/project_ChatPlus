package com.example.whatsappclone.domain.usecases

import android.content.Context
import com.example.whatsappclone.domain.repository.ChatScreenRepository
import io.getstream.chat.android.models.InitializationState
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class ChatScreenUseCase @Inject constructor(
    private val chatScreenRepository: ChatScreenRepository
) {
    suspend fun invoke(applicationContext:Context,currentUserId:String):StateFlow<InitializationState>{
        return chatScreenRepository.toGetStateOfChatScreen(applicationContext, currentUserId)
    }
}