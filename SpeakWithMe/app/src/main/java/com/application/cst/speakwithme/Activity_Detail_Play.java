package com.application.cst.speakwithme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by KenBui on 01/08/2015.
 */
public class Activity_Detail_Play extends Activity {
    SQLDatabaseSource db;
    List<Item_Detail> detailList;
    List<Item_Detail> tam;
    Adapter_Detail adapter;
    AtomicBoolean isRunning =new AtomicBoolean(false);
    String ID;
    Handler handler;
    Bundle bundle;
    Intent intent;
    ListView listView;
    TextView tvTitle;
    TextToSpeech tts;
    int count=0;

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_play);
        tvTitle = (TextView) findViewById(R.id.textViewDetailPlayTitle);
        db = new SQLDatabaseSource(this);
        detailList = new ArrayList<>();
        tam=new ArrayList<>();
        intent = getIntent();
        bundle = intent.getBundleExtra("DETAIL_PLAY");
        ID = bundle.getString("ID");
        String NAME = bundle.getString("NAME");
        tvTitle.setText(NAME);
        tts=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result=tts.setLanguage(Locale.US);
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
        loadData(ID);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                    Item_Detail item = detailList.get(msg.arg1);
                    tam.add(item);
                    String strSpeak = item.getContent().toString();
                    try {
                        if (item.getPerson().equals("teacher")) {
                            tts.speak(strSpeak, TextToSpeech.QUEUE_FLUSH, null);
                        }
                    } catch (Exception e) {
                    }
                    adapter.notifyDataSetChanged();
            }
        };
       doStart();

    }


    private void loadData(String id)
    {
        detailList=db.getlistDetail(id);
        listView=(ListView)findViewById(R.id.listViewDetailPlay);
        adapter=new Adapter_Detail(this, R.layout.item_detail,tam);
        listView.setAdapter(adapter);
    }

    private void doStart() {
        isRunning.set(false);

        Thread th=new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i =0;i<detailList.size();i++)
                {

                        Message msg = handler.obtainMessage();
                        msg.arg1 = i;
                        handler.sendMessage(msg);
                        Item_Detail item = detailList.get(i);
                        long time = 0;
                        time = item.getContent().length() * 90;
                        SystemClock.sleep(time);

                }
            }
        });
        isRunning.set(true);
        th.start();
    }


}
