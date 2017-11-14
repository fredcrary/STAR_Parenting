package com.starparent.starparent;

import android.util.Log;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class StarProcessMainActivity extends BaseNavigationDrawerActivity {
    private static final String TAG = "StarProcessMain";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Rendering the pane");
        super.onCreate(savedInstanceState);
        onCreateDrawer();
        getLayoutInflater().inflate(R.layout.activity_star_process_main, frameLayout);
        setTitle("Star Process");
    }
}
