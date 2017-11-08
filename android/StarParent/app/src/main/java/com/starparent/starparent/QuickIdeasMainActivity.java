package com.starparent.starparent;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.util.Log;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.starparent.starparent.StaticClasses.*;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class QuickIdeasMainActivity extends AppCompatActivity{
    //Standard constants
    private static final String TAG = "QuickIdeasMain";
    private final String tag = "quick_ideas";
    //private final String tag = "points_tutorial";
    private final String xmlFileName = tag + ".xml";
    private final String URL = "http://starparent.com/appdata/" + xmlFileName;

    //Classes we need
    Utils utils = new Utils();
    InputStream stream = null;
    XmlParser parser = new XmlParser();

    // The following are used for the shake detection
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

    protected List<QuickIdeaTools> quickIdeas;

    //buttons
    private int[] quick_ideas_index;
    private Button btn_1_quick_ideas;
    private Button btn_2_quick_ideas;
    private Button btn_3_quick_ideas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Rendering the pane");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_ideas_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        try {
            parseXml();
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }


        //Create buttons and onClick listeners
        Button btn_star_points =(Button)findViewById(R.id.btn_star_points);
        btn_star_points.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent ii = new Intent(QuickIdeasMainActivity.this, StarPointsActivity.class);
                startActivity(ii);
            }
        });

        btn_1_quick_ideas =(Button)findViewById(R.id.btn_1_quick_ideas);
        btn_1_quick_ideas.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int index = quick_ideas_index[0];
                Intent ii = new Intent(QuickIdeasMainActivity.this, QuickIdeaSecondaryActivity.class);
                ii.putExtra("QuickIdea", index);
                startActivity(ii);
            }
        });
        btn_2_quick_ideas =(Button)findViewById(R.id.btn_2_quick_ideas);
        btn_2_quick_ideas.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int index = quick_ideas_index[1];
                Intent ii = new Intent(QuickIdeasMainActivity.this, QuickIdeaSecondaryActivity.class);
                ii.putExtra("QuickIdea", index);
                startActivity(ii);
            }
        });
        btn_3_quick_ideas =(Button)findViewById(R.id.btn_3_quick_ideas);
        btn_3_quick_ideas.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int index = quick_ideas_index[2];
                Intent ii = new Intent(QuickIdeasMainActivity.this, QuickIdeaSecondaryActivity.class);
                ii.putExtra("QuickIdea", index);
                startActivity(ii);
            }
        });

        //set Quick Ideas button text
        quick_ideas_index = setQuickIdeasBtnText();

        //fades in buttons
        btnFadeIn();


//        //Animation
//        final Animation fade_in;
//        fade_in = AnimationUtils.loadAnimation(getApplicationContext(),
//                R.anim.fade_in);
//
//        final TextView animatedText = (TextView)findViewById(R.id.quick_ideas_text);
//
//        // fade in
//        animatedText.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                animatedText.setVisibility(View.VISIBLE);
//                animatedText.startAnimation(fade_in);
//                int index = getRandomNumber(quickIdeas.size());
//                Log.d(TAG, "index: " + index);
//                Log.d(TAG, "size : " + quickIdeas.size());
//                String idea = quickIdeas.get(index).name + "\n" + quickIdeas.get(index).display;
//                animatedText.setText(idea);
//            }
//        });

        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {
                //handleShakeEvent(count);
                //animatedText.setVisibility(View.VISIBLE);
                //animatedText.startAnimation(fade_in);
                //int index = getRandomNumber(quickIdeas.size());
                //Log.d(TAG, "index: " + index);
                //Log.d(TAG, "size : " + quickIdeas.size());
                //String idea = quickIdeas.get(index).name + "\n" + quickIdeas.get(index).display;
                //animatedText.setText(idea);
                quick_ideas_index = setQuickIdeasBtnText();
                btnFadeIn();
            }
        });


    }

    //Set Quick Ideas button text
    private int[] setQuickIdeasBtnText(){
        int[] index = new int[3];
        for(int i=0;i<index.length;i++){
            index[i] = getRandomNumber(quickIdeas.size());
        }
        while(index[0] == index[1] ||  index[0] == index[2] || index[1] == index [2]){
            index[0] = getRandomNumber(quickIdeas.size());
            index[1] = getRandomNumber(quickIdeas.size());
            index[2] = getRandomNumber(quickIdeas.size());
        }
        btn_1_quick_ideas.setText(quickIdeas.get(index[0]).name);
        btn_2_quick_ideas.setText(quickIdeas.get(index[1]).name);
        btn_3_quick_ideas.setText(quickIdeas.get(index[2]).name);
        return index;
    }

    //Makes buttons fade in
    private void btnFadeIn() {
        ArrayList<View> viewsToFadeIn = new ArrayList<View>();

        viewsToFadeIn.add(findViewById(R.id.btn_1_quick_ideas));
        viewsToFadeIn.add(findViewById(R.id.btn_2_quick_ideas));
        viewsToFadeIn.add(findViewById(R.id.btn_3_quick_ideas));

        for (View v : viewsToFadeIn) {
            v.setAlpha(0); // make invisible to start
        }

        for (View v : viewsToFadeIn) {
            // 3 second fade in time
            v.animate().alpha(1.0f).setDuration(3000).start();
        }
    }

    private void handleShakeEvent(int count){
//        if (quickIdeas != null && !quickIdeas.isEmpty()) {
//            //quick_ideas_index = setQuickIdeasBtnText();
//            int index = getRandomNumber(quickIdeas.size());
//            Log.d(TAG, "index: " + index);
//            Log.d(TAG, "size : " + quickIdeas.size());
//            String toastText = quickIdeas.get(index).name + "\n" + quickIdeas.get(index).display;
//            Toast.makeText(getApplicationContext(), toastText, Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(getApplicationContext(), "Shake is working. Number of shakes: " + count, Toast.LENGTH_SHORT).show();
//        }
    }

    private int getRandomNumber(int listSize) {
        return (int) Math.floor(Math.random() * 101 % listSize);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
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