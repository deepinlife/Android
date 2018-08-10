package com.deepinlife.med_det;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.security.PrivateKey;
import java.util.Locale;

public class HomePage extends AppCompatActivity {
private Button capture,addmedicine;
    TextToSpeech t1;
    private String MESSAGE_KEY="ADD MEDICINE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        capture=(Button)findViewById(R.id.capture);
        addmedicine=(Button)findViewById(R.id.medicine);


        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });

        capture.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                String toSpeak = "CAPTURE IMAGE";
                t1.setSpeechRate((float) 0.7);
                t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);

                return false;
            }
        });
        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent = new Intent(getApplicationContext(), RecognizeText.class);
                startActivity(intent);
            }
        });

        addmedicine.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                String toSpeak = "ADD MEDICINE";
                t1.setSpeechRate((float) 0.7);
                t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);

                return false;
            }
        });

        addmedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent = new Intent(getApplicationContext(),AddMedicine.class);
                startActivity(intent);
            }
        });
    }
}
