package com.starparent.starparent;

import android.content.Intent;
import android.util.Log;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
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
    protected List<IdeasBankProblem> problems;
    //TODO: When both data and network are reliable, check for network status and try to download from source
    boolean isNetworkAvailable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Rendering the pane");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idea_bank_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        try {
            parseXml();
        } catch (XmlPullParserException  | IOException e) {
            e.printStackTrace();
        }

        if (problems != null) {
            for (final IdeasBankProblem problem : problems) {
                Button btn_problem = (Button) findViewById(R.id.btn_idea_bank_problem);
                btn_problem.setText(problem.title);
                btn_problem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(IdeaBankMainActivity.this, ProblemActivity.class);
                        Bundle b = new Bundle();
                        b.putSerializable("problem", problem);
                        i.putExtras(b);
                        startActivity(i);
                    }
                });
            }
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
