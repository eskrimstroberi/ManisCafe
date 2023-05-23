package com.example.uklkasirr.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Menu (
    @PrimaryKey (autoGenerate = true) var mnid: Int? = null,
    @ColumnInfo(name= "nama_menu") var namaMenu: String?,
    @ColumnInfo(name= "deskripsi") var deskripsi: String?,
    @ColumnInfo(name= "harga") var harga: String?
)