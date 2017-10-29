package com.starparent.starparent;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class LearnAboutStarMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_about_star);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Process Button
        Button learn_about_process_btn = (Button)findViewById(R.id.learn_about_process_btn);
        learn_about_process_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LearnAboutStarMainActivity.this, StarProcessMainActivity.class));
            }
        });

        //Points Button
        Button learn_about_points_btn = (Button)findViewById(R.id.learn_about_points_btn);
        learn_about_points_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LearnAboutStarMainActivity.this, StarPointsActivity.class));
            }
        });

        //Points Button
        Button learn_about_resources_btn = (Button)findViewById(R.id.learn_about_resources_btn);
        learn_about_resources_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LearnAboutStarMainActivity.this, StarResourcesActivity.class));
            }
        });


    }

}
