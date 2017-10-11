package com.starparent.starparent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //STAR Process Button
        Button btn_star_process_main = (Button)findViewById(R.id.btn_star_process_main);
        btn_star_process_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StarProcessMainActivity.class));
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

        //Idea Bank / Issues List Button
        Button btn_idea_bank_main = (Button)findViewById(R.id.btn_idea_bank_main);
        btn_idea_bank_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, IdeaBankMainActivity.class));
            }
        });
    }
}
