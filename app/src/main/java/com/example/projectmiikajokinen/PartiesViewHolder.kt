package com.example.projectmiikajokinen

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.projectmiikajokinen.databinding.PartyitemlistBinding

class PartiesViewHolder (private val view: PartyitemlistBinding) : RecyclerView.ViewHolder(view.root){
    fun bind (party: String){
        view.PartyText.text = party
    }
}