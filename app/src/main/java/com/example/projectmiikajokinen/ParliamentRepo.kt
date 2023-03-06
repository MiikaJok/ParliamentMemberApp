package com.example.projectmiikajokinen

import androidx.lifecycle.LiveData
//singleton joka toimii repona MembersOfParliamentille
object MemberRepo {
    //Saadaan instanssi membersOfParliamentDAOsta ja päästään kiinni databaseen
    private val dao = ParliamentDB.getInstance().membersOfParliamentDAO
    //LiveData objekti, joka pitää kaikkia jäseniä databasessa
    val logData: LiveData<List<MembersOfParliament>> = dao.getAll()
    //haetaan id:n perusteella yksittäinen henkilö
    fun getSingleMember(id: Int?): LiveData<List<MembersOfParliament>>{
        return dao.getSingleMember(id)
    }
    //haetaan tietyn puolueen jäsenet
    fun getPartyMembers(party: String): LiveData<List<MembersOfParliament>>{
        return dao.getEveryPartyMember(party)
    }
}