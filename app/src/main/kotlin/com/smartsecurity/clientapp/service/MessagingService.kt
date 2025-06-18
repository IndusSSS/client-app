package com.smartsecurity.clientapp.service

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
        // Handle FCM message
    }
}
