package com.starparent.starparent;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class StarPointsActivity extends BaseNavigationDrawerActivity {
    //Standard constants
    private static final String TAG = "StarPointsMain";
    private final String tag = "points_tutorial";
    private final String xmlFileName = tag + ".xml";
    private final String URL = "http://starparent.com/appdata/" + xmlFileName;

    //Classes we need
    Utils utils = new Utils();
    InputStream stream = null;
    XmlParser parser = new XmlParser();

    protected List<StaticClasses.PointsTutorialPoint> points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateDrawer();
        getLayoutInflater().inflate(R.layout.activity_star_points, frameLayout);
        setTitle("Star Points");

        try {
            parseXml();
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }

        TextView lblPointTitle = (TextView)findViewById(R.id.lblPointHeader);
        TextView lblGoal = (TextView)findViewById(R.id.lblGoal);
        TextView lblExplaination = (TextView)findViewById(R.id.lblExplaination);

        Intent i = getIntent();
        Bundle b = i.getExtras();

        //Points looks good -- Do what you will with them!  :)
        if (points != null && b != null) {
            int index = (int)b.get("point");
            Log.d(TAG, points.toString());

            lblPointTitle.setText(points.get(index).name);
            lblGoal.setText(points.get(index).goal);
            lblExplaination.setText(points.get(index).explanation);
        }
    }

    //This should be usable in every ActivityClass
    private void parseXml() throws XmlPullParserException, IOException {
        stream = utils.isNetworkAvailable() ? parser.downloadUrl(URL) : this.getAssets().open(xmlFileName);
        try {
            points = parser.parse(stream, tag);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
