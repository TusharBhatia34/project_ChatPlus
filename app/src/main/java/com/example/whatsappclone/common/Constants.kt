package com.example.animelist.common

import android.content.Context
import com.example.whatsappclone.BuildConfig
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.logger.ChatLogLevel
import io.getstream.chat.android.offline.plugin.factory.StreamOfflinePluginFactory
import io.getstream.chat.android.state.plugin.config.StatePluginConfig
import io.getstream.chat.android.state.plugin.factory.StreamStatePluginFactory


object Constants {

    const val SIGN_IN_ROUTE="signInScreen"
    const val OTP_ROUTE="otpScreen"
    const val CHAT_LIST_ROUTE="chatListScreen"
    const val CHAT_ROUTE="chatScreen"
    fun chatClient(context: Context):ChatClient{
        val offlinePluginFactory = StreamOfflinePluginFactory(appContext = context)
        val statePluginFactory = StreamStatePluginFactory(config = StatePluginConfig(), appContext = context)


        // 2 - Set up the client for API calls and with the plugin for offline storage
        val chatClientInstance = ChatClient.Builder(BuildConfig.STREAM_CHAT_API_KEY, context)
            .withPlugins(offlinePluginFactory, statePluginFactory)
            .logLevel(ChatLogLevel.ALL) // Set to NOTHING in prod
            .build()
        return chatClientInstance

    }
}

