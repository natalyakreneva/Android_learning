package com.natalyakreneva.hometask06


import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.natalyakreneva.hometask06.DAO.Contact
import com.natalyakreneva.hometask06.DAO.ContactRepository
import com.natalyakreneva.hometask06.DAO.ContactRoomDatabase
import kotlinx.android.synthetic.main.activity_edit.removeButton
import kotlinx.android.synthetic.main.activity_edit.editName
import kotlinx.android.synthetic.main.activity_edit.editPhoneNumber



class ActivityEditContact : AppCompatActivity() {
    private lateinit var contactRepository: ContactRepository
    private lateinit var contact: Contact

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        
        val contactDao = ContactRoomDatabase.getInstance(this)?.contactDao()
        contactRepository= ContactRepository(contactDao)

        val intent= getIntent()
        val contactId =intent.getIntExtra("contact_id",0)

        contact = contactRepository.getContact(contactId)!!

        editName.setText(contact.name)
        editPhoneNumber.setText(contact.phoneNumber)

        removeButton.setOnClickListener(View.OnClickListener {
            contactRepository.deleteContact(contact)
            finish()
        })

       val returnButtonEdit = findViewById<ImageButton>(R.id.returnButtonEdit)
        returnButtonEdit.setOnClickListener(View.OnClickListener {
            contact.name = editName.getText().toString()
            contact.phoneNumber =editPhoneNumber.getText().toString()
            contactRepository.updateContact(contact)
            finish()
        })

    }

}
