package com.starparent.starparent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class ProblemSolverMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_solver);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Process Button
        Button btn_quick_ideas = (Button)findViewById(R.id.btn_quick_ideas);
        btn_quick_ideas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProblemSolverMainActivity.this, QuickIdeasMainActivity.class));
            }
        });

        //Points Button
        Button btn_idea_bank = (Button)findViewById(R.id.btn_idea_bank);
        btn_idea_bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProblemSolverMainActivity.this, IdeaBankMainActivity.class));
            }
        });

        //Points Button
        Button btn_problem_solving_guide = (Button)findViewById(R.id.btn_problem_solving_guide);
        btn_problem_solving_guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProblemSolverMainActivity.this, ProblemSolvingGuideActivity.class));
            }
        });


    }

}
