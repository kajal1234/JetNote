package com.kajal.jetnote.util

import androidx.room.TypeConverter
import java.util.UUID

class UUIDConverter {
    @TypeConverter
    fun fromUUID(uuid: UUID): String? {
        return uuid.toString()
    }

    @TypeConverter
    fun uuidFromString(uuid: String?): UUID? {
        return UUID.fromString(uuid)
    }
}