package com.jefferymoju.borutoapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jefferymoju.borutoapp.util.Constants.HERO_REMOTE_KEYS_DATABASE_TABLE

@Entity(tableName = HERO_REMOTE_KEYS_DATABASE_TABLE)
data class HeroRemoteKeys ( // remote key to tell the backend server which data to load next
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?,
    val lastUpdated: Long?
)