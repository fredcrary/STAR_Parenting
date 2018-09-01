package com.starparent.starparent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProblemSolvingGuideChooserActivity extends BaseNavigationDrawerActivity {
    private static final String TAG = "ProblemSolvingGuideChooser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_solving_guide_chooser);

        super.onCreate(savedInstanceState);
        onCreateDrawer();
        getLayoutInflater().inflate(R.layout.activity_problem_solver, frameLayout);
        setTitle("Problem Solving Buddy");

        //Quick Ideas Button
        Button btn_quick_ideas = (Button)findViewById(R.id.btn_new_problem);
        btn_quick_ideas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProblemSolvingGuideChooserActivity.this, ProblemSolvingGuideActivity.class));
            }
        });

        //Ideas Bank Button
        Button btn_idea_bank = (Button)findViewById(R.id.btn_old_problem);
        btn_idea_bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProblemSolvingGuideChooserActivity.this, ListOfPreviousPSGuide.class));
            }
        });

        //Problem Solving Guide Button
        Button btn_problem_solving_guide = (Button)findViewById(R.id.btn_share_problem);
        btn_problem_solving_guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProblemSolvingGuideChooserActivity.this, ProblemSolvingGuideActivity.class));
            }
        });

    }
}
