package com.starparent.starparent;

import android.graphics.Color;
import android.util.Log;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;

import com.tooltip.Tooltip;

public class MainActivity extends BaseNavigationDrawerActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Rendering the pane");
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_main, frameLayout);
        setTitle("STAR Parenting");

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
    }
}
