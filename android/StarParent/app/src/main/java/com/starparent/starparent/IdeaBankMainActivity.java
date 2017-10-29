package com.starparent.starparent;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import com.starparent.starparent.StaticClasses.*;

public class IdeaBankMainActivity extends AppCompatActivity {
    //Standard constants
    private static final String TAG = "IdeaBankMain";
    private final String tag = "ideas_bank";
    private final String xmlFileName = tag + ".xml";
    private final String URL = "http://starparent.com/appdata/" + xmlFileName;

    //Classes we need
    Utils utils = new Utils();
    InputStream stream = null;
    XmlParser parser = new XmlParser();

    //Views we'll use
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    protected List<IdeasBankProblem> problems;

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

        //Descriptive Text Container
        TextView textView = (TextView) findViewById(R.id.textView);
        StringBuilder htmlString = new StringBuilder();
        htmlString.append("<h1>Ideas Bank</h1>");
        htmlString.append("<p>A brief description of this pane and how it works.</p>");
        htmlString.append("<p>Maybe even a second line of somewhat descriptive text.</p>");
        textView.setText(Html.fromHtml(htmlString.toString()));

        //List of parsed items Container
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if (problems != null) {
            List <Button> btnList = new ArrayList<>();
            for (final IdeasBankProblem problem : problems) {
                Button btn = new Button(this);
                btn.setText(problem.title);
                btn.setVisibility(View.VISIBLE);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(IdeaBankMainActivity.this, ProblemActivity.class);
                        Bundle b = new Bundle();
                        b.putSerializable("problem", problem);
                        i.putExtras(b);
                        startActivity(i);
                    }
                });
                btnList.add(btn);
            }
            adapter = new RecyclerViewAdapter(btnList);
            recyclerView.setAdapter(adapter);
        }
    }

    //This should be usable in every ActivityClass
    private void parseXml() throws XmlPullParserException, IOException {
        if (utils.isNetworkAvailable()) {
            stream = parser.downloadUrl(URL);
        } else {
            stream = this.getAssets().open(xmlFileName);
        }
        try {
            problems = parser.parse(stream, tag);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
