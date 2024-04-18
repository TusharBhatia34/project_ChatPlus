package com.example.whatsappclone.di

import com.example.whatsappclone.data.repository.ChatScreenRepositoryImp
import com.example.whatsappclone.data.repository.phoneAuthRepositoryImp
import com.example.whatsappclone.domain.repository.ChatScreenRepository
import com.example.whatsappclone.domain.repository.PhoneAuthRepository
import com.example.whatsappclone.domain.usecases.ChatScreenUseCase
import com.example.whatsappclone.domain.usecases.GetOtpUseCase
import com.example.whatsappclone.domain.usecases.VerifyOtpUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providePhoneAuth():PhoneAuthRepository{
        return phoneAuthRepositoryImp()
    }
    @Provides
    @Singleton
    fun chatScreenRepository():ChatScreenRepository{
        return ChatScreenRepositoryImp()
    }



    @Provides
    @Singleton
    fun provideGetOtpUseCase(phoneAuthRepository: PhoneAuthRepository):GetOtpUseCase{
        return GetOtpUseCase(phoneAuthRepository)
    }
    @Provides
    @Singleton
    fun provideVerifyOtpUseCase(phoneAuthRepository: PhoneAuthRepository): VerifyOtpUseCase {
        return VerifyOtpUseCase(phoneAuthRepository)
    }
    @Provides
    @Singleton
    fun provideChatScreenUseCase(chatScreenRepository: ChatScreenRepository):ChatScreenUseCase{
        return ChatScreenUseCase(chatScreenRepository)
    }
}