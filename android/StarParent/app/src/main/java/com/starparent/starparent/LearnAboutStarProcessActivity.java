package com.starparent.starparent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class LearnAboutStarProcessActivity extends BaseNavigationDrawerActivity {

    ImageButton learn_about_process_imageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateDrawer();
        getLayoutInflater().inflate(R.layout.activity_learn_about_star_process, frameLayout);
        setTitle("STAR Process");

        //Star Process ImageButton
        learn_about_process_imageBtn = (ImageButton) findViewById(R.id.learn_about_process_imageButton);
        learn_about_process_imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LearnAboutStarProcessActivity.this, StarProcessMainActivity.class));
            }
        });
    }
}
