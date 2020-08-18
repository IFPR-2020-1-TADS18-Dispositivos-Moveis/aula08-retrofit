package com.example.recyclerpeople.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerpeople.R
import com.example.recyclerpeople.api.PersonService
import com.example.recyclerpeople.model.Person
import kotlinx.android.synthetic.main.item_person.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PersonAdapter(val listener: PersonAdapterListener, context: Context) :
    RecyclerView.Adapter<PersonAdapter.ViewHolder>() {

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:3000/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val service = retrofit.create(PersonService::class.java)
    private var people = mutableListOf<Person>()

    init {
        service.getAll().enqueue(object : Callback<List<Person>> {
            override fun onFailure(call: Call<List<Person>>, t: Throwable) {}

            override fun onResponse(call: Call<List<Person>>, response: Response<List<Person>>) {
                people = response.body()!!.toMutableList()
                notifyDataSetChanged()
            }

        })
    }

    fun save(person: Person): Int {
        return if (person.id == null) {
            service.insert(person).enqueue(object : Callback<Person> {
                override fun onFailure(call: Call<Person>, t: Throwable) {}
                override fun onResponse(call: Call<Person>, response: Response<Person>) {
                    person.id = response.body()!!.id
                }
            })

            val position = 0
            people.add(position, person)
            notifyItemInserted(position)
            position
        } else {
            service.update(person.id!!, person).enqueue(object : Callback<Person> {
                override fun onFailure(call: Call<Person>, t: Throwable) {}
                override fun onResponse(call: Call<Person>, response: Response<Person>) {}
            })

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