package com.example.projectmiikajokinen

import androidx.lifecycle.LiveData

object ParliamentRepo {
    private val dao = ParliamentDB.getInstance().membersOfParliamentDAO
    val logData: LiveData<List<MembersOfParliament>> = dao.getAll()

    suspend fun newOpsLogEntry(amount: Int, noteText: String){
        dao.insert(
            MembersOfParliament(hetekaId = 0,seatNumber = 0, firstname = String(), lastname = String(), party = String())
        )
    }
}