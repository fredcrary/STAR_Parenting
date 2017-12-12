package com.starparent.starparent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class QuickIdeaSecondaryActivity extends BaseNavigationDrawerActivity {
    //Standard constants
    private static final String TAG = "QuickIdeasMain";
    private final String tag = "quick_ideas";
    private final String xmlFileName = tag + ".xml";
    private final String URL = "http://starparent.com/appdata/" + xmlFileName;

    //Classes we need
    //Utils utils = new Utils();
    InputStream stream = null;
    XmlParser parser = new XmlParser();

    protected List<StaticClasses.QuickIdeaTools> quickIdeas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateDrawer();
        getLayoutInflater().inflate(R.layout.activity_quick_idea_secondary, frameLayout);
        setTitle("Quick Ideas");

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
            quick_idea_goal.setText(idea_goal);
        }

        Button btn_star_points2 =(Button)findViewById(R.id.btn_star_points2);
        btn_star_points2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent ii = new Intent(QuickIdeaSecondaryActivity.this, LearnAboutStarPointsActivity.class);
                startActivity(ii);
            }
        });

    }

    //This should be usable in every ActivityClass
    private void parseXml() throws XmlPullParserException, IOException {
        try {
            boolean networkSuccess = false;
            try {
                Utils network = new Utils(this);
                quickIdeas = network.execute(URL, tag).get();
                networkSuccess = true;
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (!networkSuccess) {
                    quickIdeas = parser.parse(this.getAssets().open(xmlFileName), tag);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}