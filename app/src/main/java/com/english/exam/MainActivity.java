package com.english.exam;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    public static List<Chapter> chapter = new ArrayList<>();
    public static int choosenChapterNumber;

    Button button;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    Button button10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseDatabase = FirebaseDatabase.getInstance();
        readChaptersFromFirebase();

        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        button10 = findViewById(R.id.button10);

        defaultButtonsInvisible();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosenChapterNumber = 0;
                startActivity(new Intent(MainActivity.this, ChoosingScreen.class));
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosenChapterNumber = 1;
                startActivity(new Intent(MainActivity.this, ChoosingScreen.class));
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosenChapterNumber = 2;
                startActivity(new Intent(MainActivity.this, ChoosingScreen.class));
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosenChapterNumber = 3;
                startActivity(new Intent(MainActivity.this, ChoosingScreen.class));
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosenChapterNumber = 4;
                startActivity(new Intent(MainActivity.this, ChoosingScreen.class));
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosenChapterNumber = 5;
                startActivity(new Intent(MainActivity.this, ChoosingScreen.class));
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosenChapterNumber = 6;
                startActivity(new Intent(MainActivity.this, ChoosingScreen.class));
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosenChapterNumber = 7;
                startActivity(new Intent(MainActivity.this, ChoosingScreen.class));
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosenChapterNumber = 8;
                startActivity(new Intent(MainActivity.this, ChoosingScreen.class));
            }
        });
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosenChapterNumber = 9;
                startActivity(new Intent(MainActivity.this, ChoosingScreen.class));
            }
        });
    }

    private void defaultButtonsInvisible() {
        button.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
        button3.setVisibility(View.INVISIBLE);
        button4.setVisibility(View.INVISIBLE);
        button5.setVisibility(View.INVISIBLE);
        button6.setVisibility(View.INVISIBLE);
        button7.setVisibility(View.INVISIBLE);
        button8.setVisibility(View.INVISIBLE);
        button9.setVisibility(View.INVISIBLE);
        button10.setVisibility(View.INVISIBLE);
    }

    private void showButtons() {
        button.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);
        button5.setVisibility(View.VISIBLE);
        button6.setVisibility(View.VISIBLE);
        button7.setVisibility(View.VISIBLE);
        button8.setVisibility(View.VISIBLE);
        button9.setVisibility(View.VISIBLE);
        button10.setVisibility(View.VISIBLE);
    }

    private void ShowData() {
        int i=0;
        for (Chapter chapter : chapter) {
            for(HashMap.Entry<String, String> entry : chapter.words.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
            }
            i++;
        }
    }

    private void createObjectInFirebase() {
        DatabaseReference chapter1 = firebaseDatabase.getReference().child("chapter1").child("words");
        DatabaseReference chapter2 = firebaseDatabase.getReference().child("chapter2").child("words");
        chapter1.child("english").setValue("polish");
        chapter1.child("english2").setValue("polish2");
        chapter1.child("english3").setValue("polish3");
        chapter2.child("english4").setValue("polish4");
        chapter2.child("english5").setValue("polish5");
        chapter2.child("english6").setValue("polish6");
    }

    private void readChaptersFromFirebase() {
        firebaseDatabase.getReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot child : dataSnapshot.getChildren()) {
                    chapter.add(child.getValue(Chapter.class));
                }
                showButtons();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
