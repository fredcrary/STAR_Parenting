package com.starparent.starparent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class LearnAboutStarProcessActivity extends BaseNavigationDrawerActivity {

    ImageButton learn_about_process_imageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateDrawer();
        getLayoutInflater().inflate(R.layout.activity_learn_about_star_process, frameLayout);
        setTitle("STAR Process");

        //Create buttons and onClick listeners
        Button btn_star_process =(Button)findViewById(R.id.btn_star_process);
        btn_star_process.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent ii = new Intent(LearnAboutStarProcessActivity.this, MainActivity.class);
                startActivity(ii);
            }
        });

        //Star Process ImageButton
        learn_about_process_imageBtn = (ImageButton) findViewById(R.id.learn_about_process_imageButton);
//        learn_about_process_imageBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(LearnAboutStarProcessActivity.this, MainActivity.class));
//            }
//        });
    }
}
