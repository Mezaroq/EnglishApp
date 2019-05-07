package com.english.exam;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class Writing extends AppCompatActivity {

    public Chapter choosenChapter;
    public HashMap<String, String> words;
    public ArrayList<String> KeyWords = new ArrayList<>();
    public TextView chapter;
    public TextView word;
    public EditText translate;
    public Button help;
    public Button next;
    public TextView correctWords;
    public TextView wordsInTranslation;
    public TextView helpUse;
    public TextView wordsRemained;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing);

        chapter = findViewById(R.id.textView16);
        translate = findViewById(R.id.textView14);
        word = findViewById(R.id.textView13);
        help = findViewById(R.id.button25);
        next = findViewById(R.id.button26);
        correctWords = findViewById(R.id.textView27);
        wordsInTranslation = findViewById(R.id.textView26);
        helpUse = findViewById(R.id.textView20);
        wordsRemained = findViewById(R.id.textView22);

        choosenChapter = MainActivity.chapter.get(MainActivity.choosenChapterNumber);
        words = choosenChapter.getWords();

        chapter.setTextColor(Color.parseColor("#282828"));
        chapter.setText("Rozdział "+String.valueOf(MainActivity.choosenChapterNumber+1));
    }
}
