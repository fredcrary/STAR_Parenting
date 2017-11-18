package com.starparent.starparent;

import android.util.Log;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseNavigationDrawerActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Rendering the pane");
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_main, frameLayout);
        setTitle("Main");

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

        //Splash Button
        Button btn_splash_screen = (Button)findViewById(R.id.btn_splash_screen);
        btn_splash_screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SplashActivity.class));
            }
        });

        //problem solving guide
        Button btn_problem_solving_guide = (Button)findViewById(R.id.btn_problem_solving_guide);
        btn_problem_solving_guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProblemSolvingGuideActivity.class));
            }
        });


    }
}
