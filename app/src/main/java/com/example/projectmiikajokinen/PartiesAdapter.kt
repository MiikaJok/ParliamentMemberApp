package com.example.projectmiikajokinen

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.projectmiikajokinen.databinding.PartyitemlistBinding

class PartiesAdapter(val parties: List<String>) : RecyclerView.Adapter<PartiesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartiesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = PartyitemlistBinding.inflate(inflater, parent, false)
        return PartiesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return parties.size
    }

    override fun onBindViewHolder(holder: PartiesViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.PartyText).apply{
            text = parties[position]
            setOnClickListener {
                val action = PartiesDirections.actionPartiesToPartyMembers()
                it.findNavController().navigate(action)
            }
        }
    }
}