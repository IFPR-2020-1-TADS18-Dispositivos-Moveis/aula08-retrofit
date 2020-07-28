package com.example.recyclerpeople.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerpeople.R
import com.example.recyclerpeople.model.Person
import kotlinx.android.synthetic.main.item_person.view.*

class PersonAdapter(val listener: PersonAdapterListener) :
    RecyclerView.Adapter<PersonAdapter.ViewHolder>() {

    private val people = Person.example()

    fun add(person: Person): Int {
        val position = 0 // or 'itemCount' if you want to add at the end
        people.add(position, person)
        notifyItemInserted(position)
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_person, parent, false)
        )

    override fun getItemCount() = people.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val person = people[position]
        holder.fillView(person)
//        Log.d("onBindViewHolder", person.firstName) // You'll see it only when you scroll
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