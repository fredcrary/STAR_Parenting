package com.starparent.starparent;

import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class TipOfTheDayActivity extends BaseNavigationDrawerActivity {
    //Standard constants
    private static final String TAG = "TipOfTheDay";
    private final String tag = "daily_tip";
    private final String xmlFileName = tag + ".xml";
    private final String URL = "http://starparent.com/appdata/" + xmlFileName;

    //Classes we need
    //Utils utils = new Utils();
    InputStream stream = null;
    XmlParser parser = new XmlParser();

    protected List<StaticClasses.DailyTip> tips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateDrawer();
        getLayoutInflater().inflate(R.layout.activity_tip_of_the_day, frameLayout);
        setTitle("Tip Of The Day");

        try {
            parseXml();
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }

        TextView textView = (TextView) findViewById(R.id.textView);

        if (tips != null) {
            StringBuilder htmlString = new StringBuilder();
            for (int i = 0; i < tips.size(); i++) {
                StaticClasses.DailyTip tip = tips.get(i);
                htmlString.append(i+1 + ".  ");
                htmlString.append(tip.text + "<br><br>");
                if (tip.explanation.contains("♥")) {
                    String[] hearts = tip.explanation.split("♥");
                    for (int j = 1; j < hearts.length; j++) {
                        htmlString.append("♥  " + hearts[j] + "<br>");
                    }
                    htmlString.append("<br>");
                } else {
                    htmlString.append(tip.explanation + "<br>");
                }
                if (tip.link != null && !tip.link.equals("")) {
                    htmlString.append("  -- " + tip.link);
                }
                htmlString.append("<br><br>");
            }
            textView.setText(Html.fromHtml(htmlString.toString()));
            textView.setMovementMethod(new ScrollingMovementMethod());
        }
    }

    //This should be usable in every ActivityClass
    private void parseXml() throws XmlPullParserException, IOException {
        try {
            boolean networkSuccess = false;
            try {
                Utils network = new Utils(this);
                tips = network.execute(URL, tag).get();
                networkSuccess = true;
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (!networkSuccess) {
                    tips = parser.parse(this.getAssets().open(xmlFileName), tag);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
