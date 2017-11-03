package com.starparent.starparent;

import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import com.starparent.starparent.StaticClasses.*;
import java.util.ArrayList;
import java.util.List;

public class ProblemActivity extends AppCompatActivity {
    private IdeasBankProblem problem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView textView = (TextView) findViewById(R.id.textView);

        Bundle b = getIntent().getExtras();
        if (b.getSerializable("problem") != null) {
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

        StringBuilder htmlString = new StringBuilder();
        htmlString.append("<h2>" + problem.title + "</h2>");
        for (IdeasBankDescription description : problem.descriptions) {
            htmlString.append("<h4>Description:</h4>");
            htmlString.append("<p>" + description.text + "</p>");
        }
        htmlString.append("<h4>Goal: </h4>");
        htmlString.append("<p>" + problem.goal + "</p>");
        htmlString.append("<h4>Reality Check: </h4>");
        htmlString.append("<p>" + problem.reality_check + "</p>");
        htmlString.append("<h3>Ideas: </h3>");
        htmlString.append("<h4>Avoid problems</h4>");
        for (IdeasBankIdea idea : avoidProblems) {
            htmlString.append("<p>" + idea.idea_text + " <b>" + idea.star_point + "</b></p>");
        }
        htmlString.append("<h4>Respond to cooperation</h4>");
        for (IdeasBankIdea idea : respondToCoop) {
            htmlString.append("<p>" + idea.idea_text + " <b>" + idea.star_point + "</b></p>");
        }
        htmlString.append("<h4>Set reasonable limits</h4>");
        for (IdeasBankIdea idea : setReasLimits) {
            htmlString.append("<p>" + idea.idea_text + " <b>" + idea.star_point + "</b></p>");
        }
        htmlString.append("<h4>Teach new skills</h4>");
        for (IdeasBankIdea idea : teachNewSkills) {
            htmlString.append("<p>" + idea.idea_text + " <b>" + idea.star_point + "</b></p>");
        }
        htmlString.append("<h4>Acknowledge feelings</h4>");
        for (IdeasBankIdea idea : ackFeelings) {
            htmlString.append("<p>" + idea.idea_text + " <b>" + idea.star_point + "</b></p>");
        }
        textView.setText(Html.fromHtml(htmlString.toString()));
        textView.setMovementMethod(new ScrollingMovementMethod());
    }
}
