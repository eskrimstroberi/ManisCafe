package com.example.uklkasirr.data.dao

import androidx.room.*
import com.example.uklkasirr.data.entity.Menu

@Dao
interface MenuDao {
    @Query("SELECT * FROM menu")
    fun getAll(): List<Menu>

    @Query("SELECT * FROM menu WHERE mnid IN (:menuIds)")
    fun loadAllByIds(menuIds: IntArray): List<Menu>

    @Insert
    fun insertAll(vararg menus: Menu)

    @Delete
    fun delete(menu: Menu)

    @Query("SELECT * FROM menu WHERE mnid = :mnid")
    fun get(mnid: Int) : Menu

    @Update
    fun update(menu: Menu)
}