package com.example.projectmiikajokinen

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MembersOfParliament::class], version = 5, exportSchema = false)

abstract class ParliamentDB: RoomDatabase() {
    abstract val membersOfParliamentDAO: MembersOfParliamentDAO
    companion object {
        @Volatile
        private var INSTANCE: ParliamentDB? = null
        fun getInstance(): ParliamentDB {
            synchronized(this) {
                var instance = INSTANCE
                if(instance == null) {
                    instance = Room.databaseBuilder(MyApp.appContext, ParliamentDB::class.java,"ops_database")
                        .fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}