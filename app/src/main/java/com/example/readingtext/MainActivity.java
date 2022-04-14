package com.example.readingtext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextToSpeech t1;
    TextToSpeech t1a;
    EditText txt;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = findViewById(R.id.xmlTxt);
        btn = findViewById(R.id.xmlbutton);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String toSpeak = txt.getText().toString();
                if (isArabic(toSpeak)){
                    Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                    t1a.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                }else {
                    Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                    t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                }

            }
        });

        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                        t1.setLanguage(Locale.UK);
                }
            }
        });

        t1a=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                        t1a.setLanguage(Locale.forLanguageTag("ar"));
                }
            }
        });

}// On_Create END
    private boolean isArabic(String text){
        String textWithoutSpace = text.trim().replaceAll(" ",""); //to ignore whitepace
        for (int i = 0; i < textWithoutSpace.length();) {
            int c = textWithoutSpace.codePointAt(i);
            //range of arabic chars/symbols is from 0x0600 to 0x06ff
            //the arabic letter 'لا' is special case having the range from 0xFE70 to 0xFEFF
            if (c >= 0x0600 && c <=0x06FF || (c >= 0xFE70 && c<=0xFEFF))
                i += Character.charCount(c);
            else
                return false;

        }
        return true;
    }

    }// CLASS END