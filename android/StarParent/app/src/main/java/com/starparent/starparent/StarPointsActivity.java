package com.starparent.starparent;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class StarPointsActivity extends BaseNavigationDrawerActivity {
    //Standard constants
    private static final String TAG = "StarPointsMain";
    private final String tag = "points_tutorial";
    private final String xmlFileName = tag + ".xml";
    private final String URL = "http://starparent.com/appdata/" + xmlFileName;

    //Classes we need
    //Utils utils = new Utils();
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

        Button btnToolOne = (Button)findViewById(R.id.btnToolOne);
        Button btnToolTwo = (Button)findViewById(R.id.btnToolTwo);
        Button btnToolThree = (Button)findViewById(R.id.btnToolThree);

        Intent i = getIntent();
        Bundle b = i.getExtras();

        //Points looks good -- Do what you will with them!  :)
        if (points != null && b != null) {
            final int index = (int)b.get("point");
            Log.d(TAG, points.toString());

            lblPointTitle.setText(Html.fromHtml(points.get(index).name));
            lblGoal.setText(Html.fromHtml(points.get(index).goal));
            lblExplaination.setText(Html.fromHtml(points.get(index).explanation));

            String tool1 = points.get(index).tools.get(0).name;
            String tool2 = points.get(index).tools.get(1).name;
            String tool3 = points.get(index).tools.get(2).name;

            btnToolOne.setText(tool1);
            btnToolTwo.setText(tool2);
            btnToolThree.setText(tool3);

            btnToolOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(StarPointsActivity.this, StarPointTools.class);
                    intent.putExtra("point", index);
                    intent.putExtra("tool", 0);
                    startActivity(intent);
                }
            });

            btnToolTwo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(StarPointsActivity.this, StarPointTools.class);
                    intent.putExtra("point", index);
                    intent.putExtra("tool", 1);
                    startActivity(intent);
                }
            });

            btnToolThree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(StarPointsActivity.this, StarPointTools.class);
                    intent.putExtra("point", index);
                    intent.putExtra("tool", 2);
                    startActivity(intent);
                }
            });
        }
    }

    //This should be usable in every ActivityClass
    private void parseXml() throws XmlPullParserException, IOException {
        try {
            boolean networkSuccess = false;
            try {
                Utils network = new Utils(this);
                points = network.execute(URL, tag).get();
                networkSuccess = true;
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (!networkSuccess) {
                    points = parser.parse(this.getAssets().open(xmlFileName), tag);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
