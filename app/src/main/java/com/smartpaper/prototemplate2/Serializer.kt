package com.smartpaper.prototemplate2

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object ProtoSerializer: Serializer<TemplateSetting>{
    override val defaultValue: TemplateSetting = TemplateSetting.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): TemplateSetting {
        try {
            return TemplateSetting.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException){
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(
        t: TemplateSetting,
        output: OutputStream
    ) = t.writeTo(output)
}
