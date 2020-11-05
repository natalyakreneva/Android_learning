package com.natalyakreneva.hometask06.DAO

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData


class ContactViewModel (application: Application) : AndroidViewModel(application) {

    private val contactRepository:ContactRepository

    val allContacts: LiveData<List<Contact>>?

    init {
        val contactsDao = ContactRoomDatabase.getInstance(application)?.contactDao()
        contactRepository = ContactRepository(contactsDao)
        allContacts = contactRepository.getAllContacts()
    }

}