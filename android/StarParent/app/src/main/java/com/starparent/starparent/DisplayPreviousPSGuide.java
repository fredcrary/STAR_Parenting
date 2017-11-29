package com.starparent.starparent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DisplayPreviousPSGuide extends BaseNavigationDrawerActivity {
    String filename;
    String fileContents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateDrawer();
        getLayoutInflater().inflate(R.layout.activity_display_previous_psguide, frameLayout);
        setTitle("Problem Solving Guide");

        Intent i = getIntent();
        Bundle b = i.getExtras();

        if(b!=null){
            filename = (String)b.get("FileName");
        }

        try {
            fileContents = readFile(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        TextView displayPSGuideHeading = (TextView)findViewById(R.id.displayPSGuideHeading);
        displayPSGuideHeading.setText("Created on:  " + filename);
        TextView displayPSGuide = (TextView)findViewById(R.id.displayPSGuide);
        displayPSGuide.setText(fileContents);

    }

    public String readFile(String filename) throws FileNotFoundException {
        // Read File and Content
        FileInputStream fin = openFileInput(filename);
        int size;
        String neuText = "";

        // read inside if it is not null (-1 means empty)
        try {
            while ((size = fin.read()) != -1) {
                // add & append content
                neuText += Character.toString((char) size);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return neuText;
    }
}
