package com.starparent.starparent;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class SplashActivity extends BaseNavigationDrawerActivity {
    private final int continueTime = 5000;
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();

    private static final String TAG = "TipOfTheDay";
    private final String tag = "daily_tip";
    private final String xmlFileName = tag + ".xml";
    private final String URL = "http://starparent.com/appdata/" + xmlFileName;
    private boolean continueFlag = true;

    //Classes we need
    Utils utils = new Utils();
    InputStream stream = null;
    XmlParser parser = new XmlParser();

    protected List<StaticClasses.DailyTip> tips;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateDrawer();
        getLayoutInflater().inflate(R.layout.activity_splash, frameLayout);
        setTitle("Star Process");
        progressBar = (ProgressBar) findViewById(R.id.bar_progress_splash);

        try {
            parseXml();
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }


        // Tip of the Day Handling
        TextView textView = (TextView) findViewById(R.id.tv_tip_content);
        Map<String, String> tipList = new HashMap<String, String>();

        if (tips != null) {
            //StringBuilder htmlString = new StringBuilder();
            for (int i = 0; i < tips.size(); i++) {
                StaticClasses.DailyTip tip = tips.get(i);
                String title = (tip.text + "<br><br>");
                String tipDescription = "";
                if (tip.explanation.contains("♥")) {
                    String[] hearts = tip.explanation.split("♥");
                    for (int j = 1; j < hearts.length; j++) {
                        tipDescription = ("♥  " + hearts[j] + "<br>");
                    }
                } else {
                    tipDescription = (tip.explanation + "<br>");
                }
                tipList.put(title,tipDescription);

            }

            // Handle Random Selection of Daily Tip
            Random random  = new Random();
            List<String> keys = new ArrayList<String>(tipList.keySet());
            String       randomKey = keys.get( random.nextInt(keys.size()) );
            String       value     = tipList.get(randomKey);

            StringBuilder htmlString = new StringBuilder();
            htmlString.append("<b>" + randomKey + "</b>" + value);
            textView.setText(Html.fromHtml(htmlString.toString()));
            textView.setMovementMethod(new ScrollingMovementMethod());
        }

        // Start long running operation in a background thread
        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {

                    progressStatus += 1;
                    // Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        Thread.sleep(continueTime / 100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(continueFlag) {
                    Intent main = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(main);
                    finish();
                }
            }
        }).start();
    }

    // Continue to Main Activity
    public void toTipsActivity(View view){
        Intent tipsIntent = new Intent(this,TipOfTheDayActivity.class);
        continueFlag = false;
        finish();
        startActivity(tipsIntent);
    }

    //This should be usable in every ActivityClass
    private void parseXml() throws XmlPullParserException, IOException {
        stream = utils.isNetworkAvailable() ? parser.downloadUrl(URL) : this.getAssets().open(xmlFileName);
        try {
            tips = parser.parse(stream, tag);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
