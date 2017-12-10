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
                filenames.add(file.getName());
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

                        Intent ii = new Intent(ListOfPreviousPSGuide.this, DisplayPreviousPSGuide.class);
                        ii.putExtra("FileName", filename);
                        startActivity(ii);

                        //Toast.makeText(ListOfPreviousPSGuide.this, filename, Toast.LENGTH_SHORT ).show();
                    }
                }
        );
    }


}
