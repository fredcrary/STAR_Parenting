package com.starparent.starparent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DisplayPreviousPSGuide extends BaseNavigationDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateDrawer();
        getLayoutInflater().inflate(R.layout.activity_display_previous_psguide, frameLayout);
        setTitle("Previous Problem Solving Guide");

        TextView psg_display = (TextView)findViewById(R.id.psg_display);
        try {
            psg_display.setText(readFile());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String readFile() throws FileNotFoundException {
        // Read File and Content
        FileInputStream fin = openFileInput("ProblemSolvingGuide");
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
