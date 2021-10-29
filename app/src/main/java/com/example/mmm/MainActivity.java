package com.example.mmm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static final String CALCULATOR_ID = "Calculator";
    static final String LECTURE_6_ID = "Lecture_6";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstui);


        Spinner application_chooser = (Spinner) findViewById(R.id.application_chooser_spinner);
        ArrayList<String> list = new ArrayList<>();
        list.add(CALCULATOR_ID);
        list.add(LECTURE_6_ID);
        list.add("Rap");
        list.add("Basketball");
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
                    case CALCULATOR_ID:
                        intent = new Intent(MainActivity.this, Calculator.class);
                        startActivity(intent);
                    case LECTURE_6_ID:
                        intent = new Intent(MainActivity.this, Lecture6.class);
                        startActivity(intent);
                    default:
                        intent = new Intent(MainActivity.this, Calculator.class);
                        startActivity(intent);
                }
            }
        });
    }


}