package com.starparent.starparent;

import android.text.Html;
import android.util.Log;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import com.starparent.starparent.StaticClasses.*;


public class IdeaBankMainActivity extends AppCompatActivity {
    private static final String TAG = "IdeaBankMain";
    private final String URL = "http://starparent.com/appdata/ideas_bank.xml";
    private final String tag = "ideas_bank";
    InputStream stream = null;
    XmlParser parser = new XmlParser();
    List<IdeasBankProblem> problems;
    //TODO: When both data and network are reliable, check for network status and try to download from source
    boolean isNetworkAvailable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Rendering the pane");
        //Instantiate the pane
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idea_bank_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView textView = new TextView(this);
        setContentView(textView);

        //Do stuff!
        try {
            parseXml();
        } catch (XmlPullParserException  | IOException e) {
            e.printStackTrace();
        }
        if (problems != null) {
            StringBuilder htmlString = new StringBuilder();
            for (IdeasBankProblem problem : problems) {
                htmlString.append("<h2>" + problem.title + "</h2>");
                htmlString.append("<h4>Description: </h4>");
                htmlString.append("<p>" + problem.description + "</p>");
                htmlString.append("<h4>Goal: </h4>");
                htmlString.append("<p>" + problem.goal + "</p>");
                htmlString.append("<h4>Reality Check: </h4>");
                htmlString.append("<p>" + problem.reality_check + "</p>");
                htmlString.append("<h3>Ideas: </h3>");
                for (IdeasBankIdea idea : problem.ideas) {
                    htmlString.append("<h4>" + idea.star_point + "</h4>");
                    htmlString.append("<p>" + idea.idea_text + "</p>");
                }
            }
            textView.setText(Html.fromHtml(htmlString.toString()));
            Log.d(TAG, htmlString.toString());
        }

    }

    //This should be usable in every ActivityClass
    private void parseXml() throws XmlPullParserException, IOException {
        if (isNetworkAvailable) {
            stream = parser.downloadUrl(URL);
        } else {
            stream = this.getAssets().open("ideas_bank.xml");
        }
        try {
            problems = parser.parse(stream, tag);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
