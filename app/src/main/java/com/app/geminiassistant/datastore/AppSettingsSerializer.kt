package com.app.geminiassistant.datastore

import androidx.datastore.core.Serializer
import com.app.geminiassistant.datastore.model.AppSettings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

class AppSettingsSerializer(override val defaultValue: AppSettings = AppSettings()) :
    Serializer<AppSettings> {

    override suspend fun readFrom(input: InputStream): AppSettings {
        return try {
            Json.decodeFromString(
                deserializer = AppSettings.serializer(), string = input.readBytes().decodeToString()
            )
        } catch (ex: Exception) {
            ex.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: AppSettings, output: OutputStream) {
        withContext(Dispatchers.IO) {
            output.write(
                Json.encodeToString(serializer = AppSettings.serializer(), t).encodeToByteArray()
            )
        }
    }
}