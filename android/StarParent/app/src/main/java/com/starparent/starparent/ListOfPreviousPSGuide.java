package com.starparent.starparent;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ListOfPreviousPSGuide extends BaseNavigationDrawerActivity {
    private static final String TAG = "ListOfPreviousPSGuide";

    ListView listOfFilenames;
    ArrayList<String> filenames = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateDrawer();
        getLayoutInflater().inflate(R.layout.activity_list_of_previous_psguide, frameLayout);
        setTitle("Problem Solving Guide");

        File dir = getFilesDir();
        File[] subFiles = dir.listFiles();

        if (subFiles != null)
        {
            for (File file : subFiles)
            {
                Log.d(TAG, file.getName());
                filenames.add(getFileIdentifier(file.getName()) + " (" + file.getName() + ")");
            }
        }

        listOfFilenames = (ListView)findViewById(R.id.displayPSGlistView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, filenames);
        listOfFilenames.setAdapter(adapter);

        listOfFilenames.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String filename = String.valueOf(adapterView.getItemAtPosition(i));
                        filename = filename.substring(filename.length()-20, filename.length()-1);

                        Intent ii = new Intent(ListOfPreviousPSGuide.this, DisplayPreviousPSGuide.class);
                        ii.putExtra("FileName", filename);
                        startActivity(ii);

                        //Toast.makeText(ListOfPreviousPSGuide.this, filename, Toast.LENGTH_SHORT ).show();
                    }
                }
        );
    }

    private String getFileIdentifier(String filename){
        // Reads the beginning of the file to extract the first field
        String fileContents;
        try {
            fileContents = readFile(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "Identifier unavailable";
        }
        int bar = fileContents.indexOf("||");
        if( fileContents.charAt(bar-1) == '\\') bar++;
        String result = fileContents.substring(0,bar);
        return result.replace("\\|", "|").replace("\\\\", "\\");
    }

    public String readFile(String filename) throws FileNotFoundException {
        // Read File and Content
        //      ===> It would be preferable if this were available as a utility
        FileInputStream fin = openFileInput(filename);
        int size;
        String neuText = "";

        // read inside if it is not null (-1 means empty)
        // only read enough to get the first field (the identifier)
        try {
            while ((size = fin.read()) != -1 && !neuText.contains("||")) {
                // add & append content
                neuText += Character.toString((char) size);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return neuText;
    }



}
