package com.starparent.starparent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StarPointsMainActivity extends BaseNavigationDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateDrawer();
        getLayoutInflater().inflate(R.layout.activity_star_points_main, frameLayout);
        setTitle("Star Points & Tools");


        Button btnRespond = (Button)findViewById(R.id.btnRespondCooperation);
        btnRespond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StarPointsMainActivity.this, StarPointsActivity.class);
                intent.putExtra("point", 1);
                startActivity(intent);
            }
        });

        Button btnAcknowledge = (Button)findViewById(R.id.btnAcknowledgeFeelings);
        btnAcknowledge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StarPointsMainActivity.this, StarPointsActivity.class);
                intent.putExtra("point", 2);
                startActivity(intent);
            }
        });
        Button btnAvoid = (Button)findViewById(R.id.btnAvoidProblems);
        btnAvoid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StarPointsMainActivity.this, StarPointsActivity.class);
                intent.putExtra("point", 0);
                startActivity(intent);            }
        });
        Button btnLimits = (Button)findViewById(R.id.btnSetLimits);
        btnLimits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StarPointsMainActivity.this, StarPointsActivity.class);
                intent.putExtra("point", 3);
                startActivity(intent);            }
        });
        Button btnTeach = (Button)findViewById(R.id.btnTeachNewSkills);
        btnTeach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StarPointsMainActivity.this, StarPointsActivity.class);
                intent.putExtra("point", 4);
                startActivity(intent);            }
        });
    }
}
