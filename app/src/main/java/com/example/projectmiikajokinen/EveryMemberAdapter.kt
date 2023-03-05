package com.example.projectmiikajokinen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class EveryMemberAdapter(val members: List<String>) : RecyclerView.Adapter<EveryMemberAdapter.MemberListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.everymemberitemlist, parent, false)
        return MemberListViewHolder(view)
    }
    override fun getItemCount(): Int {
        return members.size
    }
    override fun onBindViewHolder(holder: MemberListViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.MemberList).apply{
            text = members[position]
            setOnClickListener {
                val action = EveryMemberDirections.actionEveryMemberToSingleMember()
                it.findNavController().navigate(action)
            }
        }
    }
    class MemberListViewHolder(view: View): RecyclerView.ViewHolder(view){
    }
}