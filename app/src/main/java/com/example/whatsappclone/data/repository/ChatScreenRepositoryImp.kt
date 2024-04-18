package com.example.whatsappclone.data.repository

import android.content.Context
import android.database.ContentObserver
import android.provider.ContactsContract
import com.example.animelist.common.Constants
import com.example.whatsappclone.domain.repository.ChatScreenRepository
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import io.getstream.chat.android.models.InitializationState
import io.getstream.chat.android.models.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class ChatScreenRepositoryImp: ChatScreenRepository {

    override suspend fun toGetStateOfChatScreen(applicationContext: Context, currentUserId:String): StateFlow<InitializationState>{
     val client = Constants.chatClient(applicationContext)

        val user = User(id = currentUserId)


        val token = client.devToken(currentUserId)

        client.connectUser(user,token).enqueue()
        val clientInitialisationState = client.clientState.initializationState
        // Observe the client connection state
return  clientInitialisationState

    }

    @OptIn(ExperimentalPermissionsApi::class)
    override suspend fun toGetContactsList(
        context: Context,
        permissionState: PermissionState,
    ):StateFlow<List<Triple<Long,String,String>>> {
        val _contacts = MutableStateFlow<List<Triple<Long, String, String>>>(emptyList())

               if (permissionState.status.isGranted) {
                                    // Initial contact retrieval
                   _contacts.value = refreshContacts(context)
                   // Observe contact changes using ContentObserver
                   val contentObserver = object : ContentObserver(null) {
                       override fun onChange(selfChange: Boolean) {
                           _contacts.value = refreshContacts(context)
                       }
                   }

                   context.contentResolver.registerContentObserver(
                       ContactsContract.Contacts.CONTENT_URI,
                       true,
                       contentObserver
                   )
                }

        return _contacts.asStateFlow()
    }

    private fun refreshContacts(context: Context):List<Triple<Long,String,String>> {
        val list =  mutableListOf<Triple<Long, String, String>>()

            val projection = arrayOf(
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
            )
            context.contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                projection,
                null,
                null,
                null
            )?.use { cursor->
                val idColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)
                val nameColumnIndex = cursor.getColumnIndex( ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                val numberColumnIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                while (cursor.moveToNext()){
                    val id =  cursor.getLong(idColumnIndex)
                    val name = cursor.getString(nameColumnIndex)
                    val number = cursor.getString(numberColumnIndex)
                    val digitOnly = number.filter { it.isDigit() }
                    if (digitOnly.isNotBlank()){
                        list.add(Triple(id,name,number))
                    }
                }
            }
        
        return list
    }
   /*     val list =  _contacts.value.toMutableList()
        if(permissionState.status.isGranted){
            Log.d("ccc","granted")
            val projection = arrayOf(
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
            )
            context.contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                projection,
                null,
                null,
                null
            )?.use { cursor->
                val idColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)
                val nameColumnIndex = cursor.getColumnIndex( ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                val numberColumnIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                while (cursor.moveToNext()){
                    val id =  cursor.getLong(idColumnIndex)
                    val name = cursor.getString(nameColumnIndex)
                    val number = cursor.getString(numberColumnIndex)
                    Log.d("ddd",id.toString())
                    Log.d("ddd",name.toString())
                    Log.d("ddd",number.toString())
                    val digitOnly = number.filter { it.isDigit() }
                    if (digitOnly.isNotBlank()){
                        list.add(Triple(id,name,number))
                    }
                }
            }
            Log.d("ddd",list.size.toString())
        }
        _contacts.value = list
        val contacts = _contacts.asStateFlow()
        Log.d("contactsList",contacts.value.joinToString())
        return contacts
    }*/
}