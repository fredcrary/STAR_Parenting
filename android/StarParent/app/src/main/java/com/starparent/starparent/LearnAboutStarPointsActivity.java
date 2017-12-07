package com.starparent.starparent;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.tooltip.Tooltip;

public class LearnAboutStarPointsActivity extends BaseNavigationDrawerActivity {

    ImageButton learn_about_points_imageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateDrawer();
        getLayoutInflater().inflate(R.layout.activity_learn_about_star_points, frameLayout);
        setTitle("STAR Points & Tools");

        //Star Process ImageButton
        learn_about_points_imageBtn = (ImageButton) findViewById(R.id.learn_about_points_imageButton);

        //Tooltip implementation: https://github.com/ViHtarb/Tooltip
        ImageView imageView = (ImageView) findViewById(R.id.helpView) ;
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

        learn_about_points_imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LearnAboutStarPointsActivity.this, StarPointsActivity.class));
            }
        });

    }
}
