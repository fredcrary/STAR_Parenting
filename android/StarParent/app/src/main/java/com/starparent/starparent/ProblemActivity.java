package com.starparent.starparent;

import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import com.starparent.starparent.StaticClasses.*;

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
        StringBuilder htmlString = new StringBuilder();
        htmlString.append("<h2>" + problem.title + "</h2>");
        htmlString.append("<h4>Age Groups: </h4>");
        for (String age : problem.ageGroups) {
            htmlString.append("<p>" + age + "</p>");
        }
        for (IdeasBankDescription description : problem.descriptions) {
            htmlString.append("<h3>" + description.ageGroup + "</h3>");
            htmlString.append("<p>" + description.text + "</p>");
        }
        htmlString.append("<h4>Goal: </h4>");
        htmlString.append("<p>" + problem.goal + "</p>");
        htmlString.append("<h4>Reality Check: </h4>");
        htmlString.append("<p>" + problem.reality_check + "</p>");
        htmlString.append("<h3>Ideas: </h3>");
        for (IdeasBankIdea idea : problem.ideas) {
            htmlString.append("<h4>" + idea.star_point + "</h4>");
            htmlString.append("<p>" + idea.idea_text + "</p>");
        }
        textView.setText(Html.fromHtml(htmlString.toString()));
        textView.setMovementMethod(new ScrollingMovementMethod());
    }
}
