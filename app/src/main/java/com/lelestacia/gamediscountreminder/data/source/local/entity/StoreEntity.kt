package com.lelestacia.gamediscountreminder.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "store", indices = [Index(value = ["store_id"], unique = true)])
data class StoreEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "store_id")
    val storeID: String,

    @ColumnInfo(name = "store_name")
    val storeName: String,

    @ColumnInfo(name = "is_active")
    val isActive: Int,

    @ColumnInfo(name = "store_banner")
    val banner: String,

    @ColumnInfo(name = "store_icon")
    val icon: String,

    @ColumnInfo(name = "store_logo")
    val logo: String
)
