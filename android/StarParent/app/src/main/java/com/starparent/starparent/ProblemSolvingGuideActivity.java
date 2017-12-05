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
        String background;
        EditText psg_background_input = (EditText)findViewById(R.id.psg_background_input);
        if (TextUtils.isEmpty(psg_background_input.getText())) {
            background =
                    "Background:\n\n" + "No answer " + "\n\n\n";
        }else{
            background =
                    "Background:\n\n" + psg_background_input.getText().toString() + "\n\n\n";
        }

        //Collect data section
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

        //Values
        String values1;
        EditText valuesInput1 = (EditText)findViewById(R.id.valuesInput1);
        if (TextUtils.isEmpty(valuesInput1.getText())) {
            values1 =
                    "1: " + "No answer " + "\n\n\n";
        }else{
            values1 =
                    "1: " + valuesInput1.getText().toString() + "\n\n\n";
        }
        String values2;
        EditText valuesInput2 = (EditText)findViewById(R.id.valuesInput2);
        if (TextUtils.isEmpty(valuesInput2.getText())) {
            values2 =
                    "2: " + "No answer " + "\n\n\n";
        }else{
            values2 =
                    "2: " + valuesInput2.getText().toString() + "\n\n\n";
        }
        String values3;
        EditText valuesInput3 = (EditText)findViewById(R.id.valuesInput3);
        if (TextUtils.isEmpty(valuesInput3.getText())) {
            values3 =
                    "3: " + "No answer " + "\n\n\n";
        }else{
            values3 =
                    "3: " + valuesInput3.getText().toString() + "\n\n\n";
        }

        //Development
        String dev;
        EditText developmentInput = (EditText)findViewById(R.id.developmentInput);
        if (TextUtils.isEmpty(developmentInput.getText())) {
            dev =
                    "What is the child's developmental task?:\n\n" + "No answer " + "\n\n\n";
        }else{
            dev =
                    "What is the child's developmental task?:\n\n" + developmentInput.getText().toString() + "\n\n\n";
        }


        //Temperament
        String temperament1;
        EditText temperamentInput1 = (EditText)findViewById(R.id.temperamentInput1);
        if (TextUtils.isEmpty(temperamentInput1.getText())) {
            temperament1 =
                    "1: " + "No answer " + "\n\n\n";
        }else{
            temperament1 =
                    "1: " + temperamentInput1.getText().toString() + "\n\n\n";
        }
        String temperament2;
        EditText temperamentInput2 = (EditText)findViewById(R.id.temperamentInput2);
        if (TextUtils.isEmpty(temperamentInput2.getText())) {
            temperament2 =
                    "2: " + "No answer " + "\n\n\n";
        }else{
            temperament2 =
                    "2: " + temperamentInput2.getText().toString() + "\n\n\n";
        }
        String temperament3;
        EditText temperamentInput3 = (EditText)findViewById(R.id.temperamentInput3);
        if (TextUtils.isEmpty(temperamentInput3.getText())) {
            temperament3 =
                    "3: " + "No answer " + "\n\n\n";
        }else{
            temperament3 =
                    "3: " + temperamentInput3.getText().toString() + "\n\n\n";
        }

        //Desired behavior
        String desiredbeh;
        EditText desiredInput = (EditText)findViewById(R.id.desiredInput);
        if (TextUtils.isEmpty(desiredInput.getText())) {
            desiredbeh =
                    "What you want the child to do instead of what they are doing:\n\n" + "No answer " + "\n\n\n";
        }else{
            desiredbeh =
                    "What you want the child to do instead of what they are doing\n\n" + desiredInput.getText().toString() + "\n\n\n";
        }

        //Think of Ideas
        //Change things
        String changeThings;
        EditText changeThingsInput = (EditText)findViewById(R.id.changeThingsInput);
        if (TextUtils.isEmpty(changeThingsInput.getText())) {
            changeThings =
                    "Change Things\n\n" + "No answer " + "\n\n\n";
        }else{
            changeThings =
                    "Change Things\n\n" + changeThingsInput.getText().toString() + "\n\n\n";
        }

        //Reduce Stress
        String reduceStress;
        EditText reduceStressInput = (EditText)findViewById(R.id.reduceStressInput);
        if (TextUtils.isEmpty(reduceStressInput.getText())) {
            reduceStress =
                    "Reduce Stress\n\n" + "No answer " + "\n\n\n";
        }else{
            reduceStress =
                    "Reduce Stress\n\n" + reduceStressInput.getText().toString() + "\n\n\n";
        }

        //Two yeses
        String twoYeses;
        EditText twoYesInput = (EditText)findViewById(R.id.twoYesInput);
        if (TextUtils.isEmpty(twoYesInput.getText())) {
            twoYeses =
                    "Two yeses\n\n" + "No answer " + "\n\n\n";
        }else{
            twoYeses =
                    "Two yeses\n\n" + twoYesInput.getText().toString() + "\n\n\n";
        }

        //Attention
        String attention;
        EditText attentionInput = (EditText)findViewById(R.id.attentionInput);
        if (TextUtils.isEmpty(attentionInput.getText())) {
            attention =
                    "Attention\n\n" + "No answer " + "\n\n\n";
        }else{
            attention =
                    "Attention\n\n" + attentionInput.getText().toString() + "\n\n\n";
        }

        //Praise
        String praise;
        EditText praiseInput = (EditText)findViewById(R.id.praiseInput);
        if (TextUtils.isEmpty(praiseInput.getText())) {
            praise =
                    "Praise\n\n" + "No answer " + "\n\n\n";
        }else{
            praise =
                    "Praise\n\n" + praiseInput.getText().toString() + "\n\n\n";
        }

        //Rewards
        String rewards;
        EditText rewardsInput = (EditText)findViewById(R.id.rewardsInput);
        if (TextUtils.isEmpty(rewardsInput.getText())) {
            rewards =
                    "Rewards\n\n" + "No answer " + "\n\n\n";
        }else{
            rewards =
                    "Rewards\n\n" + rewardsInput.getText().toString() + "\n\n\n";
        }

        //Simple Listening
        String simple;
        EditText simpleListenInput = (EditText)findViewById(R.id.simpleListenInput);
        if (TextUtils.isEmpty(simpleListenInput.getText())) {
            simple =
                    "Simple Listening\n\n" + "No answer " + "\n\n\n";
        }else{
            simple =
                    "Simple Listening\n\n" + simpleListenInput.getText().toString() + "\n\n\n";
        }
        //Active listening
        String active;
        EditText ActListenInput = (EditText)findViewById(R.id.ActListenInput);
        if (TextUtils.isEmpty(ActListenInput.getText())) {
            active =
                    "Active Listening\n\n" + "No answer " + "\n\n\n";
        }else{
            active =
                    "Active Listenin\n\n" + ActListenInput.getText().toString() + "\n\n\n";
        }
        //Grant in fantasy
        String grant;
        EditText grantFanInput = (EditText)findViewById(R.id.grantFanInput);
        if (TextUtils.isEmpty(grantFanInput.getText())) {
            grant =
                    "Grant in fantasy\n\n" + "No answer " + "\n\n\n";
        }else{
            grant =
                    "Grant in fantasy\n\n" + grantFanInput.getText().toString() + "\n\n\n";
        }

        //clear rules
        String rules;
        EditText clearRulesInput = (EditText)findViewById(R.id.grantFanInput);
        if (TextUtils.isEmpty(clearRulesInput.getText())) {
            rules =
                    "Clear Rules\n\n" + "No answer " + "\n\n\n";
        }else{
            rules =
                    "Clear Rules\n\n" + clearRulesInput.getText().toString() + "\n\n\n";
        }
        //Grant in fantasy
        String consequences;
        EditText consequencesInput = (EditText)findViewById(R.id.consequencesInput);
        if (TextUtils.isEmpty(consequencesInput.getText())) {
            consequences =
                    "Consequences\n\n" + "No answer " + "\n\n\n";
        }else{
            consequences =
                    "Consequences\n\n" + consequencesInput.getText().toString() + "\n\n\n";
        }
        //betterWay
        String betterWay;
        EditText betterWayInput = (EditText)findViewById(R.id.betterWayInput);
        if (TextUtils.isEmpty(betterWayInput.getText())) {
            betterWay =
                    "A Better Way\n\n" + "No answer " + "\n\n\n";
        }else{
            betterWay =
                    "A Better Way\n\n" + betterWayInput.getText().toString() + "\n\n\n";
        }

        //model
        String model;
        EditText modelInput = (EditText)findViewById(R.id.modelInput);
        if (TextUtils.isEmpty(modelInput.getText())) {
            model =
                    "Model\n\n" + "No answer " + "\n\n\n";
        }else{
            model =
                    "Model\n\n" + modelInput.getText().toString() + "\n\n\n";
        }
        //Redo it right
        String redo;
        EditText redoInput = (EditText)findViewById(R.id.redoInput);
        if (TextUtils.isEmpty(redoInput.getText())) {
            redo =
                    "Redo it right\n\n" + "No answer " + "\n\n\n";
        }else{
            redo =
                    "Redo it right\n\n" + redoInput.getText().toString() + "\n\n\n";
        }
        //Shaping
        String shaping;
        EditText shapingInput = (EditText)findViewById(R.id.shapingInput);
        if (TextUtils.isEmpty(shapingInput.getText())) {
            shaping =
                    "Shaping\n\n" + "No answer " + "\n\n\n";
        }else{
            shaping =
                    "Shaping\n\n" + shapingInput.getText().toString() + "\n\n\n";
        }

        //Act Effectively
        //tryFirst
        String tryFirst;
        EditText whatChangeInput = (EditText)findViewById(R.id.whatChangeInput);
        if (TextUtils.isEmpty(whatChangeInput.getText())) {
            tryFirst =
                    "What will you try first?\n\n" + "No answer " + "\n\n\n";
        }else{
            tryFirst =
                    "What will you try first?\n\n" + whatChangeInput.getText().toString() + "\n\n\n";
        }

        //roadblocks
        String roadblocks;
        EditText roadblocksInput = (EditText)findViewById(R.id.roadblocksInput);
        if (TextUtils.isEmpty(roadblocksInput.getText())) {
            roadblocks =
                    "What might interfere with your success?\n\n" + "No answer " + "\n\n\n";
        }else{
            roadblocks =
                    "What might interfere with your success?\n\n" + whatChangeInput.getText().toString() + "\n\n\n";
        }

        //support
        String support;
        EditText supportInput = (EditText)findViewById(R.id.supportInput);
        if (TextUtils.isEmpty(supportInput.getText())) {
            support =
                    "How can you protect yourself from the problem?\n\n" + "No answer " + "\n\n\n";
        }else{
            support =
                    "How can you protect yourself from the problem?\n\n" + supportInput.getText().toString() + "\n\n\n";
        }

        //make a plan
        String makePlan;
        EditText makePlanInput = (EditText)findViewById(R.id.makePlanInput);
        if (TextUtils.isEmpty(makePlanInput.getText())) {
            makePlan =
                    "What will you need?\n\n" + "No answer " + "\n\n\n";
        }else{
            makePlan =
                    "What will you need?\n\n" + makePlanInput.getText().toString() + "\n\n\n";
        }
        //how long
        String howLong;
        EditText howLongInput = (EditText)findViewById(R.id.howLongInput);
        if (TextUtils.isEmpty(howLongInput.getText())) {
            howLong =
                    "How long will you contine this plan?\n\n" + "No answer " + "\n\n\n";
        }else{
            howLong =
                    "How long will you contine this plan?\n\n" + howLongInput.getText().toString() + "\n\n\n";
        }

        //Review and Revise
        //wentWell
        String wentWell;
        EditText wentWellInput = (EditText)findViewById(R.id.wentWellInput);
        if (TextUtils.isEmpty(wentWellInput.getText())) {
            wentWell = "What went well?\n\n" + "No answer " + "\n\n\n";
        }else{
            wentWell = "What went well?\n\n" + wentWellInput.getText().toString() + "\n\n\n";
        }
        //whoNeeds
        String whoNeeds;
        EditText whoNeedsInput = (EditText)findViewById(R.id.whoNeedsInput);
        if (TextUtils.isEmpty(whoNeedsInput.getText())) {
            whoNeeds = "Who needs to change?\n\n" + "No answer " + "\n\n\n";
        }else{
            whoNeeds = "Who needs to change?\n\n" + whoNeedsInput.getText().toString() + "\n\n\n";
        }
        //tryNext
        String tryNext;
        EditText tryNextInput = (EditText)findViewById(R.id.tryNextInput);
        if (TextUtils.isEmpty(tryNextInput.getText())) {
            tryNext = "What will you try next? For how long?\n\n" + "No answer " + "\n\n\n";
        }else{
            tryNext = "What will you try next? For how long?\n\n" + tryNextInput.getText().toString() + "\n\n\n";
        }
        //wentWell
        String future;
        EditText futureInput = (EditText)findViewById(R.id.futureInput);
        if (TextUtils.isEmpty(futureInput.getText())) {
            future = "What have you learned for the future?\n\n" + "No answer " + "\n\n\n";
        }else{
            future = "What have you learned for the future?\n\n" + futureInput.getText().toString() + "\n\n\n";
        }




        //Attempts to write data to text file
        try {
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
