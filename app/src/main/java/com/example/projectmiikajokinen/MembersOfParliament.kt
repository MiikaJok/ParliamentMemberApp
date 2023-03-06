package com.example.projectmiikajokinen

import androidx.lifecycle.LiveData
import androidx.room.*
@Entity
data class MembersOfParliament (
    /*
    Miika Jokinen
    2201315
    27.3.2023
    Dataclassi kokonaisuus, jossa määritellään LiveDatasta
    poimittavat tiedot. */
    @PrimaryKey
    val hetekaId: Int,
    val seatNumber: Int = 0,
    val lastname: String,
    val firstname: String,
    val party: String,
    val minister: Boolean = false,
    val pictureUrl: String = ""
)
@Dao
interface MembersOfParliamentDAO{
    /*Tässä interfacessa päästään käsiksi dataan ja muokkaamaan
    * , mitä sieltä poimitaan projektiin mukaan. MembersOfParliament taulusta.*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: MembersOfParliament)
    @Query("select * from MembersOfParliament") //valitaan kaikki data MembersOfParliament dataclassista
    fun getAll(): LiveData<List<MembersOfParliament>> //tätä funktiota kutsumalla koodissa saadaan livedatana tiedot
    @Query("Select * from MembersOfParliament where party = :party") //Valitaan kaikki data valitusta party taulusta
    fun getEveryPartyMember(party: String): LiveData<List<MembersOfParliament>>
    @Query("Select * from MembersOfParliament where hetekaId = :id") //haetaan hetekaId MembersOfParliament taulusta
    fun getSingleMember(id: Int?): LiveData<List<MembersOfParliament>>
}