package com.starparent.starparent;

import android.content.Intent;
import android.util.Log;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class StarProcessMainActivity extends BaseNavigationDrawerActivity {
    //Standard constants
    private static final String TAG = "StarProcessMain";
    private final String tag = "process_tutorial";
    private final String xmlFileName = tag + ".xml";
    private final String URL = "http://starparent.com/appdata/" + xmlFileName;

    //Classes we need
    Utils utils = new Utils();
    InputStream stream = null;
    XmlParser parser = new XmlParser();

    protected List<StaticClasses.ProcessTutorialStep> steps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Rendering the pane");
        super.onCreate(savedInstanceState);
        onCreateDrawer();
        getLayoutInflater().inflate(R.layout.activity_star_process_main, frameLayout);
        setTitle("Star Process");

        try {
            parseXml();
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }

        TextView lblName = (TextView)findViewById(R.id.lblStepName);
        TextView lblDetail = (TextView)findViewById(R.id.lblStepDetail);

        Intent i = getIntent();
        Bundle b = i.getExtras();

        //Steps looks good -- Do what you will with them!  :)
        if (steps != null) {
            Log.d(TAG, steps.toString());
            int index = (int)b.get("step");

            lblName.setText(steps.get(index).name);
            lblDetail.setText(steps.get(index).detail);
        }
    }

    //This should be usable in every ActivityClass
    private void parseXml() throws XmlPullParserException, IOException {
        stream = utils.isNetworkAvailable() ? parser.downloadUrl(URL) : this.getAssets().open(xmlFileName);
        try {
            steps = parser.parse(stream, tag);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
