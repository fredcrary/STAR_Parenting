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

        //Gathers form data and sets up data to written to textfile
        String fileIdentifier = fetchField(R.id.psg_identifier_input, "Identifier");
        String background = fetchField(R.id.psg_background_input, "Background");

        //Collect data section
        String childAge  = fetchField(R.id.childAgeInput, "Child Age");
        String length    = fetchField(R.id.lengthInput,   "Length of the problem");
        String frequency = fetchField(R.id.frequencyInput,"Frequency of the Behavior");

        //Values
        String values1 = fetchField(R.id.valuesInput1, "1:");
        String values2 = fetchField(R.id.valuesInput2, "2:");
        String values3 = fetchField(R.id.valuesInput3, "3:");

        //Development
        String dev = fetchField(R.id.developmentInput,"What is the child's developmental task?");

        //Temperament
        String temperament1 = fetchField(R.id.temperamentInput1,"1:");
        String temperament2 = fetchField(R.id.temperamentInput2,"2:");
        String temperament3 = fetchField(R.id.temperamentInput3,"3:");

        //Desired behavior
        String desiredbeh = fetchField(R.id.desiredInput,"What you want the child to do instead of what they are doing:");

        //Think of Ideas
        //Change things
        String changeThings = fetchField(R.id.changeThingsInput, "Change Things");
        //Reduce Stress
        String reduceStress = fetchField(R.id.reduceStressInput, "Reduce Stress");
        //Two yeses
        String twoYeses = fetchField(R.id.twoYesInput, "Two yeses");

        //Attention
        String attention = fetchField(R.id.attentionInput,"Attention");
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




        //Attempts to write data to text file
        try {
            fos.write(fileIdentifier.getBytes());
            fos.write(background.getBytes());

            fos.write("\nStop and Focus\n\n".getBytes());

            fos.write("\n\nCollect Data\n\n\n".getBytes());
            fos.write(childAge.getBytes());
            fos.write(length.getBytes());
            fos.write(frequency.getBytes());

            fos.write("\n\nValues\n".getBytes());
            fos.write("\n\n\nList three that may be related to the behaviors\n\n\n".getBytes());
            fos.write(values1.getBytes());
            fos.write(values2.getBytes());
            fos.write(values3.getBytes());

            fos.write("\n\nDevelopment\n\n".getBytes());
            fos.write(dev.getBytes());

            fos.write("\n\nTemperament\n".getBytes());
            fos.write("\n\n\nList three traits that might affect this behavior and how intense the traits are\n\n\n".getBytes());
            fos.write(temperament1.getBytes());
            fos.write(temperament2.getBytes());
            fos.write(temperament3.getBytes());

            fos.write("\n\nDesired Behavior\n\n".getBytes());
            fos.write(desiredbeh.getBytes());

            fos.write("\nThink of Ideas\n\n".getBytes());
            fos.write("\n\nAvoid the Problem\n".getBytes());
            fos.write(changeThings.getBytes());
            fos.write(reduceStress.getBytes());
            fos.write(twoYeses.getBytes());

            fos.write("\n\nRespond to cooperation\n".getBytes());
            fos.write(attention.getBytes());
            fos.write(praise.getBytes());
            fos.write(rewards.getBytes());

            fos.write("\n\nAcknowledge Feelings\n".getBytes());
            fos.write(simple.getBytes());
            fos.write(active.getBytes());
            fos.write(grant.getBytes());

            fos.write("\n\nSet Limits\n".getBytes());
            fos.write(rules.getBytes());
            fos.write(consequences.getBytes());
            fos.write(betterWay.getBytes());

            fos.write("\n\nTeach New Skills\n".getBytes());
            fos.write(model.getBytes());
            fos.write(redo.getBytes());
            fos.write(shaping.getBytes());

            fos.write("\nAct Effectively\n\n".getBytes());
            fos.write("\n\nIdeas\n".getBytes());
            fos.write(tryFirst.getBytes());

            fos.write("\n\nRoadblocks\n".getBytes());
            fos.write(roadblocks.getBytes());

            fos.write("\n\nSupport\n".getBytes());
            fos.write(support.getBytes());

            fos.write("\n\nMake a plan\n".getBytes());
            fos.write(makePlan.getBytes());
            fos.write(howLong.getBytes());

            fos.write("\nReview and Revise\n\n".getBytes());;
            fos.write(wentWell.getBytes());
            fos.write(whoNeeds.getBytes());
            fos.write(tryNext.getBytes());
            fos.write(future.getBytes());


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

    public String fetchField(int field_id, String label) {
        String result = label + ": ";
        EditText text_input = (EditText)findViewById(field_id);
        if (TextUtils.isEmpty(text_input.getText()))
            result += "No answer";
        else
            result += text_input.getText();
        result += "\n\n\n";
        return result;
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
