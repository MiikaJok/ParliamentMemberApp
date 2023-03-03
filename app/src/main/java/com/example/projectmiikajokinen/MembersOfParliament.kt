package com.example.projectmiikajokinen

import androidx.lifecycle.LiveData
import androidx.room.*

@Entity
data class MembersOfParliament (
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
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: MembersOfParliament)
    @Query("select * from MembersOfParliament")
    fun getAll(): LiveData<List<MembersOfParliament>>
    // @Query("select * from MemberOfParliament where timestamp > :key")
    //fun getAllSince(key: Long): LiveData<List<MemberOfParliament>>
}