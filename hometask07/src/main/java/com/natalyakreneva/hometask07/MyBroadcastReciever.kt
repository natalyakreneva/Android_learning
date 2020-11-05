package com.natalyakreneva.hometask07

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

const val ACTION_NAME ="Action name"
const val DATE_STAMP ="Date"

class MyBroadcastReciever : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val currentTimeStamp = getCurrentDate()
        val bcAction = intent.action
        when (bcAction) {
            Intent.ACTION_AIRPLANE_MODE_CHANGED -> {
                printLog("Airplane mode changed $currentTimeStamp")
                startMyService(bcAction, currentTimeStamp,context)
            }
            "com.natalyakreneva.hometask07.CUSTOM_ACTION" -> {
                printLog("Storage type has been changed $currentTimeStamp")
                startMyService(bcAction, currentTimeStamp,context)
            }
            Intent.ACTION_TIME_CHANGED -> {
                printLog("Time has been changed $currentTimeStamp")
                startMyService(bcAction, currentTimeStamp,context)
            }
            Intent.ACTION_TIMEZONE_CHANGED -> {
                printLog("Time zone has been changed $currentTimeStamp")
                startMyService(bcAction, currentTimeStamp,context)
            }
        }
    }

    private fun printLog(msg: String) {
        Log.d("LOG", msg)
    }

    private fun startMyService(bcAction: String?, currentTimeStamp: String, context: Context) {
        val intent = Intent(context, MyService::class.java)
        intent.putExtra(ACTION_NAME,bcAction)
        intent.putExtra(DATE_STAMP,currentTimeStamp)
        context.startService(intent)
    }

    private fun getCurrentDate(): String {
        val currentDate =Date()
        val timeFormat: DateFormat = SimpleDateFormat("yyyy/MM/dd_HH:mm", Locale.getDefault())
        return timeFormat.format(currentDate)
    }

}