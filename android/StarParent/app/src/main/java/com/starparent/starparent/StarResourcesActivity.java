package com.starparent.starparent;

import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class StarResourcesActivity extends BaseNavigationDrawerActivity {
    private final String tag = "resources";
    private final String xmlFileName = tag + ".xml";
    private final String URL = "http://starparent.com/appdata/" + xmlFileName;

    InputStream stream = null;
    //Utils utils = new Utils();
    XmlParser parser = new XmlParser();
    protected List<StaticClasses.ResourceEntry> entries;

    private static final String TAG = "ResourcesActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateDrawer();
        getLayoutInflater().inflate(R.layout.activity_star_resources, frameLayout);
        setTitle("Star Resources");

        TextView textView = (TextView) findViewById(R.id.textView);
        Log.d(TAG, "on create started");

        try {
            parseXml();
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }

        StringBuilder htmlString = new StringBuilder();
        for (StaticClasses.ResourceEntry entry : entries) {
            htmlString.append("<p>" + entry.entry + "</p>");
        }
        Log.d(TAG, String.valueOf(htmlString));

        textView.setText(Html.fromHtml(htmlString.toString()));
        textView.setMovementMethod(new ScrollingMovementMethod());
    }

    //This should be usable in every ActivityClass
    private void parseXml() throws XmlPullParserException, IOException {
        try {
            boolean networkSuccess = false;
            try {
                Utils network = new Utils(this);
                entries = network.execute(URL, tag).get();
                networkSuccess = true;
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (!networkSuccess) {
                    entries = parser.parse(this.getAssets().open(xmlFileName), tag);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
