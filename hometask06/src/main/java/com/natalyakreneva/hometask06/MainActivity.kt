package com.natalyakreneva.hometask06

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.natalyakreneva.hometask06.ContactListAdapter.OnItemClickListener
import com.natalyakreneva.hometask06.DAO.Contact
import com.natalyakreneva.hometask06.DAO.ContactRepository
import com.natalyakreneva.hometask06.DAO.ContactRoomDatabase
import com.natalyakreneva.hometask06.DAO.ContactViewModel
import kotlinx.android.synthetic.main.activity_main.imageButtonAddContact

class MainActivity : AppCompatActivity() {
    private lateinit var contactRepository: ContactRepository
    private lateinit var contactViewModel: ContactViewModel
    private lateinit var recyclerView: RecyclerView
    private val allContacts: List<Contact> = mutableListOf()
    private lateinit var adapter: ContactListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val contactDao = ContactRoomDatabase.getInstance(this)?.contactDao()
        contactRepository= ContactRepository(contactDao)

        recyclerView = findViewById<RecyclerView>(R.id.listView)

        adapter = ContactListAdapter(allContacts, object : OnItemClickListener {
            override fun onItemClick(contact:Contact) {
                updateContacts(contact)
            }
        })
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        contactViewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
        contactViewModel.allContacts?.observe(this, Observer {
            it.let { adapter.setAllContacts(it) }
        })

        imageButtonAddContact.setOnClickListener {
            val intent = Intent(this, ActivityAddContact::class.java)
            startActivity(intent)
        }
    }


    private fun updateContacts(contact: Contact) {
        val intent = Intent (this, ActivityEditContact::class.java).apply {
            putExtra("contact_id", contact.id)}
        startActivity(intent)
    }


}


