package com.natalyakreneva.hometask05.DAO

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactViewModel (application: Application) : AndroidViewModel(application) {

    private val contactRepository:ContactRepository

    val allContacts: LiveData<List<Contact>>?

    init {
        val contactsDao = ContactRoomDatabase.getInstance(application)?.contactDao()
        contactRepository = ContactRepository(contactsDao)
        allContacts = contactRepository.getAllContacts()
    }



}