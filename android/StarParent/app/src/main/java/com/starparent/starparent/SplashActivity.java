package com.starparent.starparent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SplashActivity extends BaseNavigationDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateDrawer();
        getLayoutInflater().inflate(R.layout.activity_splash, frameLayout);
        setTitle("STAR Parenting");

        final Button continueButton = (Button) findViewById(R.id.btn_continue);
        continueButton.setVisibility(View.INVISIBLE);
        continueButton.postDelayed(new Runnable() {
            public void run() {
                continueButton.setVisibility(View.VISIBLE);
            }
        }, 5000);

    }
    // Continue to Main Activity
    public void continueButton(View view){
        Intent continueIntent = new Intent(this,MainActivity.class);
        startActivity(continueIntent);
    }
}
