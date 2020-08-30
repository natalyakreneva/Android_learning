package com.natalyakreneva.hometask03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.SearchView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.natalyakreneva.hometask03.ContactListAdapter.OnItemClickListener;
import java.util.ArrayList;

public class MainActivity extends Activity {
    private static final int ADD_ACTIVITY_RESULT = 1000;
    private static final int EDIT_ACTIVITY_RESULT = 2000;
    public static final String IS_DELETED = "is deleted";
    public static final String CONTACT_ID = "contactID";
    protected static final String CONTACTS = "contacts";
    public static final String CONTACT ="contact";
    private ImageButton imageButtonAddContact;
    private RecyclerView recyclerView;
    private SearchView searchView;
    public Contacts contact;
    private ArrayList<Contacts> contacts;

    public MainActivity() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contacts = new ArrayList();
        searchView = (SearchView)findViewById(R.id.searchContact);
        imageButtonAddContact = (ImageButton)findViewById(R.id.imageButtonAddContact);

        imageButtonAddContact.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityAddContact.class);
                startActivityForResult(intent, ADD_ACTIVITY_RESULT);
            }
        });

        recyclerView = (RecyclerView)findViewById(R.id.listView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        updateContacts();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case ADD_ACTIVITY_RESULT:
                contact = (Contacts)data.getSerializableExtra(CONTACT);
                contacts.add(contact);
                updateContacts();
                break;
            case EDIT_ACTIVITY_RESULT:
                boolean isDeleted = data.getBooleanExtra(IS_DELETED, false);
                int id = data.getIntExtra(CONTACT_ID, 0);
                if (isDeleted) {
                    contacts.remove(id);
                } else {
                    contact = (Contacts)data.getSerializableExtra(CONTACT);
                    contacts.set(id, contact);
                }

                updateContacts();
        }

    }

    private void updateContacts() {
        ContactListAdapter a = new ContactListAdapter(contacts, new OnItemClickListener() {
            public void onItemClick(Contacts contact) {
                Intent intent = new Intent(MainActivity.this, ActivityEditContact.class);
                intent.putExtra("contact", contact);
                startActivityForResult(intent, EDIT_ACTIVITY_RESULT);
            }
        });

        recyclerView.setAdapter(a);
        a.notifyDataSetChanged();
    }

    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(CONTACTS, contacts);
    }

    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        contacts = (ArrayList)savedInstanceState.getSerializable(CONTACTS);
        updateContacts();
    }
}