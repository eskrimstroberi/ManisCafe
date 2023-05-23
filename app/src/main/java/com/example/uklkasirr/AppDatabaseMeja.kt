package com.example.uklkasirr

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.uklkasirr.data.dao.MejaDao
import com.example.uklkasirr.data.entity.Meja



@Database(entities = [Meja::class], version = 3)
abstract class AppDatabaseMeja: RoomDatabase() {
    abstract fun MejaDao() : MejaDao

    companion object{
        private var instance: AppDatabaseMeja? = null

        fun getInstance(context: Context): AppDatabaseMeja{
            if (instance==null){
                instance = Room.databaseBuilder(context, AppDatabaseMeja::class.java, "app - database meja")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }

}

