package com.example.recyclerpeople.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.recyclerpeople.R
import com.example.recyclerpeople.database.AppDatabase
import com.example.recyclerpeople.database.dao.PersonDao
import com.example.recyclerpeople.model.Person
import kotlinx.android.synthetic.main.item_person.view.*

class PersonAdapter(val listener: PersonAdapterListener, context: Context) :
    RecyclerView.Adapter<PersonAdapter.ViewHolder>() {

    private val dao: PersonDao
    private var people: MutableList<Person>

    init {
        val db = Room.databaseBuilder(context, AppDatabase::class.java, "person-db")
            .allowMainThreadQueries()
            .build()
        dao = db.personDao()
        people = dao.getAll().toMutableList()
    }

    fun save(person: Person): Int {
        return if (person.id == 0L) {
            person.id = dao.insert(person)

            val position = 0
            people.add(position, person)
            notifyItemInserted(position)
            position
        } else {
            dao.update(person)

            val position = people.indexOf(person)
            notifyItemChanged(position)
            position
        }
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_person
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(viewType, parent, false)
        )

    override fun getItemCount() = people.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val person = people[position]
        holder.fillView(person)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun fillView(person: Person) {
            itemView.lbTitle.text = person.title
            itemView.lbFirstName.text = person.firstName
            itemView.lbLastName.text = person.lastName

            itemView.setOnClickListener {
                listener.onPersonSelected(person)
            }
        }
    }
}