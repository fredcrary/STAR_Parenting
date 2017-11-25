package com.starparent.starparent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import net.cachapa.expandablelayout.ExpandableLayout;

public class ProblemSolvingGuideActivity extends BaseNavigationDrawerActivity {

    ExpandableLayout expandableLayout1, expandableLayout2, expandableLayout3, expandableLayout4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateDrawer();
        getLayoutInflater().inflate(R.layout.activity_problem_solving_guide, frameLayout);
        setTitle("Problem Solving Guide");

        //sets up references to expanding/collapsing sections
        expandableLayout1 = (ExpandableLayout) findViewById(R.id.expandableLayout1);
        expandableLayout2 = (ExpandableLayout) findViewById(R.id.expandableLayout2);
        expandableLayout3 = (ExpandableLayout) findViewById(R.id.expandableLayout3);
        expandableLayout4 = (ExpandableLayout) findViewById(R.id.expandableLayout4);

    }

    //methods expand & collapse different sections of the guide based on user click
    public void expandableButton1(View view) {
        expandableLayout1.toggle(); // toggle expand and collapse
        if(expandableLayout2.isExpanded()){
            expandableLayout2.collapse();
        }
        if(expandableLayout3.isExpanded()){
            expandableLayout3.collapse();
        }
        if(expandableLayout4.isExpanded()){
            expandableLayout4.collapse();
        }
    }
    public void expandableButton2(View view) {
        expandableLayout2.toggle(); // toggle expand and collapse
        if(expandableLayout1.isExpanded()){
            expandableLayout1.collapse();
        }
        if(expandableLayout3.isExpanded()){
            expandableLayout3.collapse();
        }
        if(expandableLayout4.isExpanded()){
            expandableLayout4.collapse();
        }
    }

    public void expandableButton3(View view) {
        expandableLayout3.toggle(); // toggle expand and collapse
        if(expandableLayout2.isExpanded()){
            expandableLayout2.collapse();
        }
        if(expandableLayout4.isExpanded()){
            expandableLayout4.collapse();
        }
        if(expandableLayout4.isExpanded()){
            expandableLayout4.collapse();
        }
    }
    public void expandableButton4(View view) {
        expandableLayout4.toggle(); // toggle expand and collapse
        if(expandableLayout2.isExpanded()){
            expandableLayout2.collapse();
        }
        if(expandableLayout3.isExpanded()){
            expandableLayout3.collapse();
        }
        if(expandableLayout1.isExpanded()){
            expandableLayout1.collapse();
        }
    }


}
