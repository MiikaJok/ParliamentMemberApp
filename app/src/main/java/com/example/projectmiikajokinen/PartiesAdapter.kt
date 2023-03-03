package com.example.projectmiikajokinen

import android.view.LayoutInflater
import android.view.ViewGroup
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
        val party = parties[position]
        holder.bind(party)
    }
}