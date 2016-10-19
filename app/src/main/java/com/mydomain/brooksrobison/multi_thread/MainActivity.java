package com.mydomain.brooksrobison.multi_thread;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static com.mydomain.brooksrobison.multi_thread.R.id.progressBar;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    View mainView;
    ArrayList<String> list = new ArrayList();
    int progress = 0;

    public void clickButton1(View v) {

        mainView = v;
        clickThread1.start();

        ProgressBar pBar = (ProgressBar) findViewById(progressBar);
        while(progress < 50) {
            pBar.setProgress(progress);
        }
    }

    Thread clickThread1 = new Thread() {

        public void run() {

            String filename = "numbers.txt";
            Context context = mainView.getContext();
            File file = new File(context.getFilesDir(), filename);

            FileOutputStream outStream;

            try {
                outStream = openFileOutput(filename, Context.MODE_PRIVATE);
                for (Integer i = 1; i <= 10; i++) {
                    outStream.write(i.toString().concat("\n").getBytes());
                    Thread.sleep(250);
                    progress += 5;
                }
                outStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    };

    public void clickButton2(View v) {

        mainView = v;
        clickThread2.start();

        ProgressBar pBar = (ProgressBar) findViewById(progressBar);
        while(progress < 100) {
            pBar.setProgress(progress);
        }

        try {
            clickThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, list);

        ListView num = (ListView) findViewById(R.id.numLV);
        num.setAdapter(adapter);
    }

    Thread clickThread2 = new Thread() {

        public void run() {

            String filename = "numbers.txt";
            Context context = mainView.getContext();
            File file = new File(context.getFilesDir(), filename);

            FileInputStream inStream;

            try {
                inStream = openFileInput(filename);
                Scanner scanner = new Scanner(file);
                while (scanner.hasNext()) {
                    list.add(scanner.nextLine());
                    Thread.sleep(250);
                    progress += 5;
                }
                inStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }



        }

    };

    public void clickButton3(View v) {

        ArrayList<String> list = new ArrayList();


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, list);

        adapter.clear();

        ListView num = (ListView) findViewById(R.id.numLV);
        num.setAdapter(adapter);
    }

    }


