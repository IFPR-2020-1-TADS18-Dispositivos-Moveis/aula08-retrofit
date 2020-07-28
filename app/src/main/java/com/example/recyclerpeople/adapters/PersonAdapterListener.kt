package com.example.recyclerpeople.adapters

import com.example.recyclerpeople.model.Person

interface PersonAdapterListener {
    fun onPersonSelected(person: Person)
}