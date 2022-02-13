package com.example.marvalentertainment.database

import android.content.Context
import androidx.room.*
import com.example.marvalentertainment.model.Result

@Database(entities = [Result::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getDao(): DataDao

    companion object {
        val appDataBase_DB = "APPDataBaseDB.db"

        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDataseClient(context: Context): AppDataBase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, AppDataBase::class.java, appDataBase_DB)
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }

    }
}