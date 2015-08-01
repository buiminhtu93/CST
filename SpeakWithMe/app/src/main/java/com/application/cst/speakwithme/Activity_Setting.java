package com.application.cst.speakwithme;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by KenBui on 02/08/2015.
 */
public class Activity_Setting extends Activity {
    SQLDatabaseSource db;
    Spinner spinnerFirt;
    Spinner spinnerSecond;
    Spinner spinnerSpeed;
    ImageButton imgbuttonBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        spinnerFirt=(Spinner)findViewById(R.id.spinnerFirstLanguage);
        spinnerSecond=(Spinner)findViewById(R.id.spinnerSecondLanguage);
        spinnerSpeed=(Spinner)findViewById(R.id.spinnerSpeed);
        imgbuttonBack=(ImageButton)findViewById(R.id.imageButtonBackSetting);
        db=new SQLDatabaseSource(this);
        imgbuttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        spinnerFirt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = spinnerFirt.getSelectedItem().toString();
                updateFirstLanguage(value);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerSecond.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = spinnerSecond.getSelectedItem().toString();
                updateSecondLanguage(value);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerSpeed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = spinnerSpeed.getSelectedItem().toString();
                updateSpeed(value);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
       loadSetting();
    }

    private void loadSetting()
    {
        Item_Setting item=new Item_Setting();
        item=db.getSettingItem();
        if (item.getFirstLanguage().equals("Vietnamese")) spinnerFirt.setSelection(0);
        if (item.getFirstLanguage().equals("English")) spinnerFirt.setSelection(1);
        if (item.getFirstLanguage().equals("Japanese")) spinnerFirt.setSelection(2);
        if (item.getSecondLanguage().equals("Vietnamese")) spinnerSecond.setSelection(0);
        if (item.getSecondLanguage().equals("English")) spinnerSecond.setSelection(1);
        if (item.getSecondLanguage().equals("Japanese")) spinnerSecond.setSelection(2);
        if (item.getSpeed().equals("Slow")) spinnerSpeed.setSelection(0);
        if (item.getSpeed().equals("Quick")) spinnerSpeed.setSelection(1);
    }

    public   void updateFirstLanguage(String value)
    {
        db.update_Setting_FirstLanguage(value);
    }

    public void  updateSecondLanguage(String value)
    {
        db.update_Setting_SecondLanguage(value);
    }

    public  void updateSpeed(String value)
    {
        db.update_Setting_Speed(value);
    }
}
