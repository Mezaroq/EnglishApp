package com.english.exam;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ChoosingScreen extends AppCompatActivity {

    public static boolean EnglishToPolish = true; //typ uczenia sie
    TextView chapter;
    Button uczenie;
    Button losowanie;
    Button wpisywanie;
    Button type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosing_screen);

        chapter=findViewById(R.id.textView10);
        uczenie=findViewById(R.id.button13);
        losowanie=findViewById(R.id.button14);
        wpisywanie=findViewById(R.id.button15);
        type=findViewById(R.id.button12);
        type.setText("Angielski-polski");
        chapter.setTextColor(Color.parseColor("#282828"));
        chapter.setText("Rozdział "+String.valueOf(MainActivity.choosenChapterNumber+1));

        type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (EnglishToPolish) {
                    EnglishToPolish=false;
                    type.setText("Polski-Angielski");
                }
                else {
                    EnglishToPolish=true;
                    type.setText("Angielski-Polski");
                }
            }
        });

        uczenie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChoosingScreen.this, Lessons.class));
            }
        });

        losowanie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChoosingScreen.this, Learning.class));
            }
        });

        wpisywanie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChoosingScreen.this, Writing.class));
            }
        });
    }
}
