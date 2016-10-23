/***************************************************
 * Assignment: 06 Prove : Multi-threaded Programming
 *
 * Authors: Dan McDaniel and Brooks Robison
 *
 * Summary: This program will use threads to write to a file, load data
 * from a file, and update a list while monitoring profess on a ProgressBar.
 **************************************************/

package com.mydomain.brooksrobison.multi_thread;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
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

public class MainActivity extends AppCompatActivity {

    private Handler handler;         //to interact with main thread
    private ProgressBar progressBar;
    int progress = 0;

    View mainView;
    ArrayList<String> list = new ArrayList(); //to store value
    ArrayAdapter<String> adapter;             //to load list into listview


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progressBar); //associate progressBar
        handler = new Handler();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, list);
    }

    //Create Method
    public void clickButton1(View v) {
        mainView = v;
        try { clickThread1.start(); } catch (IllegalThreadStateException e) { e.printStackTrace(); }
    }

    //Create Thread
    Thread clickThread1 = new Thread() {
        public void run() {
            String filename = "numbers.txt";
            Context context = mainView.getContext(); //get the context of the main view
            File file = new File(context.getFilesDir(), filename); //create the file there
            FileOutputStream outStream; //stream to write to file
            try {
                outStream = openFileOutput(filename, Context.MODE_PRIVATE); //open the file
                for (Integer i = 1; i <= 10; i++) {                         //write 1 through 10
                    outStream.write(i.toString().concat("\n").getBytes());
                    Thread.sleep(250);
                    progress += 5;                    //increment progress for the progressBar
                    handler.post(new Runnable() {     //use the handler to run a task on main thread
                        @Override
                        public void run() {
                            progressBar.setProgress(progress);                  //update progressBar
                        }
                    });
                }
                    outStream.close(); //close stream and file
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    //Load Event
    public void clickButton2(View v) {
        mainView = v;
        try { clickThread2.start(); } catch (IllegalThreadStateException e) { e.printStackTrace(); }
    }

    //Load Thread
    Thread clickThread2 = new Thread() {
        public void run() {
            String filename = "numbers.txt";
            Context context = mainView.getContext(); //get the context of the main view
            File file = new File(context.getFilesDir(), filename); //create the file there
            FileInputStream inStream; //stream to read from file
            try {
                inStream = openFileInput(filename);  //open the file
                Scanner scanner = new Scanner(file); //create a scanner
                while (scanner.hasNext()) {          //while there's something to read
                    list.add(scanner.nextLine());    //add each line to the list
                    Thread.sleep(250);
                    progress += 5;                   //increment progress for the progressBar
                    handler.post(new Runnable() {    //use the handler to run a task on main thread
                        @Override
                        public void run() {
                            ListView num = (ListView) findViewById(R.id.numLV); //get the listview
                            num.setAdapter(adapter);                            //update listview
                            progressBar.setProgress(progress);                  //update progressBar
                        }
                    });
                }
                inStream.close(); //close stream and file
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    };

    //Clear Method
    public void clickButton3(View v) {
        mainView = v;

        //Reset the list...
        ArrayList<String> list = new ArrayList();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, list);

        adapter.clear(); //empty out the adapter, just in case

        ListView num = (ListView) findViewById(R.id.numLV);
        num.setAdapter(adapter);

        //Reset the progress bar...
        progressBar.setProgress(0);
    }
}


