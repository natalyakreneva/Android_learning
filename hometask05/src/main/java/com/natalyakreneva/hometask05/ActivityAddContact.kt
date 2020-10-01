package com.natalyakreneva.hometask05

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.natalyakreneva.hometask05.DAO.Contact
import com.natalyakreneva.hometask05.DAO.ContactRepository
import com.natalyakreneva.hometask05.DAO.ContactRoomDatabase
import kotlinx.android.synthetic.main.activity_add.*
import kotlin.properties.Delegates

class ActivityAddContact : AppCompatActivity() {
    private lateinit var contactRepository: ContactRepository
    private lateinit var contact: Contact
    private var image by Delegates.notNull<Int>()
    private lateinit var name: String
    private lateinit var phoneNumber: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        val contactDao = ContactRoomDatabase.getInstance(this)?.contactDao()
        contactRepository=ContactRepository(contactDao)

        radioGroup.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.radioButtonEmail -> {
                    image =R.drawable.ic_baseline_contact_mail_24
                    addTextPhoneNumber.hint = "Email"
                }
                R.id.radioButtonPhone -> {
                    image=R.drawable.ic_baseline_contact_phone_24
                    addTextPhoneNumber.hint = "Phone number"
                }
            }
        }
        saveButton.setOnClickListener {
            name =addTextName.text.toString()
            phoneNumber = addTextPhoneNumber.text.toString()
            contact= Contact(name, phoneNumber, image)
            contactRepository.insertContact(contact)
            finish()
        }

        returnButton.setOnClickListener {
            finish()
        }
    }

}