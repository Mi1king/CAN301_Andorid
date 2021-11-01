package com.example.mmm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class Calculator extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);


        //add listener to calculation button
        Button run = findViewById(R.id.calculate_button);
        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num1, num2, result;
                EditText editText = (EditText) findViewById(R.id.inputNumber1);
                EditText editText2 = (EditText) findViewById(R.id.inputNumber2);
                String operator = ((Spinner) findViewById(R.id.operator_chooser)).getSelectedItem().toString();
                num1 = getNumberFromView(editText);
                num2 = getNumberFromView(editText2);
                result = calculate(num1, num2, operator);
                TextView t =  findViewById(R.id.calc_result_textView);
                t.setText(Integer.toString(result));
            }
        });

        //set operators as adapter of spinner
//        final Spinner s = findViewById(R.id.operator_chooser);
//        ArrayList<String> list = new ArrayList<>();
//        list.add("+");
//        list.add("-");
//        list.add("*");
//        list.add("/");
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.activity_calculator, list);
//        s.setAdapter(adapter);
//        s.getAdapter();


    }

    private int getNumberFromView(EditText t) {
        String text = t.getText().toString();
        int num;
        if (text.equals("")) {
            num = 0;
        } else {
            num = Integer.parseInt(text);
        }
        return num;
    }

    private int calculate(int num1, int num2, String operator) {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                return num1 / num2;
            default:
                return 0;
        }
    }


}