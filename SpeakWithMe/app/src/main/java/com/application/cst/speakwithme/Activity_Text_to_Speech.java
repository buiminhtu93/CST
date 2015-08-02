package com.application.cst.speakwithme;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.Locale;

/**
 * Created by KenBui on 02/08/2015.
 */
public class Activity_Text_to_Speech extends Activity {
    SQLDatabaseSource db;
    String strSpeak="";
    EditText edtInput;
    TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_speech);
        db = new SQLDatabaseSource(this);
        edtInput=(EditText)findViewById(R.id.editTextInputTexttoSpeech);
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

        ImageButton imgButton=(ImageButton)findViewById(R.id.imageButtonSpeechTexttoSpeech);
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strSpeak = edtInput.getText().toString();
                try {
                    tts.speak(strSpeak, TextToSpeech.QUEUE_FLUSH, null);
                } catch (Exception e) {
                }
            }
        });

        ImageButton imgButtonClear=(ImageButton)findViewById(R.id.imageButtonClearInputTexttoSpeech);
        imgButtonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtInput.setText("");
            }
        });

        ImageButton imgButtonBack=(ImageButton)findViewById(R.id.imageButtonBackTexttoSpeech);
        imgButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
