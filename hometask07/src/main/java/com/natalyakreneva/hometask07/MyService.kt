package com.natalyakreneva.hometask07

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
private const val LOG ="LOG"
private const val INTERNAL_FILE="data.txt"

class MyService : Service() {

    override fun onBind(intent: Intent): IBinder? {
       return null
    }
    private val KEY_INDEX="Saved radio button index"
    private lateinit var storageType: StorageType

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val action = intent?.getStringExtra(ACTION_NAME)
        val currentDate = intent?.getStringExtra(DATE_STAMP)
        Log.d(LOG, "Service started $startId")
        storageType =getStorageType();
        writeAction(action, currentDate,storageType, startId)
        return  super.onStartCommand(intent, flags, startId)
    }

    private fun writeAction(action: String?, currentDate: String?, storageType: StorageType,startId: Int) {

        var thread = Thread(Runnable {
            if (storageType == StorageType.EXTERNAL) {
        })
      
    }}

    override fun onDestroy() {
        Log.d(LOG, "Service stopped")
        super.onDestroy()
    }

    protected fun getStorageType (): StorageType {
        var sharedPreferences = getSharedPreferences("APP_PREF", MODE_PRIVATE)
        var savedType = sharedPreferences.getString (KEY_INDEX, "")
        return  StorageType.valueOf(savedType!!)
    }
}