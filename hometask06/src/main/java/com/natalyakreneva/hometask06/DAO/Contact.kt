package com.natalyakreneva.hometask06.DAO

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "contact_table")
class Contact (@ColumnInfo(name = "contactName") var name: String,
               @ColumnInfo(name = "contactPhone") var phoneNumber: String,
               @ColumnInfo(name = "contactImage") val image: Int) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo (name = "Id") var id: Int = 0
}