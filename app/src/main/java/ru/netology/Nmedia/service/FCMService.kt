package ru.netology.Nmedia.service
/*
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import ru.netology.Nmedia.R
import kotlin.random.Random

class FCMService : FirebaseMessagingService() {

    private val channelId = "netology"
    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_remote_name)
            val descriptionText = getString(R.string.channel_remote_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        println(Gson().toJson(remoteMessage))
        remoteMessage.data["action"]?.let {
            when (Action.valueOf(it)) {
                Action.LIKE -> handleLike(
                    Gson().fromJson(
                        remoteMessage.data["content"],
                        Like::class.java
                    )
                )
            }

        }
    }

    private fun handleLike(like: Like) {
        val notification = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_shining_background)
            .setContentTitle(
                getString(
                    R.string.notification_user_liked, like.userName, like.postAuthor,
                )
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        NotificationManagerCompat.from(this)
            .notify(Random.nextInt(100_000), notification)
    }


    override fun onNewToken(token: String) {
        println(token)
    }

}

enum class Action {
    LIKE
}

data class Like(
    val userId: Int,
    val userName: String,
    val postId: Int,
    val postAuthor: String
)
*/
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import ru.netology.Nmedia.R
import kotlin.random.Random


class FCMService : FirebaseMessagingService() {
    private val action = "action"
    private val content = "content"
    private val channelId = "remote"
    private val gson = Gson()

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_remote_name)
            val descriptionText = getString(R.string.channel_remote_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    override fun onMessageReceived(message: RemoteMessage) {


        try {
            message.data[action]?.let {
                /* if (Action.valueOf(it) != Action.LIKE || Action.valueOf(it) != Action.TEXT) {
                error()

            }*/
                when (Action.valueOf(it)) {
                    Action.LIKE ->
                        handleLike(gson.fromJson(message.data[content], Like::class.java))

                    Action.TEXT ->
                        handleText(gson.fromJson(message.data[content], hanText::class.java))


                }
            }
        } catch (e: Throwable) {
            //error()
            //можно выдавать ошибку об обновлении
            return
        }
    }

    override fun onNewToken(token: String) {
        println(token)
    }

     private fun handleLike(content: Like) {
        val notification = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(
                getString(
                    R.string.notification_user_liked,
                    content.userName,
                    content.postAuthor,
                )
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        NotificationManagerCompat.from(this)
            .notify(Random.nextInt(100_000), notification)
    }

    private fun handleText(content: hanText) {
        var notification = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(content.title)
            .setContentText(content.minText)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(content.contentText)
            )
            .setDefaults(Notification.DEFAULT_SOUND)
            .build()

        NotificationManagerCompat.from(this)
            .notify(Random.nextInt(100_000), notification)

    }

    private fun error() {
        var notification = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(getString(R.string.title_error))
            .setContentText(getString(R.string.content_text_error))
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(getString(R.string.catch_error))
            )
            .setDefaults(Notification.DEFAULT_SOUND)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(this)
            .notify(Random.nextInt(100_000), notification)
    }
}


enum class Action {
    LIKE, TEXT
}

data class Like(
    val userId: Long,
    val userName: String,
    val postId: Long,
    val postAuthor: String,
)

data class hanText(
    val authorName: String,
    val title: String,
    val contentText: String,
    val minText: String
)