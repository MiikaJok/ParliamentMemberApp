package com.example.projectmiikajokinen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
class EveryMemberAdapter(val members: List<String>) : RecyclerView.Adapter<EveryMemberAdapter.MemberListViewHolder>() {
    /*
    * Miika Jokinen
    * 2201315
    * 5.3.2023
    * Tässä luokassa luodaan adapteri, kutsutaan itemlistaa, näytetään data
    * tietyssä paikassa ja navigoidaan uuteen fragmenttiin. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.everymemberitemlist, parent, false)
        return MemberListViewHolder(view) //palauttaa uuden instanssin
    }
    //Palautetaan tarvittavien itemien määrä
    override fun getItemCount(): Int {
        return members.size
    }
    //Tässä funktiossa näytetään data oikeassa kohdassa recyclerviewssä
    override fun onBindViewHolder(holder: MemberListViewHolder, position: Int) {
        //asetetaan teksti annettuun paikkaan
        holder.itemView.findViewById<TextView>(R.id.MemberList).apply{
            text = members[position]
            //asetetaan onClickListener havaitsemaan, mitä nimeä käyttäjä klikkaa ja navigoi sen nimen single infoihin
            setOnClickListener {
                val action = EveryMemberDirections.actionEveryMemberToSingleMember(text.toString())
                it.findNavController().navigate(action)
            }
        }
    }
    /*
    * Miika Jokinen
    * 2201315
    * 5.3.2023
    * Määritetään viewholder, joka näyttää itemit recyclerviewissä,
    * tässä tapauksessa pelkkään textviewiin */
    class MemberListViewHolder(view: View): RecyclerView.ViewHolder(view)
}