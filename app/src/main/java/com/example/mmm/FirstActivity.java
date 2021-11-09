package com.example.mmm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.mmm.lecture10.Lecture10;

import java.util.ArrayList;

public class FirstActivity extends AppCompatActivity {
    static final String CALCULATOR_ID = "Calculator";
    static final String LECTURE_6_ID = "Lecture_6";
    static final String LECTURE_7_ID = "Lecture_7";
    static final String LECTURE_8_ID = "Lecture_8";
    static final String LECTURE_9_ID = "Lecture_9";
    static final String LECTURE_10_ID = "Lecture_10";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstui);


        Spinner application_chooser = (Spinner) findViewById(R.id.application_chooser_spinner);
        ArrayList<String> list = new ArrayList<>();
        list.add(CALCULATOR_ID);
        list.add(LECTURE_6_ID);
        list.add(LECTURE_7_ID);
        list.add(LECTURE_8_ID);
        list.add(LECTURE_9_ID);
        list.add(LECTURE_10_ID);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, list);
        application_chooser.setAdapter(adapter);
        application_chooser.getAdapter();


        //button function: go selected application/activity
        Button go_to_application_button = (Button) findViewById(R.id.go_to_button);
        go_to_application_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Spinner application_chooser = (Spinner) findViewById(R.id.application_chooser_spinner);
                Intent intent;
                switch (application_chooser.getSelectedItem().toString()) {
                    case LECTURE_6_ID:
                        intent = new Intent(FirstActivity.this, Lecture6.class);
                        startActivity(intent);
                        break;
                    case LECTURE_7_ID:
                        intent = new Intent(FirstActivity.this, Lecture7.class);
                        startActivity(intent);
                        break;
                    case LECTURE_8_ID:
                        intent = new Intent(FirstActivity.this, Lecture8.class);
                        startActivity(intent);
                        break;
                    case LECTURE_9_ID:
                        intent = new Intent(FirstActivity.this, Lecture9.class);
                        startActivity(intent);
                        break;
                    case LECTURE_10_ID:
                        intent = new Intent(FirstActivity.this, Lecture10.class);
                        startActivity(intent);
                        break;
                    case CALCULATOR_ID:
                    default:
                        intent = new Intent(FirstActivity.this, Calculator.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }


}