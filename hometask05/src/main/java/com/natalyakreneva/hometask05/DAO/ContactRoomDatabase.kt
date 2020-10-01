package com.natalyakreneva.hometask05.DAO

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Contact::class), version = 1, exportSchema = false)
abstract class ContactRoomDatabase: RoomDatabase() {
    abstract fun contactDao(): ContactDao

     companion object {
        var instance: ContactRoomDatabase? = null

        fun getInstance(context: Context): ContactRoomDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(context, ContactRoomDatabase::class.java, "contactDB")
                        .allowMainThreadQueries()
                        .build()
            }
            return instance
        }
    }
}