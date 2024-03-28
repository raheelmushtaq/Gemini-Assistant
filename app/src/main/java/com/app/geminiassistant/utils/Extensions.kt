package com.app.geminiassistant.utils

import android.content.ContentResolver
import android.content.Context
import android.content.pm.ApplicationInfo
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.util.Base64
import com.app.geminiassistant.presentation.screens.chats.data.ChatMessage
import com.app.geminiassistant.presentation.screens.newchat.data.Message
import kotlinx.collections.immutable.toPersistentList
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun addDelay(delay: Long, action: Completion) {
    val handler = Handler(Looper.getMainLooper())

    handler.postDelayed({

        action.invoke()
        handler.removeCallbacksAndMessages(null)
    }, delay)
}

fun ApplicationInfo.getAppName(context: Context) = loadLabel(context.packageManager).toString()


fun ContentResolver.uriToBitmap(uri: Uri): Bitmap? {
    return try {
        val inputStream = openInputStream(uri)
        BitmapFactory.decodeStream(inputStream)
    } catch (e: Exception) {
        null
    }
}


fun Bitmap.getBase64(): String? {
    val byteArrayOutputStream = ByteArrayOutputStream()
    // You can choose PNG for lossless compression (better quality, larger size)
    // or JPEG for lossy compression (smaller size, potential quality loss)
    compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream) // adjust quality as needed
    val byteArray = byteArrayOutputStream.toByteArray()
    return Base64.encodeToString(byteArray, Base64.DEFAULT)
}

fun String.toBitmap(): Bitmap? {
    val decodedBytes: ByteArray? = try {
        // Handle potential decoding errors gracefully
        Base64.decode(this, Base64.DEFAULT)
    } catch (e: IllegalArgumentException) {
        e.printStackTrace()
        null
    }

    return decodedBytes?.let {
        BitmapFactory.decodeByteArray(it, 0, it.size)
    }
}

fun Long.toDate(): String {
    try {
        return SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(this)
    } catch (ex: Exception) {
        return Date(this).toString()
    }
}

fun List<Message>.toChatMessage(): List<ChatMessage> {
    var list = this.map { item ->
        ChatMessage(
            id = item.id,
            images = item.images.toBase64String().toPersistentList(), text = item.text
        )
    }

    return list
}

fun List<Bitmap?>.toBase64String(): List<String> {
    var list = this.map { item ->
        item?.getBase64() ?: ""
    }

    return list
}

fun List<ChatMessage>.toMessage(): List<Message> {
    var list = this.map { item ->
        Message(
            id = item.id,
            images = item.images.toList().toBitmap(), text = item.text
        )
    }

    return list
}

fun List<String>.toBitmap(): List<Bitmap?> {
    var list = this.map { item ->
        item.toBitmap()
    }

    return list
}