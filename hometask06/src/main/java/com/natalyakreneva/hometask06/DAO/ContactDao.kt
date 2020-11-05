package com.natalyakreneva.hometask06.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.natalyakreneva.hometask06.DAO.Contact


@Dao
interface ContactDao {
    @Query("SELECT * from contact_table")
    fun getAllContacts(): LiveData<List<Contact>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertContact(contact: Contact)

    @Update
    fun updateContact(contact: Contact)

    @Delete
    fun deleteContact(contact: Contact)

    @Query("select * from contact_table where id LIKE :id")
    fun getContact(id: Int): Contact

}