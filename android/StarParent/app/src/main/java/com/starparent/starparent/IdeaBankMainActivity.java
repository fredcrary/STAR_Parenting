package com.starparent.starparent;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import com.starparent.starparent.StaticClasses.IdeasBankProblem;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class IdeaBankMainActivity extends BaseNavigationDrawerActivity {
    //Standard constants
    private static final String TAG = "IdeaBankMain";
    private final String tag = "ideas_bank2";
    private final String xmlFileName = tag + ".xml";
    private final String URL = "http://starparent.com/appdata/" + xmlFileName;

    //Classes we need
    //Utils utils = new Utils();
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
        onCreateDrawer();
        getLayoutInflater().inflate(R.layout.activity_idea_bank_main, frameLayout);
        setTitle("Ideas Bank");
        try {
            parseXml();
        } catch (XmlPullParserException  | IOException e) {
            e.printStackTrace();
        }

        //Descriptive Text Container
//        TextView textView = (TextView) findViewById(R.id.textView);
//        StringBuilder htmlString = new StringBuilder();
//        htmlString.append("<p>A brief description of this pane and how it works.</p>");
//        htmlString.append("<p>Maybe even a second line of somewhat descriptive text.</p>");
//        htmlString.append("<h3>Choose age group: </h3>");
//        textView.setText(Html.fromHtml(htmlString.toString()));


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Initial Layout should show everything (no filter)
        updateRecycler(problems);
    }

    private void updateRecycler(List<IdeasBankProblem> problems) {
        if (problems != null) {
            adapter = new RecyclerViewAdapter(problems, IdeaBankMainActivity.this);
            recyclerView.setAdapter(adapter);
        } else {
            //TODO: erm...display something here probably. :)
        }
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.toddler:
                if (checked)
                    updateRecycler(filterProblems("toddler"));
                    break;
            case R.id.preschool:
                if (checked)
                    updateRecycler(filterProblems("preschool"));
                    break;
            case R.id.schoolage:
                if (checked)
                    updateRecycler(filterProblems("school-age"));
                    break;
            case R.id.all:
                if (checked)
                    updateRecycler(problems);
                break;
        }
    }

    private List<IdeasBankProblem> filterProblems(String criteria) {
        List<IdeasBankProblem> filteredProblems = new ArrayList<>();
        for (IdeasBankProblem problem : problems) {
            if (problem.ageGroups.contains(criteria)) {
                filteredProblems.add(problem);
            }
        }
        return filteredProblems;
    }

    public void setList(List list) {
        this.problems = list;
    }

    //This should be usable in every ActivityClass
    private void parseXml() throws XmlPullParserException, IOException {
        try {
            boolean networkSuccess = false;
            try {
                Utils network = new Utils(this);
                problems = network.execute(URL, tag).get();
                networkSuccess = true;
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (!networkSuccess) {
                    problems = parser.parse(this.getAssets().open(xmlFileName), tag);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
