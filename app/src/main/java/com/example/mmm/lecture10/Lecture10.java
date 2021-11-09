package com.example.mmm.lecture10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mmm.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Lecture10 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture10);
        String fileName = "myfile";

        //internal memory file
        writeInternalFile(fileName);
        readInternalFile(fileName);

        //external memory file
        writeExternalFile(fileName);
        readExternalFile(fileName);


        //Shared Preferences
        SharedPreferences prefs = getSharedPreferences("Phone", MODE_PRIVATE);
//        String number = prefs.getString("new_number", "empty");
        String number = String.valueOf(prefs.getInt("new_number", 0));

        TextView viewById = findViewById(R.id.lecture10_number_textView);
        viewById.setText(number);

        Button callBut = (Button) findViewById(R.id.lecture10_call_button);
        callBut.setText("Call");
        callBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBroadcast(new Intent("Call"));
                updateNumber();
            }
        });
        Lecture10Receiver lecture10Receiver = new Lecture10Receiver();
        registerReceiver(lecture10Receiver, new IntentFilter("Call"));


        //database
        Lecture10SQLite db = new Lecture10SQLite(this);
        db.addContact(new Lecture10Contact(1012, "Alice", "88160000"));
        db.addContact(new Lecture10Contact(913, "Bob", "88160001"));
// Reading contact
        Lecture10Contact contact = db.getContact(1);
        String log = "Id: "+ contact.getID()+" ,Name: " + contact.getName() + " ,Phone: " +
                contact.getPhoneNumber();
// Writing Contacts to log
        Log.d("Name: ", log);

    }

    private void updateNumber() {
        //Shared Preferences
        SharedPreferences prefs = getSharedPreferences("Phone", MODE_PRIVATE);
//        String number = prefs.getString("new_number", "empty");
        String number = String.valueOf(prefs.getInt("new_number", 0));

        TextView viewById =findViewById(R.id.lecture10_number_textView);
        viewById.setText(number);
    }

    private void readExternalFile(String fileName) {
    }

    private void writeExternalFile(String fileName) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            File file = new File(getExternalFilesDir(null), fileName);
            try {
                FileOutputStream fo = new FileOutputStream(file, true);
                String writeToFile = "hello world";
                fo.write(writeToFile.getBytes());
                fo.close();
            } catch (IOException e) {
            }
        }
    }

    private void writeInternalFile(String fileName) {
        String inputString = "hello";
        try {
            FileOutputStream outputStr =
                    openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStr.write(inputString.getBytes());
            outputStr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readInternalFile(String fileName) {
        try {
            FileInputStream inputStr = openFileInput(fileName);
            InputStreamReader strRe = new InputStreamReader(inputStr);
            BufferedReader bufRe = new BufferedReader(strRe);
// reads toward a new line character and move the reader pointer to the beginning of the next line
            bufRe.readLine();
            inputStr.close(); // close the file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
