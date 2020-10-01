package com.natalyakreneva.hometask03;

import android.content.Intent;
import android.os.Bundle;
import android.service.controls.actions.ControlAction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import static com.natalyakreneva.hometask03.MainActivity.CONTACT;
import static com.natalyakreneva.hometask03.MainActivity.CONTACT_ID;
import static com.natalyakreneva.hometask03.MainActivity.IS_DELETED;

public class ActivityEditContact extends AppCompatActivity {
    private Button removeButton;
    private EditText editName;
    private EditText editPhoneNumber;
    private ImageButton returnButtonEdit;
    private Contacts contact;
    private int id;
    protected static final String EDIT_NAME = "";
    protected static final String EDIT_PHONE = "";

      protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        removeButton = (Button)findViewById(R.id.removeButton);
        editName = (EditText)findViewById(R.id.editName);
        editPhoneNumber = (EditText)findViewById(R.id.editPhoneNumber);
        returnButtonEdit = (ImageButton)findViewById(R.id.returntButtonEdit);

        final Intent intent = getIntent();

        contact = (Contacts)intent.getSerializableExtra("contact");

        editName.setText(contact.getName());
        editPhoneNumber.setText(contact.getPhoneNumber());
        id = contact.getId();

         removeButton.setOnClickListener(new OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent intent1 = new Intent();
                  intent1.putExtra(IS_DELETED, true);
                  intent1.putExtra(CONTACT_ID,id);
                  setResult(RESULT_OK, intent1);
                  finish();
              }
          });

          returnButtonEdit.setOnClickListener(new OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent intent1 = new Intent();
                  contact.setName(editName.getText().toString());
                  contact.setPhoneNumber(editPhoneNumber.getText().toString());
                  intent1.putExtra("contact",contact);
                  setResult(RESULT_OK,intent1);
                  finish();
              }
          });

    }

    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(EDIT_NAME, editName.getText().toString());
        outState.putString(EDIT_PHONE, editPhoneNumber.getText().toString());
        super.onSaveInstanceState(outState);
    }

    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        editName.setText(savedInstanceState.getString(EDIT_NAME));
        editPhoneNumber.setText(savedInstanceState.getString(EDIT_PHONE));
    }
}