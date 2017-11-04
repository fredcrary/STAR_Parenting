package com.starparent.starparent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

public class StarResourcesActivity extends AppCompatActivity {
    private StaticClasses.ResourceEntry entry;
    private StaticClasses.ResourceEntry name;
    private StaticClasses.ResourceEntry url;


    private static final String TAG = "ResourcesActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star_resources);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView textView = (TextView) findViewById(R.id.textView);
        Log.d(TAG, "on create started");

        StringBuilder htmlString = new StringBuilder();
        htmlString.append("<p>" + entry + "</p>");
        htmlString.append("<p>" + name + "</p>");
        htmlString.append("<p>" + url + "</p>");
        htmlString.append("<p> test </p>");
        htmlString.append("<p> test </p>");
        htmlString.append("<p> test </p>");
        Log.d(TAG, String.valueOf(htmlString));

        textView.setText(Html.fromHtml(htmlString.toString()));
        textView.setMovementMethod(new ScrollingMovementMethod());

    }

}
