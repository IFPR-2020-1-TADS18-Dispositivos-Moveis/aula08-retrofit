package com.example.recyclerpeople.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "people")
data class Person(
    var title: String,
    @ColumnInfo(name = "first_name")
    var firstName: String,
    @ColumnInfo(name = "last_name")
    var lastName: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    val fullName get() = "$firstName $lastName (${title ?: "-"})"
    override fun toString() = fullName
}