package com.example.recyclerpeople.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.recyclerpeople.database.dao.PersonDao
import com.example.recyclerpeople.model.Person

@Database(entities = [Person::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao
}