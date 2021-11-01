package com.example.mmm;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Lecture7 extends AppCompatActivity {

    final String METHOD_1 = "Method 1: runOnUiThread";
    final String METHOD_2 = "Method 2: runOnUiThread";
    final String METHOD_3 = "Method 3: AsyncTask";
    final String METHOD_4 = "Lab Task 1: AsyncTask";
    final String LAB_TASK3 = "Lab Task 3: Change color";
    final int COLOR_RED = Color.RED;
    final int COLOR_BLUE = Color.BLUE;
    boolean STOP = false;
    boolean ODD = false;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture7);


        Button lecture7_method1_button = (Button) findViewById(R.id.lecture7_method1_button);
        lecture7_method1_button.setText(METHOD_1);
        lecture7_method1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    int i;

                    @Override
                    public void run() {
                        final TextView label = findViewById(R.id.lecture7_lable_textView);
                        for (i = 1; i <= 5; i++) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            //Method 1: runOnUiThread
                            runOnUiThread(new Runnable() {
                                @RequiresApi(api = Build.VERSION_CODES.O)
                                @Override
                                public void run() {
                                    label.setText(String.join(" ", new String[]{METHOD_1, "loop", String.valueOf(i)}));
                                }
                            });
                        }
                    }
                }).start();

            }
        });


        Button lecture7_method2_button = (Button) findViewById(R.id.lecture7_method2_button);
        lecture7_method2_button.setText(METHOD_2);
        lecture7_method2_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    int i;

                    @Override
                    public void run() {
                        final TextView label = findViewById(R.id.lecture7_lable_textView);
                        for (i = 1; i <= 5; i++) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            //Method 2: view.post(Runnable)
                            label.post(new Runnable() {
                                @RequiresApi(api = Build.VERSION_CODES.O)
                                @Override
                                public void run() {
                                    label.setText(String.join(" ", new String[]{METHOD_2, "loop", String.valueOf(i)}));
                                }
                            });
                        }
                    }
                }).start();

            }
        });

        Button lecture7_method3_button = (Button) findViewById(R.id.lecture7_method3_button);
        lecture7_method3_button.setText(METHOD_3);
        lecture7_method3_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 1; i <= 5; i++) {
                    new subAsyncTask().execute(i);
                }
            }
        });


        Button lecture7_labtask1_button = (Button) findViewById(R.id.lecture7_labtask1_button);
        lecture7_labtask1_button.setText(METHOD_4);
        lecture7_labtask1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 1; i <= 5; i++) {
                    new labTask1().execute(i);
                }
            }
        });


        Button lecture7_labTask3_button = (Button) findViewById(R.id.lecture7_labTask3_button);
        lecture7_labTask3_button.setText(LAB_TASK3);
        ((TextView) findViewById(R.id.lecture7_labTask3_textView1)).setText("Hello");
        ((TextView) findViewById(R.id.lecture7_labTask3_textView2)).setText("World");

        new labTask3().execute();
        STOP = false;
        lecture7_labTask3_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                STOP = true;
            }
        });
    }

    class subAsyncTask extends AsyncTask<Integer, Void, Integer> {
        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p>
         * This will normally run on a background thread. But to better
         * support testing frameworks, it is recommended that this also tolerates
         * direct execution on the foreground thread, as part of the {@link #execute} call.
         * <p>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param integers The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected Integer doInBackground(Integer... integers) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return integers[0];
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        protected void onPostExecute(Integer integer) {
            final TextView label = findViewById(R.id.lecture7_lable_textView);
            label.setText(String.join(" ", new String[]{METHOD_3, "loop", String.valueOf(integer)}));
        }
    }

    class labTask1 extends AsyncTask<Integer, Integer, Integer> {
        @Override
        protected Integer doInBackground(Integer... integers) {
            try {
                Thread.sleep(1000);
                publishProgress(integers[0]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return integers[0];
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        protected void onProgressUpdate(Integer... integers) {
            final TextView label = findViewById(R.id.lecture7_lable_textView);
            label.setText(String.join(" ", new String[]{METHOD_4, "loop", String.valueOf(integers[0])}));
        }
    }

    class labTask3 extends AsyncTask<Integer, Boolean, Void> {
        @Override
        protected Void doInBackground(Integer... integers) {
            while (true) {
                if (STOP) {
                    try {
                        Thread.sleep(5000);
                        STOP = !STOP;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        Thread.sleep(1000);
                        publishProgress(ODD);
                        ODD = !ODD;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        @Override
        protected void onProgressUpdate(Boolean... b) {
            final TextView label1 = findViewById(R.id.lecture7_labTask3_textView1);
            final TextView label2 = findViewById(R.id.lecture7_labTask3_textView2);
            if (b[0]) {
                label1.setTextColor(COLOR_RED);
                label2.setTextColor(COLOR_BLUE);
            } else {
                label1.setTextColor(COLOR_BLUE);
                label2.setTextColor(COLOR_RED);
            }
        }
    }


}