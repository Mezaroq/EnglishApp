package com.english.exam;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;

public class Lessons extends AppCompatActivity {

    public Chapter choosenChapter;
    public HashMap<String, String> words;
    public ArrayList<String> KeyWords = new ArrayList<>();
    public int numberOfWords;
    public int KeyWordsIndex = 0;
    public CountDownTimer timer;
    public int timeDelay;
    public TextView chapter;
    public TextView wordNumber;
    public TextView word;
    public TextView translation;
    public Button back_button;
    public Button next_button;
    public Button time01;
    public Button time02;
    public Button time03;
    public Button time04;
    public Button time05;
    public Button time06;
    public Button time07;
    public Button time08;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);

        chapter = findViewById(R.id.textView11);
        wordNumber = findViewById(R.id.textView12);
        word = findViewById(R.id.textView13);
        translation = findViewById(R.id.textView14);
        back_button = findViewById(R.id.button11);
        next_button = findViewById(R.id.button16);
        time01 = findViewById(R.id.button17);
        time02 = findViewById(R.id.button18);
        time03 = findViewById(R.id.button19);
        time04 = findViewById(R.id.button20);
        time05 = findViewById(R.id.button24);
        time06 = findViewById(R.id.button21);
        time07 = findViewById(R.id.button22);
        time08 = findViewById(R.id.button23);


        choosenChapter = MainActivity.chapter.get(MainActivity.choosenChapterNumber);
        words = choosenChapter.getWords();

        chapter.setTextColor(Color.parseColor("#282828"));
        chapter.setText("Rozdział "+String.valueOf(MainActivity.choosenChapterNumber+1));

        timer(); //inicjalizacja timera;
        getKeyWords(); //pobranie slow kluczowych jako osobna ArrayLista
        numberOfWords = KeyWords.size();

        displayWord(KeyWordsIndex); //wyswietl pierwsze slowo

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                if (KeyWordsIndex>0) {
                    KeyWordsIndex--;
                    displayWord(KeyWordsIndex);
                }
                else {
                    KeyWordsIndex=numberOfWords-1;
                    displayWord(KeyWordsIndex);
                }
            }
        });

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                if (KeyWordsIndex < numberOfWords - 1) {
                    KeyWordsIndex++;
                    displayWord(KeyWordsIndex);
                }
                else {
                    KeyWordsIndex=0;
                    displayWord(KeyWordsIndex);
                }
            }
        });

        time01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeDelay = 0;
                timer();
            }
        });

        time02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeDelay = 400;
                timer();
            }
        });

        time03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeDelay = 600;
                timer();
            }
        });

        time04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeDelay = 800;
                timer();
            }
        });

        time05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeDelay = 1200;
                timer();
            }
        });

        time06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeDelay = 1500;
                timer();
            }
        });

        time07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeDelay = 1800;
                timer();
            }
        });

        time08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeDelay = 2200;
                timer();
            }
        });
    }

    private void displayWord(int WordIndex) {
        if (ChoosingScreen.EnglishToPolish) {
            word.setText(KeyWords.get(WordIndex));
            displayTranslation(WordIndex);
        }
        else {
            word.setText(words.get(KeyWords.get(WordIndex)));
            displayTranslation(WordIndex);
        }
        displayWordNumber(WordIndex);
    }

    private void displayTranslation(int WordIndex) {
        if (ChoosingScreen.EnglishToPolish) {
            if (timeDelay != 0) {
                timer.start();
            }
            else {
                translation.setText(words.get(KeyWords.get(WordIndex)));
            }
        }
        else {
            if (timeDelay != 0) {
                timer.start();
            }
            else {
                translation.setText(KeyWords.get(WordIndex));
            }
        }
    }

    private void displayWordNumber(int WordIndex) {
        wordNumber.setText("Słowo "+(WordIndex+1)+" z "+numberOfWords);
    }

    private void timer() {
        timer = new CountDownTimer(timeDelay, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                translation.setText(" ");
            }

            @Override
            public void onFinish() {
                if (ChoosingScreen.EnglishToPolish) {
                    translation.setText(words.get(KeyWords.get(KeyWordsIndex)));
                }
                else {
                    translation.setText(KeyWords.get(KeyWordsIndex));
                }
            }
        };
    }

    private void getKeyWords() {
        for(HashMap.Entry<String, String> entry : choosenChapter.words.entrySet()) {
            String key = entry.getKey();
            //String value = entry.getValue();
            KeyWords.add(key);
        }
    }
}
