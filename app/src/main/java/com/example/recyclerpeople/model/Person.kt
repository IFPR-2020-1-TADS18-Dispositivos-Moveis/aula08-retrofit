package com.example.recyclerpeople.model

import com.google.gson.annotations.SerializedName

data class Person(
    var title: String,
    @SerializedName("first_name")
    var firstName: String,
    @SerializedName("last_name")
    var lastName: String
) {
    var id: Long? = null

    val fullName get() = "$firstName $lastName (${title ?: "-"})"
    override fun toString() = fullName
}