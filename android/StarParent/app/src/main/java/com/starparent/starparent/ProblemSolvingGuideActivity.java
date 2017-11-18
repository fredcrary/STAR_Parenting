package com.starparent.starparent;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ProblemSolvingGuideActivity extends BaseNavigationDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateDrawer();
        getLayoutInflater().inflate(R.layout.activity_problem_solving_guide, frameLayout);
        setTitle("Problem Solving Guide");

        //save button
        Button ps_guide_save_btn = (Button)findViewById(R.id.ps_guide_save_btn);
        ps_guide_save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //display in long period of time
                Toast.makeText(getApplicationContext(), "Form has been saved",
                        Toast.LENGTH_LONG).show();
            }
        });

        //next button
        Button ps_guide_next_btn = (Button)findViewById(R.id.ps_guide_next_btn);
        ps_guide_next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProblemSolvingGuideActivity.this, PS_Guide_StopFocus.class));
            }
        });
    }

}
