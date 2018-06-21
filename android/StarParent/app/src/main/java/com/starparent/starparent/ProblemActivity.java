package com.starparent.starparent;

import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.starparent.starparent.StaticClasses.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProblemActivity extends BaseNavigationDrawerActivity {
    private IdeasBankProblem problem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateDrawer();
        getLayoutInflater().inflate(R.layout.activity_problem, frameLayout);
        setTitle("Quick Ideas");

        TextView textView = (TextView) findViewById(R.id.textView);

        Bundle b = getIntent().getExtras();
        if (b != null && b.getSerializable("problem") != null) {
            problem = (IdeasBankProblem) b.getSerializable("problem");
        }
        final String TAG = "Problem Activity: " + problem.title;

        //Star point to idea lookups
        List<IdeasBankIdea> avoidProblems  = new ArrayList<>();
        List<IdeasBankIdea> respondToCoop  = new ArrayList<>();
        List<IdeasBankIdea> setReasLimits  = new ArrayList<>();
        List<IdeasBankIdea> teachNewSkills = new ArrayList<>();
        List<IdeasBankIdea> ackFeelings    = new ArrayList<>();
        for (IdeasBankIdea idea : problem.ideas) {
            switch (idea.star_point) {
                case "Change things":
                case "Reduce stress":
                case "Two yeses":
                    avoidProblems.add(idea);
                    break;
                case "Attention":
                case "Praise":
                case "Rewards":
                    respondToCoop.add(idea);
                    break;
                case "Clear rules":
                case "Consequences":
                case "A better way":
                    setReasLimits.add(idea);
                    break;
                case "Modeling":
                case "Shaping":
                case "Re-do it right":
                    teachNewSkills.add(idea);
                    break;
                case "Active listening":
                case "Simple listening":
                case "Grant in fantasy":
                    ackFeelings.add(idea);
                    break;
                default:
                    Log.d(TAG, "Un-known idea.star_point: " + idea.star_point);
                    break;
            }
        }

        //Build the detailed TextView
        StringBuilder htmlString = new StringBuilder();
        htmlString.append("<h2>" + problem.title + "</h2>");
        htmlString.append("<b>Description:</b>  " + problem.description + "<br /><br />");
        htmlString.append("<b>Goal:</b>  " + problem.goal + "<br /><br />");
        htmlString.append("<b>Reality Check:</b>  " + problem.reality_check + "<br /><br />");
        htmlString.append("<b>Ideas:</b>");

        //Build the expandable TextViews
        final List<String> expandableListTitle;
        ExpandableListAdapter expandableListAdapter;
        ExpandableListView expandableListView = (ExpandableListView)findViewById(R.id.expandable_list);

        List<String> avoid_problems_detail = new ArrayList<>();
        List<String> respond_coop_detail = new ArrayList<>();
        List<String> reas_limits_detail = new ArrayList<>();
        List<String> new_skills_detail = new ArrayList<>();
        List<String> ack_feelings_detail = new ArrayList<>();
        for (IdeasBankIdea idea : avoidProblems) {
            avoid_problems_detail.add(idea.idea_text + "<br>&mdash;" + idea.star_point);
        }
        for (IdeasBankIdea idea : respondToCoop) {
            respond_coop_detail.add(idea.idea_text + "<br>&mdash;" + idea.star_point);
        }
        for (IdeasBankIdea idea : setReasLimits) {
            reas_limits_detail.add(idea.idea_text + "<br>&mdash;" + idea.star_point);
        }
        for (IdeasBankIdea idea : teachNewSkills) {
            new_skills_detail.add(idea.idea_text + "<br>&mdash;" + idea.star_point);
        }
        for (IdeasBankIdea idea : ackFeelings) {
            ack_feelings_detail.add(idea.idea_text + "<br>&mdash;" + idea.star_point);
        }

        final HashMap<String, List<String>> expandableListDetail = new HashMap<>();
        expandableListDetail.put("Avoid problems", avoid_problems_detail);
        expandableListDetail.put("Respond to cooperation", respond_coop_detail);
        expandableListDetail.put("Set reasonable limits", reas_limits_detail);
        expandableListDetail.put("Teach new skills", new_skills_detail);
        expandableListDetail.put("Acknowledge feelings", ack_feelings_detail);

        expandableListTitle = new ArrayList<>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);

        textView.setText(Html.fromHtml(htmlString.toString()));
        textView.setMovementMethod(new ScrollingMovementMethod());
    }
}