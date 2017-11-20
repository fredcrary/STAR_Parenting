package com.starparent.starparent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LearnAboutStarMainActivity extends BaseNavigationDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateDrawer();
        getLayoutInflater().inflate(R.layout.activity_learn_about_star, frameLayout);
        setTitle("Learn About Star");

        //Process Button
        Button learn_about_process_btn = (Button)findViewById(R.id.learn_about_process_btn);
        learn_about_process_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LearnAboutStarMainActivity.this, LearnAboutStarProcessActivity.class));
            }
        });

        //Points Button
        Button learn_about_points_btn = (Button)findViewById(R.id.learn_about_points_btn);
        learn_about_points_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LearnAboutStarMainActivity.this, LearnAboutStarPointsActivity.class));
            }
        });



    }

}
