package com.starparent.starparent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.io.InputStream;


public class IdeaBankMainActivity extends AppCompatActivity {
    private final String URL = "http://starparent.com/appdata/ideas_bank.xml";
    private final String tag = "ideas_bank";
    InputStream stream = null;
    XmlParser parser = new XmlParser();
    //TODO: When both data and network are reliable, check for network status and try to download from source
    boolean isNetworkAvailable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Instantiate the pane
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idea_bank_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Do stuff!
        try {
            parseXml();
        } catch (XmlPullParserException  | IOException e) {
            e.printStackTrace();
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
            parser.parse(stream, tag);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
