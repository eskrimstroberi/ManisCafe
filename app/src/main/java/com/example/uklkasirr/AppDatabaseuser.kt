package com.example.uklkasirr

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.uklkasirr.data.dao.UserDao
import com.example.uklkasirr.data.entity.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabaseUser: RoomDatabase() {
    abstract fun UserDao(): UserDao

    companion object{
        private var instance: AppDatabaseUser? = null

        fun getInstance(context: Context): AppDatabaseUser{
            if (instance==null){
                instance = Room.databaseBuilder(context, AppDatabaseUser::class.java, "app - database user")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }

}

