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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = PersonAdapter(this)
        listPeople.adapter = adapter
        listPeople.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        btSave.setOnClickListener {
            val person = Person(
                    txtTitle.text.toString(),
                    txtFirstName.text.toString(),
                    txtLastName.text.toString()
            )
            val position = adapter.add(person)
            (listPeople.layoutManager as LinearLayoutManager).scrollToPosition(position)
        }
    }

    override fun onPersonSelected(person: Person) {
        txtTitle.setText(person.title)
        txtFirstName.setText(person.firstName)
        txtLastName.setText(person.lastName)
    }
}