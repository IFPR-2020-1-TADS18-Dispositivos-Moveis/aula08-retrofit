package com.example.recyclerpeople.model

data class Person(var title: String, var firstName: String, var lastName: String) {
    companion object {
        fun example() = mutableListOf(
            Person("Sr.", "Diego", "Stiehl"),
            Person("Sr.", "Levy", "Ferreira"),
            Person("Sra.", "Maritza", "Silva"),
            Person("Sr.", "Josepher", "Castro"),
            Person("Sra.", "Rosana", "Prestes")
        )
    }
}