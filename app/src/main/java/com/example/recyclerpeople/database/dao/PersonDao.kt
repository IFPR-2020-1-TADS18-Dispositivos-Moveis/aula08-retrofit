package com.example.recyclerpeople.database.dao

import androidx.room.*
import com.example.recyclerpeople.R
import com.example.recyclerpeople.model.Person

@Dao
interface PersonDao {
    @Query("SELECT * FROM people ORDER BY id DESC")
    fun getAll(): List<Person>

    @Insert
    fun insert(person: Person): Long

    @Update
    fun update(person: Person)

    @Delete
    fun delete(person: Person)
}