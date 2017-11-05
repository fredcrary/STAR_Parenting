package com.starparent.starparent;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.widget.DrawerLayout;
import android.util.FloatMath;
import android.util.Log;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;
import com.starparent.starparent.StaticClasses.*;

import org.w3c.dom.Text;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class QuickIdeasMainActivity extends AppCompatActivity {
    //Standard constants
    private static final String TAG = "QuickIdeasMain";
    private final String tag = "quick_ideas";
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Rendering the pane");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_ideas_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            parseXml();
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }

        //Animation
        final Animation fade_in;
        fade_in = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);

        final TextView animatedText = (TextView)findViewById(R.id.quick_ideas_text);

        // fade in
        animatedText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                animatedText.setVisibility(View.VISIBLE);
                animatedText.startAnimation(fade_in);
                int index = getRandomNumber(quickIdeas.size());
                Log.d(TAG, "index: " + index);
                Log.d(TAG, "size : " + quickIdeas.size());
                String idea = quickIdeas.get(index).name + "\n" + quickIdeas.get(index).display;
                animatedText.setText(idea);
            }
        });

        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {
                handleShakeEvent(count);
                animatedText.setVisibility(View.VISIBLE);
                animatedText.startAnimation(fade_in);
                int index = getRandomNumber(quickIdeas.size());
                Log.d(TAG, "index: " + index);
                Log.d(TAG, "size : " + quickIdeas.size());
                String idea = quickIdeas.get(index).name + "\n" + quickIdeas.get(index).display;
                animatedText.setText(idea);
            }
        });
    }

    private void handleShakeEvent(int count){
        if (quickIdeas != null && !quickIdeas.isEmpty()) {
            int index = getRandomNumber(quickIdeas.size());
            Log.d(TAG, "index: " + index);
            Log.d(TAG, "size : " + quickIdeas.size());
            String toastText = quickIdeas.get(index).name + "\n" + quickIdeas.get(index).display;
            Toast.makeText(getApplicationContext(), toastText, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Shake is working. Number of shakes: " + count, Toast.LENGTH_SHORT).show();
        }
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