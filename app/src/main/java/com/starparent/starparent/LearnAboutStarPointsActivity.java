package com.starparent.starparent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

public class LearnAboutStarPointsActivity extends AppCompatActivity {

    ImageButton learn_about_points_imageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_about_star_points);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        //Star Process ImageButton
        learn_about_points_imageBtn = (ImageButton) findViewById(R.id.learn_about_points_imageButton);
        learn_about_points_imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LearnAboutStarPointsActivity.this, StarPointsActivity.class));
            }
        });

    }
}
