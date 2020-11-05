package com.natalyakreneva.hometask07

import android.content.Intent
import android.content.IntentFilter
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

private const val INTERNAL ="Internal"
private const val EXTERNAL ="External"
private const val CUSTOM_ACTION ="com.natalyakreneva.hometask07.CUSTOM_ACTION"

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPreferences : SharedPreferences
    private val KEY_INDEX="Saved radio button index"
    private lateinit var radioGroup: RadioGroup
    private lateinit var editor: SharedPreferences.Editor
     private lateinit var radioButtonInternal:RadioButton
    private lateinit var radioButtonExternal:RadioButton
    private val broadcastReciever= MyBroadcastReciever()
    private var storageType: StorageType = StorageType.INTERNAL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        radioGroup = findViewById(R.id.radioGroup);
        radioButtonInternal = findViewById(R.id.radioButtonInternal)
        radioButtonExternal = findViewById(R.id.radioButtonExternal)
        loadPreferences()
        createReciever()
        radioGroup.setOnCheckedChangeListener { radioGroup, i ->
            when {
                radioButtonInternal.isChecked -> {storageType = StorageType.INTERNAL}
                radioButtonExternal.isChecked -> {storageType = StorageType.EXTERNAL}
            }
            sendBroadcast(Intent().apply {
                action = CUSTOM_ACTION
                putExtra("CUSTOM", "STORAGE TYPE HAS BEEN CHANGED")
            })
            savePreferences(KEY_INDEX, storageType)
        }

    }

    private fun createReciever() {
        val intentFilter =IntentFilter().apply {
            addAction(CUSTOM_ACTION)
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            addAction(Intent.ACTION_TIME_CHANGED)
            addAction(Intent.ACTION_TIMEZONE_CHANGED)
        }
        registerReceiver(broadcastReciever, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(broadcastReciever)
    }

    private fun savePreferences(keyIndex: String, storageType: StorageType) {
        sharedPreferences=getSharedPreferences("APP_PREF", MODE_PRIVATE)
        editor =sharedPreferences.edit()
        editor.putString(keyIndex, storageType.toString())
        editor.apply()
    }

    protected fun loadPreferences () {
        sharedPreferences=getSharedPreferences("APP_PREF", MODE_PRIVATE)
        var savedType = sharedPreferences.getString(KEY_INDEX, "")
        when (savedType) {
            INTERNAL -> {
                radioButtonInternal.setChecked(true)
            }
            EXTERNAL -> {
                radioButtonExternal.setChecked(true)
            }
        }
    }
 }
