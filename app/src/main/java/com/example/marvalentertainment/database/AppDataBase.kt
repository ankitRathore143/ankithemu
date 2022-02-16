package com.example.marvalentertainment.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.marvalentertainment.model.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Result::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getDao(): DataDao

    companion object {
        val appDataBase_DB = "APPDataBaseDB.db"

        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDataseClient(context: Context, scope: CoroutineScope): AppDataBase {

            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    appDataBase_DB
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .addCallback(CharDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
//                // return instance
                instance

            }
        }


        private class CharDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onCreate method to populate the database.
             */
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                    }
                }
            }
        }

    }
}