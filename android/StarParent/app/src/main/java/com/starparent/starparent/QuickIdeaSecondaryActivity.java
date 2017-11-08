package com.starparent.starparent;

import android.content.Intent;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class QuickIdeaSecondaryActivity extends AppCompatActivity {
    //Standard constants
    private static final String TAG = "QuickIdeasMain";
    private final String tag = "quick_ideas";
    private final String xmlFileName = tag + ".xml";
    private final String URL = "http://starparent.com/appdata/" + xmlFileName;

    //Classes we need
    Utils utils = new Utils();
    InputStream stream = null;
    XmlParser parser = new XmlParser();

    protected List<StaticClasses.QuickIdeaTools> quickIdeas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_idea_secondary);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        try {
            parseXml();
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }

        //Creates textviews and sets text by getting quick idea index from intent
        TextView quick_idea_title = (TextView)findViewById(R.id.quick_idea_title);
        TextView quick_idea_goal = (TextView)findViewById(R.id.quick_idea_goal);

        Intent i = getIntent();
        Bundle b = i.getExtras();
        if(b!=null){
            int index = (int)b.get("QuickIdea");
            String idea_title = quickIdeas.get(index).name;
            String idea_goal = quickIdeas.get(index).display;
            quick_idea_title.setText(idea_title);
            quick_idea_goal.setText("Goal: " + idea_goal);

        }

    }

    //This should be usable in every ActivityClass
    private void parseXml() throws XmlPullParserException, IOException {
        if (utils.isNetworkAvailable()) {
            stream = parser.downloadUrl(URL);
        } else {
            stream = this.getAssets().open(xmlFileName);
        }
        try {
            quickIdeas = parser.parse(stream, tag);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}