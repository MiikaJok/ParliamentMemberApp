package com.example.projectmiikajokinen

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [MembersOfParliament::class], version = 5, exportSchema = false)

abstract class ParliamentDB: RoomDatabase() {
    /*
    Miika Jokinen
    2201315
    3.3.2023
    Tässä luokassa esitellään  room database, DAO joka keskustelee
    MembersOfParliaMent kokonaisuuden kanssa */
    abstract val membersOfParliamentDAO: MembersOfParliamentDAO
    companion object {
        @Volatile
        private var INSTANCE: ParliamentDB? = null
        fun getInstance(): ParliamentDB {
            synchronized(this) {
                var instance = INSTANCE
                if(instance == null) {
                    instance = Room.databaseBuilder(MyApp.appContext, ParliamentDB::class.java,"ParliamentDB")
                        .fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}