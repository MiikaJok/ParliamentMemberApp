package com.example.projectmiikajokinen

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
class PartiesAdapter(val parties: List<String>) : RecyclerView.Adapter<PartiesAdapter.PartiesViewHolder>() {
    /*
    Miika Jokinen
    2201315
    5.6.2023
    Tämä luokka toimii adapterina puolueille ja tuo layoutin näkyviin
    jokaiselle itemille, että puolueet ovat listattuna näkymässä*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartiesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.partyitemlist, parent, false)
        return PartiesViewHolder(view)
    }
    override fun getItemCount(): Int {
        return parties.size
    }
    override fun onBindViewHolder(holder: PartiesViewHolder, position: Int) {
        //asettaa puolueen nimen textviewiin
        holder.itemView.findViewById<TextView>(R.id.PartyText).apply{
            text = parties[position]
            val party = text.toString()
            //listeneri joka navigoi valitun puolueen jäseniin
            setOnClickListener {
                val action = PartiesDirections.actionPartiesToPartyMembers(party)
                Log.d("Query", text.toString() )
                it.findNavController().navigate(action)
            }
        }
    }
    /*
    Miika Jokinen
    2201315
    5.6.2023
    Tämä luokka pitää jokaisen itemin näkymää recyclerviewissä*/
    class PartiesViewHolder(view: View): RecyclerView.ViewHolder(view)
}