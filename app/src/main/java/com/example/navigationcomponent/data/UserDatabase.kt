package com.example.roomimplementation.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomimplementation.model.User


/*
THIS CLASS WILL REPRESENT A DATABASE IN THE ROOM LIBRARY, IT CONTAINS THE DATABASE FOLDER AND
THE ACCESPOINT TO THE CONNECTIONS OF THE DATA
 */
@Database(entities = [User::class], version=1, exportSchema = false)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object{
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase{
            val tempInstance = INSTANCE
             if( tempInstance != null){
                 return  tempInstance
             }
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext, UserDatabase::class.java,
                    "user_database").build()
                INSTANCE = instance
                return instance
            }

        }
    }


}