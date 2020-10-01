package com.natalyakreneva.hometask03;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;

import java.util.ArrayList;

import static com.natalyakreneva.hometask03.MainActivity.CONTACT;

public class ActivityAddContact extends Activity {
    private ImageButton returnButton;
    private ImageButton saveButton;
    private RadioButton radioButtonPhone;
    private RadioButton radioButtonEmail;
    private EditText addTextName;
    private EditText addTextPhoneNumber;
    private ArrayList<Contacts> contacts;
    protected static final String CONTACT_NAME = "Name";
    protected static final String CONTACT_PHONE = "Phone Number";


    public ActivityAddContact() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        final Contacts contact = new Contacts();
        super.onCreate(savedInstanceState);
        final Intent intent = this.getIntent();
        setContentView(R.layout.activity_add);

        radioButtonPhone = (RadioButton)this.findViewById(R.id.radioButtonPhone);
        radioButtonEmail = (RadioButton)this.findViewById(R.id.radioButtonEmail);
        addTextName = (EditText)this.findViewById(R.id.addTextName);
        addTextPhoneNumber = (EditText)this.findViewById(R.id.addTextPhoneNumber);
        saveButton = (ImageButton)this.findViewById(R.id.saveButton);
        returnButton = (ImageButton)this.findViewById(R.id.returnButton);

        saveButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                contact.setName(addTextName.getText().toString());
                contact.setPhoneNumber(addTextPhoneNumber.getText().toString());
                intent.putExtra("contact", contact);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        returnButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        RadioGroup radioGroup = (RadioGroup)this.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i) {
                    case R.id.radioButtonEmail:
                        contact.setImage(R.drawable.ic_baseline_contact_mail_24);
                        addTextPhoneNumber.setHint("Email");
                        break;
                    case R.id.radioButtonPhone:
                        contact.setImage(R.drawable.ic_baseline_contact_phone_24);
                        addTextPhoneNumber.setHint("Phone number");
                }

            }
        });
    }

    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(CONTACT_NAME,addTextName.getText().toString());
        outState.putString(CONTACT_PHONE, this.addTextPhoneNumber.getText().toString());
        super.onSaveInstanceState(outState);
    }

    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.addTextName.setText(savedInstanceState.getString(CONTACT_NAME));
        this.addTextPhoneNumber.setText(savedInstanceState.getString(CONTACT_PHONE));
    }
}