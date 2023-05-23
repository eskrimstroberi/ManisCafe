package com.example.uklkasirr.data.dao

import androidx.room.*
import com.example.uklkasirr.data.entity.Meja

@Dao
interface MejaDao {
    @Query("SELECT * FROM meja")
    fun getAll(): List<Meja>

    @Query("SELECT * FROM meja WHERE mjid IN (:mejaIds)")
    fun loadAllByIds(mejaIds: IntArray): List<Meja>

    @Insert
    fun insertAll(vararg mejas: Meja )

    @Delete
    fun delete(meja: Meja)

    @Query("SELECT * FROM Meja WHERE mjid = :mjid")
    fun get(mjid: Int) : Meja

    @Update
    fun update(meja: Meja)
}