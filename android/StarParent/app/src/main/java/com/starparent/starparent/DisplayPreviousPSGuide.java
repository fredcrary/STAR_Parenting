package com.starparent.starparent;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
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

        Button psgDelete =(Button)findViewById(R.id.psgDelete);
        psgDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog diaBox = AskOption();
                diaBox.show();
            }
        });

        Button psgShare =(Button)findViewById(R.id.psgShare);
        psgShare.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sendEmail();
            }
        });


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

    private AlertDialog AskOption()
    {
        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
                //set message, title, and icon
                //.setTitle("Delete")
                .setMessage("Are you sure you want to delete?")
                //.setIcon(R.layout.delete)

                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        //your deleting code
                        File dir = getFilesDir();
                        File file = new File(dir, filename);
                        file.delete();
                        dialog.dismiss();
                        Intent ii = new Intent(DisplayPreviousPSGuide.this, ListOfPreviousPSGuide.class);
                        startActivity(ii);
                    }

                })



                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .create();
        return myQuittingDialogBox;

    }

    protected void sendEmail() {
        String[] TO = {""};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Star Parent Problem Solving Guide");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Created on " + filename + "\n\n" + fileContents + "\n\n");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(DisplayPreviousPSGuide.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
