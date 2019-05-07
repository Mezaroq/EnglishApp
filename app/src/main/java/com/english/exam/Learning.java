package com.english.exam;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.BoringLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Learning extends AppCompatActivity {

    public Chapter choosenChapter;
    public HashMap<String, String> words = new HashMap<>();
    ArrayList<String> choosenChapterKeys = new ArrayList<>();
    ArrayList<String> primaryKeys;
    ArrayList<String> addKeys;
    ArrayList<Button> answers = new ArrayList<>();
    Random generator = new Random();
    Button choosenButton;
    Boolean buttonIsActive = true;
    CountDownTimer timergood;
    CountDownTimer timerbad;
    Boolean visibility = true;
    String keyWord;
    TextView timeCounter;
    TextView word;
    TextView badAnswers;
    TextView wordsRemained;
    Button translate0;
    Button translate1;
    Button translate2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);

        //INICJALIZACJA ROZDZIALU I SLOW Z ROZDZIALU
        choosenChapter = MainActivity.chapter.get(MainActivity.choosenChapterNumber);
        getChapterKeys();
        words = choosenChapter.getWords();
        primaryKeys = new ArrayList<>(choosenChapterKeys);
        addKeys = new ArrayList<>(choosenChapterKeys);

        //OBSLUGA APLIKACJI
        word = findViewById(R.id.textView2);
        timeCounter = findViewById(R.id.textView3);
        badAnswers = findViewById(R.id.textView5);
        wordsRemained = findViewById(R.id.textView7);
        translate0 = (Button)findViewById(R.id.answer0);
        translate1 = (Button)findViewById(R.id.answer1);
        translate2 = (Button)findViewById(R.id.answer2);
        wordsRemained.setText(String.valueOf(primaryKeys.size()));

        timeCounter.setTextColor(Color.parseColor("#282828"));
        timeCounter.setText("Rozdział "+String.valueOf(MainActivity.choosenChapterNumber+1));
        addButtonsToList();
        generateWord();
        inicjalizacjaTimerow();

        translate0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonIsActive) {
                    checkWord(translate0);
                    buttonIsActive=false;
                }
            }
        });
        translate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonIsActive) {
                    checkWord(translate1);
                    buttonIsActive=false;
                }
            }
        });
        translate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonIsActive) {
                    checkWord(translate2);
                    buttonIsActive=false;
                }
            }
        });
    }

    private void inicjalizacjaTimerow() {
        timerGood();
        timerBad();
        timergood.cancel();
        timerbad.cancel();
    }

    private void timerGood() {
        timergood = new CountDownTimer(400, 200) {

            public void onTick(long millisUntilFinished) {
                //timeCounter.setTextColor(Color.parseColor("#258622"));
                //timeCounter.setText("Dobrze");
                choosenButton.setBackgroundResource(R.drawable.frame_button_good);
            }

            public void onFinish() {
                //timeCounter.setText(" ");
                translate0.setBackgroundResource(R.drawable.frame_button);
                translate1.setBackgroundResource(R.drawable.frame_button);
                translate2.setBackgroundResource(R.drawable.frame_button);
                checkIfKeysIsEmpty();
                buttonIsActive=true;
            }
        }.start();
    }

    private void timerBad() {
        timerbad = new CountDownTimer(400, 200) {

            public void onTick(long millisUntilFinished) {
                //timeCounter.setTextColor(Color.parseColor("#7c1515"));
                //timeCounter.setText("Źle");
                choosenButton.setBackgroundResource(R.drawable.frame_button_bad);
            }

            public void onFinish() {
                //timeCounter.setText(" ");
                translate0.setBackgroundResource(R.drawable.frame_button);
                translate1.setBackgroundResource(R.drawable.frame_button);
                translate2.setBackgroundResource(R.drawable.frame_button);
                checkIfKeysIsEmpty();
                buttonIsActive=true;
            }
        }.start();
    }

    private void checkWord(Button button) {
        if (ChoosingScreen.EnglishToPolish) {
            if (choosenChapter.words.get(word.getText().toString()).equalsIgnoreCase(button.getText().toString())) {
                primaryKeys.remove(word.getText().toString());
                wordsRemained.setText(String.valueOf(primaryKeys.size()));
                choosenButton=button;
                timergood.cancel();
                timerbad.cancel();
                timergood.start();
            } else {
                badAnswers();
                choosenButton=button;
                timergood.cancel();
                timerbad.cancel();
                timerbad.start();
            }
        }
        else {
            if (word.getText().toString().equalsIgnoreCase(words.get(button.getText().toString()))) {
                primaryKeys.remove(button.getText().toString());
                wordsRemained.setText(String.valueOf(primaryKeys.size()));
                choosenButton=button;
                timergood.cancel();
                timerbad.cancel();
                timergood.start();
            } else {
                badAnswers();
                choosenButton=button;
                timergood.cancel();
                timerbad.cancel();
                timerbad.start();
            }
        }
    }

    private void checkIfKeysIsEmpty() {
        if (!primaryKeys.isEmpty()) {
            generateWord();
        }
        else {
            word.setText("Odpowiedziałeś na wszystkie słowa");
            timeCounter.setText("Koniec");
            toogleVisibility();
        }
    }

    private void getChapterKeys() {
        for(HashMap.Entry<String, String> entry : choosenChapter.words.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            choosenChapterKeys.add(key);
        }
    }

    private void generateWord() {
        if (ChoosingScreen.EnglishToPolish) {
            keyWord = primaryKeys.get(generateNumber(primaryKeys.size()));
            word.setText(keyWord);

            int button = generateNumber(answers.size());
            answers.get(button).setText(words.get(keyWord));
            answers.remove(button);

            additionalWords();
        }
        else {
            keyWord = primaryKeys.get(generateNumber(primaryKeys.size()));
            word.setText(words.get(keyWord));

            int button = generateNumber(answers.size());
            answers.get(button).setText(keyWord);
            answers.remove(button);

            additionalWords();
        }
    }

    private void additionalWords() {
        if (ChoosingScreen.EnglishToPolish) {
            addKeys.remove(keyWord);
            String keyWord1 = addKeys.get(generateNumber(addKeys.size()));
            addKeys.remove(keyWord1);
            String keyWord2 = addKeys.get(generateNumber(addKeys.size()));
            addKeys.remove(keyWord2);

            Button button1 = answers.get(generateNumber(answers.size()));
            answers.remove(button1);
            Button button2 = answers.get(0);
            answers.remove(button2);

            button1.setText(words.get(keyWord1));
            button2.setText(words.get(keyWord2));

            addButtonsToList();
            addKeys = new ArrayList<>(choosenChapterKeys);
        }
        else {
            addKeys.remove(keyWord);
            String keyWord1 = addKeys.get(generateNumber(addKeys.size()));
            addKeys.remove(keyWord1);
            String keyWord2 = addKeys.get(generateNumber(addKeys.size()));
            addKeys.remove(keyWord2);

            Button button1 = answers.get(generateNumber(answers.size()));
            answers.remove(button1);
            Button button2 = answers.get(0);
            answers.remove(button2);

            button1.setText(keyWord1);
            button2.setText(keyWord2);

            addButtonsToList();
            addKeys = new ArrayList<>(choosenChapterKeys);
        }
    }

    private void badAnswers() {
        Integer actualValue = Integer.parseInt(badAnswers.getText().toString());
        actualValue++;
        badAnswers.setText(actualValue.toString());
    }

    private int generateNumber(int maxValue) {
        return generator.nextInt(maxValue);
    }

    private void addButtonsToList() {
        answers.clear();
        answers.add(translate0);
        answers.add(translate1);
        answers.add(translate2);
    }

    private void toogleVisibility() {
        if(visibility){
            visibility = false;
            translate0.setVisibility(View.INVISIBLE);
            translate1.setVisibility(View.INVISIBLE);
            translate2.setVisibility(View.INVISIBLE);
        }
        else {
            visibility = true;
            translate0.setVisibility(View.VISIBLE);
            translate1.setVisibility(View.VISIBLE);
            translate2.setVisibility(View.VISIBLE);
        }
    }
}
