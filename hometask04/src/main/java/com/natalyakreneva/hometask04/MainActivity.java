package com.natalyakreneva.hometask04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import 	com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements OnMyTouchListener {
    protected CustomView customView;
    protected Switch switchItem;
    protected SharedPreferences sPref;
    protected SharedPreferences.Editor editor;
    private static final String TOAST ="Toast";
    private static final String SNACK ="Snack";
    private static final String SWITCH_VALUE="Switch value";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        switchItem = (Switch) findViewById(R.id.switchItem);
        customView =(CustomView) findViewById(R.id.customView);
        customView.setOnMyTouchListener(this);


        switchItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sPref = getPreferences(MODE_PRIVATE);
                editor=sPref.edit();
                if (isChecked) {
                    editor.putString(SWITCH_VALUE,TOAST);
                    editor.commit();

                } else {
                    editor.putString(SWITCH_VALUE,SNACK);
                    editor.commit();
                }
            }
        });
    }


    @Override
    public void onCustomTouchEvent(int x, int y) {
        sPref = getPreferences(MODE_PRIVATE);
        String switchValue=sPref.getString(SWITCH_VALUE,"");

        switch (switchValue){
            case TOAST:
                Toast.makeText(MainActivity.this, "Нажаты координаты " + "[ " + x + "; " + y+ " ]", Toast.LENGTH_SHORT).show();
                break;
            case SNACK:
                Snackbar.make(customView, "Нажаты координаты " + "[ " + x + "; " + y+ " ]", Snackbar.LENGTH_LONG).show();
                break;

        }
    }


}
