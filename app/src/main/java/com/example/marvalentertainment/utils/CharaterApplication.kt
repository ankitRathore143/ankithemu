package com.example.marvalentertainment.utils

import android.app.Application
import com.example.marvalentertainment.database.AppDataBase
import com.example.marvalentertainment.repository.DataRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class CharaterApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    // No need to cancel this scope as it'll be torn down with the process

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { AppDataBase.getDataseClient(applicationContext, applicationScope) }
    val repository by lazy { DataRepository(database.getDao()) }
}
