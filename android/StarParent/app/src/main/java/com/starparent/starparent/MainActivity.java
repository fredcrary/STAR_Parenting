package com.starparent.starparent;

import android.text.Html;
import android.util.Log;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Rendering the pane");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Quick Ideas (Magic 8-ball) Button
        Button btn_quick_ideas_main = (Button)findViewById(R.id.btn_quick_ideas_main);
        btn_quick_ideas_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, QuickIdeasMainActivity.class));
            }
        });

        //Problem Solver Button
        Button btn_star_process_main = (Button)findViewById(R.id.btn_problem_solver_main);
        btn_star_process_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProblemSolverMainActivity.class));
            }
        });

        //Learn About Star Parenting Button
        Button btn_learn_about_main = (Button)findViewById(R.id.btn_learn_about_main);
        btn_learn_about_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LearnAboutStarMainActivity.class));
            }
        });

        //Stuff below this line is only here temporarily for easy-access

        StringBuilder htmlString = new StringBuilder();
        htmlString.append("<h2>below here there be dragons</h2>");
        htmlString.append("<hr>");
        TextView textView = (TextView) findViewById(R.id.textViewMain);
        textView.setText(Html.fromHtml(htmlString.toString()));

        //Idea Bank Button (just for now to have an entry point
        Button btn_idea_bank_main = (Button)findViewById(R.id.btn_idea_bank_main);
        btn_idea_bank_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, IdeaBankMainActivity.class));
            }
        });

    }
}
