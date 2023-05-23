package com.example.uklkasirr.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Meja (
    @PrimaryKey (autoGenerate = true) var mjid: Int? = null,
    @ColumnInfo(name= "nomor_meja") var namaMeja: String?
)