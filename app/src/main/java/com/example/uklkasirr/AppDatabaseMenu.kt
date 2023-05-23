package com.example.uklkasirr

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.uklkasirr.data.dao.MenuDao
import com.example.uklkasirr.data.entity.Menu


@Database(entities = [Menu::class], version = 2)
abstract class AppDatabaseMenu: RoomDatabase() {
    abstract fun MenuDao() : MenuDao

    companion object{
        private var instance: AppDatabaseMenu? = null

        fun getInstance(context: Context): AppDatabaseMenu{
            if (instance==null){
                instance = Room.databaseBuilder(context, AppDatabaseMenu::class.java, "app - database menu")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }

}

