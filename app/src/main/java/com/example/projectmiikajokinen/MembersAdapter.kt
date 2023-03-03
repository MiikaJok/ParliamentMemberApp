package com.example.projectmiikajokinen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class MembersAdapter(val members: List<String>) : RecyclerView.Adapter<MembersAdapter.MembersViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MembersViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.memberitemlist, parent, false)
        return MembersViewHolder(view)
    }
    override fun getItemCount(): Int {
        return members.size
    }
    override fun onBindViewHolder(holder: MembersViewHolder, position: Int){
        holder.itemView.findViewById<TextView>(R.id.memberTextView).apply{
            text = members[position]
            setOnClickListener {
                val action = PartiesDirections.actionPartiesToPartyMembers()
                it.findNavController().navigate(action)
            }
        }
    }
    class MembersViewHolder(view: View): RecyclerView.ViewHolder(view)



}