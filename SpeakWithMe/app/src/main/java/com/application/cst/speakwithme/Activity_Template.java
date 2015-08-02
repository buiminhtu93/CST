package com.application.cst.speakwithme;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by KenBui on 02/08/2015.
 */
public class Activity_Template extends Activity {
    ListView listview;
    List<Item_Template> itemTemplateList;
    SQLDatabaseSource db;
    Adapter_Template adapter;
    String First;
    String Second;
    String strSpeak="";
    TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template);
        listview=(ListView)findViewById(R.id.listViewTemplate);
        itemTemplateList=new ArrayList<>();
        db=new SQLDatabaseSource(this);
        loadSetting();
        tts=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result;
                    if (Second.equals("Japanese"))
                    result=tts.setLanguage(Locale.JAPAN);
                    else
                    result=tts.setLanguage(Locale.US);
                    double rate=0.8f;
                    tts.setSpeechRate((float)rate);
                    tts.setPitch((float)rate);
                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "This Language is not supported");
                    }

                } else {
                    Log.e("TTS", "Initilization Failed!");
                }

            }
        });
        loadData(First, Second);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                strSpeak = itemTemplateList.get(position).getSecond();
                try {
                    tts.speak(strSpeak, TextToSpeech.QUEUE_FLUSH, null);
                } catch (Exception e) {
                }
            }
        });
    }

    public void loadData(String first, String second)
    {
        db=new SQLDatabaseSource(this);
        itemTemplateList=db.getlistTemplate(first, second);
        adapter=new Adapter_Template(this, R.layout.item_template,itemTemplateList);
        listview.setAdapter(adapter);
    }

    public void loadSetting()
    {
        Item_Setting item=new Item_Setting();
       item=db.getSettingItem();
        First=item.getFirstLanguage().toString();
        Second=item.getSecondLanguage().toString();
    }
}
