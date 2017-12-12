package com.starparent.starparent;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.tooltip.Tooltip;

public class MainActivity extends BaseNavigationDrawerActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Rendering the pane");
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_main, frameLayout);
        setTitle("STAR Parenting");

        //Tooltip implementation: https://github.com/ViHtarb/Tooltip
        ImageView imageView = (ImageView) findViewById(R.id.help) ;
        Tooltip.Builder builder = new Tooltip.Builder(imageView, R.style.AppTheme)

                .setCancelable(true)
                .setDismissOnClick(false)
                .setArrowHeight(40f )
                .setGravity(Gravity.TOP)
                .setBackgroundColor(Color.argb(210, 255, 255, 255))
                .setTextColor(Color.BLACK)
                .setTextSize(12f)
                .setText(R.string.tooltip_process);

        builder.show();

        Button btnStopFocus = (Button)findViewById(R.id.btnStopFocus);
        btnStopFocus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StarProcessMainActivity.class);
                intent.putExtra("step", 0);
                startActivity(intent);
            }
        });
        Button btnThinkIdeas = (Button)findViewById(R.id.btnThinkIdeas);
        btnThinkIdeas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StarProcessMainActivity.class);
                intent.putExtra("step", 1);
                startActivity(intent);
            }
        });
        Button btnActEffectively = (Button)findViewById(R.id.btnActEffectively);
        btnActEffectively.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StarProcessMainActivity.class);
                intent.putExtra("step", 2);
                startActivity(intent);
            }
        });
        Button btnReviewRevise = (Button)findViewById(R.id.btnReviewRevise);
        btnReviewRevise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StarProcessMainActivity.class);
                intent.putExtra("step", 3);
                startActivity(intent);
            }
        });

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

    }
}
