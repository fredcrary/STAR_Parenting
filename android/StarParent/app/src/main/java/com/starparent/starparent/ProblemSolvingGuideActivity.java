package com.starparent.starparent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import net.cachapa.expandablelayout.ExpandableLayout;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ProblemSolvingGuideActivity extends BaseNavigationDrawerActivity {
    private static final String TAG = "ProblemSolvingGuide";

    private ExpandableLayout expandableLayout1, expandableLayout2, expandableLayout3, expandableLayout4;
    private Button psg_save_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Rendering the pane");
        super.onCreate(savedInstanceState);
        onCreateDrawer();
        getLayoutInflater().inflate(R.layout.activity_problem_solving_guide, frameLayout);
        setTitle("Problem Solving Guide");

        //sets up references to expanding/collapsing sections
        expandableLayout1 = (ExpandableLayout) findViewById(R.id.expandableLayout1);
        expandableLayout2 = (ExpandableLayout) findViewById(R.id.expandableLayout2);
        expandableLayout3 = (ExpandableLayout) findViewById(R.id.expandableLayout3);
        expandableLayout4 = (ExpandableLayout) findViewById(R.id.expandableLayout4);


        psg_save_btn =(Button)findViewById(R.id.psg_save_btn);
        psg_save_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    String filename = saveData();
                    Toast toast = Toast.makeText(getBaseContext(), readFile(filename), Toast.LENGTH_SHORT);
                    toast.show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        Button psg_previous =(Button)findViewById(R.id.psg_previous);
        psg_previous.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent ii = new Intent(ProblemSolvingGuideActivity.this, ListOfPreviousPSGuide.class);
                startActivity(ii);
            }
        });

    }

    public String saveData() throws FileNotFoundException {
        String filename = new SimpleDateFormat("yyyy.MM.dd.HH:mm:ss").format(new Date());
        FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);

        String background;
        EditText psg_background_input = (EditText)findViewById(R.id.psg_background_input);
        if (TextUtils.isEmpty(psg_background_input.getText())) {
            background =
                    "Background:\n\n" + "No answer " + "\n\n\n";
        }else{
            background =
                    "Background:\n\n" + psg_background_input.getText().toString() + "\n\n\n";
        }

        String childAge;
        EditText childAgeInput = (EditText)findViewById(R.id.childAgeInput);
        if (TextUtils.isEmpty(childAgeInput.getText())) {
            childAge =
                    "Child Age:\n\n" + "No answer " + "\n\n\n";
        }else{
            childAge =
                    "Child Age:\n\n" + childAgeInput.getText().toString() + "\n\n\n";
        }

        String length;
        EditText lengthInput = (EditText)findViewById(R.id.lengthInput);
        if (TextUtils.isEmpty(lengthInput.getText())) {
            length =
                    "Length of the problem:\n\n" + "No answer " + "\n\n\n";
        }else{
            length =
                    "Length of the problem:\n\n" + lengthInput.getText().toString() + "\n\n\n";
        }

        String frequency;
        EditText frequencyInput = (EditText)findViewById(R.id.frequencyInput);
        if (TextUtils.isEmpty(frequencyInput.getText())) {
            frequency =
                    "Frequency of the Behavior:\n\n" + "No answer " + "\n\n\n";
        }else{
            frequency =
                    "Frequency of the Behavior:\n\n" + frequencyInput.getText().toString() + "\n\n\n";
        }




        try {
            fos.write(background.getBytes());
            fos.write("\n\n\nCollect Data\n\n\n".getBytes());
            fos.write(childAge.getBytes());
            fos.write(length.getBytes());
            fos.write(frequency.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filename;
    }

    public String readFile(String filename) throws FileNotFoundException {
        // Read File and Content
        FileInputStream fin = openFileInput(filename);
        Log.d(TAG, "Filename: " + filename);
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

    //methods expand & collapse different sections of the guide based on user click
    public void expandableButton1(View view) {
        expandableLayout1.toggle(); // toggle expand and collapse
        if(expandableLayout2.isExpanded()){
            expandableLayout2.collapse();
        }
        if(expandableLayout3.isExpanded()){
            expandableLayout3.collapse();
        }
        if(expandableLayout4.isExpanded()){
            expandableLayout4.collapse();
        }
    }
    public void expandableButton2(View view) {
        expandableLayout2.toggle(); // toggle expand and collapse
        if(expandableLayout1.isExpanded()){
            expandableLayout1.collapse();
        }
        if(expandableLayout3.isExpanded()){
            expandableLayout3.collapse();
        }
        if(expandableLayout4.isExpanded()){
            expandableLayout4.collapse();
        }
    }

    public void expandableButton3(View view) {
        expandableLayout3.toggle(); // toggle expand and collapse
        if(expandableLayout2.isExpanded()){
            expandableLayout2.collapse();
        }
        if(expandableLayout4.isExpanded()){
            expandableLayout4.collapse();
        }
        if(expandableLayout4.isExpanded()){
            expandableLayout4.collapse();
        }
    }
    public void expandableButton4(View view) {
        expandableLayout4.toggle(); // toggle expand and collapse
        if(expandableLayout2.isExpanded()){
            expandableLayout2.collapse();
        }
        if(expandableLayout3.isExpanded()){
            expandableLayout3.collapse();
        }
        if(expandableLayout1.isExpanded()){
            expandableLayout1.collapse();
        }
    }


}
