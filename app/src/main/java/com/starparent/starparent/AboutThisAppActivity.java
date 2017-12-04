package com.starparent.starparent;

import android.os.Bundle;

public class AboutThisAppActivity extends BaseNavigationDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateDrawer();
        getLayoutInflater().inflate(R.layout.activity_about_this_app, frameLayout);
        setTitle("About This App");

    }
}
