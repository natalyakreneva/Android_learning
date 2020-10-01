package com.natalyakreneva.hometask05.DAO

import androidx.lifecycle.LiveData

class ContactRepository(private val contactDao: ContactDao?) {

    fun getAllContacts(): LiveData<List<Contact>>? {
        return contactDao?.getAllContacts()
    }

    fun insertContact (contact: Contact) {
        contactDao?.insertContact(contact)
    }

    fun getContact(id: Int): Contact? {
        return contactDao?.getContact(id)
    }

    fun deleteContact (contact: Contact) {
        contactDao?.deleteContact(contact)
    }

    fun updateContact(contact: Contact) {
        contactDao?.updateContact(contact)
    }

}