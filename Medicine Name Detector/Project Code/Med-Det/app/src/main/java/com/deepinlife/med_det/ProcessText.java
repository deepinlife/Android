package com.deepinlife.med_det;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringTokenizer;

public class ProcessText extends AppCompatActivity {

    public static HashMap<Character,HashMap<String,String>> mshop = new HashMap<Character, HashMap<String, String>>();
    TextToSpeech t1;

    int flag=0;
    static int  data=0;

   public String message;
    String msg1,msg2;
    private TextView medname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_text);
        medname=(TextView)findViewById(R.id.result);
        Button tsp=(Button)findViewById(R.id.textts);

       if(data==0)
       {   data=1;
           medicinedatabase();
       }

        t1=new TextToSpeech(getApplicationContext(),new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });

        Intent intent1 = getIntent();
        if(intent1.hasExtra("nameofmedicine") && intent1.hasExtra("descriptionaboutmedicine"))
        {
            Bundle bd= getIntent().getExtras();
            if(!bd.getString("nameofmedicine").equals(null)){
                msg1 = bd.getString("nameofmedicine");

            }
            if(!bd.getString("descriptionaboutmedicine").equals(null)){
                msg2 = bd.getString("descriptionaboutmedicine");
            }
            addmedicine(msg1,msg2);

        }
        if (getIntent().getStringExtra("MEDICAL") != null)
          {    message = intent1.getStringExtra("MEDICAL");
                checker(message);
          }


          tsp.setOnTouchListener(new View.OnTouchListener() {
              @Override
              public boolean onTouch(View view, MotionEvent motionEvent) {
                  String toSpeak = "Press Here For Result";
                  t1.setSpeechRate((float) 0.7);
                  t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                  return false;
              }
          });

        tsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = medname.getText().toString();
                t1.setSpeechRate((float) 0.7);
                t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);

            }
        });


    }

    private void medicinedatabase() {
        SharedPreferences preferences = getSharedPreferences("MedicineDetection", Context.MODE_WORLD_WRITEABLE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("COMBIFLAM","Used in body Pain");
        editor.putString("PARACETAMOL","Used in fever");
        editor.putString("VOLINI","Used for  Inflammation and swelling in the joints and muscles");
        editor.putString("VAPORUB","Used to treat colds and minor aches");
        editor.putString("ITCHGUARD","Used to treat skin yeast or fungal infections, rashes");
        editor.apply();

    }


    private void addmedicine( String name,String description) {
        final String finalname = name.toUpperCase();
     final String finalDesciption=description;

        SharedPreferences preferences = getSharedPreferences("MedicineDetection", Context.MODE_WORLD_WRITEABLE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(finalname,finalDesciption);
        editor.apply();

        medname.setText("Medicine" + " " + finalname + " " + description+ " added succesfull ");


    }


    private void checker(String sentence)
    {
        StringTokenizer t = new StringTokenizer(sentence);
        String word ="";
        while(t.hasMoreTokens())
        {
            word = t.nextToken();
            final String finalWord = word.toUpperCase();


            SharedPreferences prfs = getSharedPreferences("MedicineDetection", Context.MODE_PRIVATE);
            String name = prfs.getString(finalWord, "");
            if(!name.equalsIgnoreCase(""))
            {       flag=1;

                  medname.setText(finalWord + " " + name);
            }

        }

        if(flag==0)
        {
            medname.setText("Sorry we can  not detect the name of medicine I think You have to rotate the cover or try again");

        }

    }
    public void onPause(){
        if(t1 !=null){
            t1.stop();
            t1.shutdown();
        }
        super.onPause();
    }
}
