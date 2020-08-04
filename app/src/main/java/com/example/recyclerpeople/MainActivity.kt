package com.example.recyclerpeople

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerpeople.adapters.PersonAdapter
import com.example.recyclerpeople.adapters.PersonAdapterListener
import com.example.recyclerpeople.model.Person
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), PersonAdapterListener {
    private lateinit var adapter: PersonAdapter
    private var personEditing: Person? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = PersonAdapter(this, applicationContext)
        listPeople.adapter = adapter
        listPeople.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        btSave.setOnClickListener {
            if (personEditing == null) {
                personEditing = Person("", "", "")
            }
            val person = personEditing as Person

            person.title = txtTitle.text.toString()
            person.firstName = txtFirstName.text.toString()
            person.lastName = txtLastName.text.toString()

            val position = adapter.save(person)
            (listPeople.layoutManager as LinearLayoutManager).scrollToPosition(position)

            clear()
        }

        btClear.setOnClickListener {
            clear()
        }

        clear()
    }

    override fun onPersonSelected(person: Person) {
        personEditing = person

        txtTitle.setText(person.title)
        txtFirstName.setText(person.firstName)
        txtLastName.setText(person.lastName)
    }

    private fun clear() {
        txtTitle.setText("")
        txtFirstName.setText("")
        txtLastName.setText("")

        personEditing = null
    }
}