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
                    saveData();
                    //String filename = saveData();
                    Toast toast = Toast.makeText(getBaseContext(), "Form saved! Tap the View Previous button to review.", Toast.LENGTH_LONG);
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


    //Takes form data and writes to textfile that is named by capturing the time the save button clicked by user
    public String saveData() throws FileNotFoundException {

        //creates file with name as timestamp
        String filename = new SimpleDateFormat("yyyy.MM.dd.HH:mm:ss").format(new Date());
        FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);

        try{
            //Gathers form data and sets up data to written to textfile
            writeField(fos, R.id.psg_identifier_input);
            writeField(fos, R.id.psg_background_input);

            //Collect data section
            writeField(fos, R.id.childAgeInput);
            writeField(fos, R.id.lengthInput);
            writeField(fos, R.id.frequencyInput);

            //Values
            writeField(fos, R.id.valuesInput1);
            writeField(fos, R.id.valuesInput2);
            writeField(fos, R.id.valuesInput3);

            //Development
            writeField(fos, R.id.developmentInput);

            //Temperament
            writeField(fos, R.id.temperamentInput1);
            writeField(fos, R.id.temperamentInput2);
            writeField(fos, R.id.temperamentInput3);

            //Desired behavior
            writeField(fos, R.id.desiredInput);

            //Think of Ideas
            //Change things
            writeField(fos, R.id.changeThingsInput);
            //Reduce Stress
            writeField(fos, R.id.reduceStressInput);
            //Two yeses
            writeField(fos, R.id.twoYesInput);

            //Attention
            writeField(fos, R.id.attentionInput);
            //Praise
            writeField(fos, R.id.praiseInput);
            //Rewards
            writeField(fos, R.id.rewardsInput);

            //Simple Listening
            writeField(fos, R.id.simpleListenInput);
            //Active listening
            writeField(fos, R.id.ActListenInput);
            //Grant in fantasy
            writeField(fos, R.id.grantFanInput);

            //Clear rules
            writeField(fos, R.id.clearRulesInput);
            //Consequences
            writeField(fos, R.id.consequencesInput);
            //A Better Way
            writeField(fos, R.id.betterWayInput);

            //Modeling
            writeField(fos, R.id.modelInput);
            //Redo it right
            writeField(fos, R.id.redoInput);
            //Shaping
            writeField(fos, R.id.shapingInput);

            //Act Effectively
            //tryFirst
            writeField(fos, R.id.whatChangeInput);
            //roadblocks
            writeField(fos, R.id.roadblocksInput);
            //support
            writeField(fos, R.id.supportInput);
            //make a plan
            writeField(fos, R.id.makePlanInput);
            //how long
            writeField(fos, R.id.howLongInput);

            //Review and Revise
            //wentWell
            writeField(fos, R.id.wentWellInput);
            //whoNeeds
            writeField(fos, R.id.whoNeedsInput);
            //tryNext
            writeField(fos, R.id.tryNextInput);
            //wentWell
            writeField(fos, R.id.futureInput);

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        /*
            //Gathers form data and sets up data to written to textfile
            String fileIdentifier = fetchField(R.id.psg_identifier_input, "Identifier");
            String background = fetchField(R.id.psg_background_input, "Background");

            //Collect data section
            String childAge = fetchField(R.id.childAgeInput, "Child Age");
            String length = fetchField(R.id.lengthInput, "Length of the problem");
            String frequency = fetchField(R.id.frequencyInput, "Frequency of the Behavior");

            //Values
            String values1 = fetchField(R.id.valuesInput1, "1:");
            String values2 = fetchField(R.id.valuesInput2, "2:");
            String values3 = fetchField(R.id.valuesInput3, "3:");

            //Development
            String dev = fetchField(R.id.developmentInput, "What is the child's developmental task?");

            //Temperament
            String temperament1 = fetchField(R.id.temperamentInput1, "1:");
            String temperament2 = fetchField(R.id.temperamentInput2, "2:");
            String temperament3 = fetchField(R.id.temperamentInput3, "3:");

            //Desired behavior
            String desiredbeh = fetchField(R.id.desiredInput, "What you want the child to do instead of what they are doing:");

            //Think of Ideas
            //Change things
            String changeThings = fetchField(R.id.changeThingsInput, "Change Things");
            //Reduce Stress
            String reduceStress = fetchField(R.id.reduceStressInput, "Reduce Stress");
            //Two yeses
            String twoYeses = fetchField(R.id.twoYesInput, "Two yeses");

            //Attention
            String attention = fetchField(R.id.attentionInput, "Attention");
            //Praise
            String praise = fetchField(R.id.praiseInput, "Praise");
            //Rewards
            String rewards = fetchField(R.id.rewardsInput, "Rewards");

            //Simple Listening
            String simple = fetchField(R.id.simpleListenInput, "Simple Listening");
            //Active listening
            String active = fetchField(R.id.ActListenInput, "Active Listening");
            //Grant in fantasy
            String grant = fetchField(R.id.grantFanInput, "Grant in fantasy");

            //Clear rules
            String rules = fetchField(R.id.clearRulesInput, "Clear Rules");
            //Consequences
            String consequences = fetchField(R.id.consequencesInput, "Consequences");
            //betterWay
            String betterWay = fetchField(R.id.betterWayInput, "A Better Way");

            //Modeling
            String model = fetchField(R.id.modelInput, "Model");
            //Redo it right
            String redo = fetchField(R.id.redoInput, "Redo it right");
            //Shaping
            String shaping = fetchField(R.id.shapingInput, "Shaping");

            //Act Effectively
            //tryFirst
            String tryFirst = fetchField(R.id.whatChangeInput, "What will you try first?");
            //roadblocks
            String roadblocks = fetchField(R.id.roadblocksInput, "What might interfere with your success?");
            //support
            String support = fetchField(R.id.supportInput, "How can you protect yourself from the problem?");
            //make a plan
            String makePlan = fetchField(R.id.makePlanInput, "What will you need?");
            //how long
            String howLong = fetchField(R.id.howLongInput, "How long will you contine this plan?");

            //Review and Revise
            //wentWell
            String wentWell = fetchField(R.id.wentWellInput, "What went well?");
            //whoNeeds
            String whoNeeds = fetchField(R.id.whoNeedsInput, "Who needs to change?");
            //tryNext
            String tryNext = fetchField(R.id.tryNextInput, "What will you try next? For how long?");
            //wentWell
            String future = fetchField(R.id.futureInput, "What have you learned for the future?");

        */

        return filename;
    }

    public String fetchField(int field_id, String label){return "";}

    public String fetchField(int field_id) {
        EditText text_input = (EditText)findViewById(field_id);
        String result = text_input.getText().toString();
        result = result.replace("\\", "\\\\").replace("|", "\\|");
        result += "||";
        return result;
    }

    public void writeField(FileOutputStream fos, int fieldID) throws IOException {
        fos.write(fetchField(fieldID).getBytes());
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
