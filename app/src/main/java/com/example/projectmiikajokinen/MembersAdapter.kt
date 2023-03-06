package com.example.projectmiikajokinen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
class MembersAdapter(val members: List<String>) : RecyclerView.Adapter<MembersAdapter.MembersViewHolder>() {
    /*
    * Miika Jokinen
    * 2201315
    * 2.3.2023
    * Tässä luokassa luodaan adapteri, kutsutaan itemlistaa, näytetään data
    * tietyssä paikassa ja navigoidaan uuteen fragmenttiin. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MembersViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.memberitemlist, parent, false)
        return MembersViewHolder(view)
    }
    override fun getItemCount(): Int {
        return members.size
    }
    //Tässä funktiossa näytetään data oikeassa kohdassa recyclerviewssä
    override fun onBindViewHolder(holder: MembersViewHolder, position: Int){
        //asetetaan teksti annettuun paikkaan
        holder.itemView.findViewById<TextView>(R.id.memberTextView).apply{
            text = members[position]
            val member = text.toString()
            //asetetaan onClickListener havaitsemaan, mitä nimeä käyttäjä klikkaa ja navigoi sen nimen single infoihin
            setOnClickListener {
                val action = PartyMembersDirections.actionPartyMembersToSingleMember(member)
                it.findNavController().navigate(action)
            }
        }
    }
    /*
    * Miika Jokinen
    * 2201315
    * 2.3.2023
    * Määritetään viewholder, joka näyttää jäsen itemit recyclerviewissä,
    * tässä tapauksessa pelkkään textviewiin */
    class MembersViewHolder(view: View): RecyclerView.ViewHolder(view)
}