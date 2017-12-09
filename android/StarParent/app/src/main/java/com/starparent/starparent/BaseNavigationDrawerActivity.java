package com.starparent.starparent;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

public abstract class BaseNavigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "BaseNavigationDrawer";
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_navigation_drawer);
        onCreateDrawer();
    }

    public void onCreateDrawer() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        frameLayout = (FrameLayout) findViewById(R.id.content_frame);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //after removing menu drop shadow by setting app elevation
        // to 0 the line below makes sure the app bar stays on top
        findViewById(R.id.appBar).bringToFront();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.base_navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_main_screen:
                startActivity(new Intent(this, MainActivity.class));
                break;

            //TOP NavMenu Block
            case R.id.nav_tip:
                startActivity(new Intent(this, TipOfTheDayActivity.class));
                break;

            //Learn about STAR Parenting Process
            case R.id.nav_learn_about_star:
                startActivity(new Intent(this,LearnAboutStarMainActivity.class));
                break;
            case R.id.nav_parenting_proc:
                //this will cause a blank page, since StarProcessMainActivity needs to know which process is being requested
                startActivity(new Intent(this,LearnAboutStarProcessActivity.class));
                break;
            case R.id.nav_points:
                startActivity(new Intent(this, StarPointsMainActivity.class));
                break;

            //Problem Solving

            case R.id.nav_problem_solving:
                startActivity(new Intent(this, ProblemSolverMainActivity.class));
                break;
            case R.id.nav_quick_ideas:
                startActivity(new Intent(this, QuickIdeasMainActivity.class));
                break;
            case R.id.nav_problem_solving_buddy:
                startActivity(new Intent(this, ProblemSolvingGuideActivity.class));
                break;
            case R.id.nav_ideas_bank:
                startActivity(new Intent(this, IdeaBankMainActivity.class));
                break;

            //other
            case R.id.nav_resources:
                startActivity(new Intent(this, StarResourcesActivity.class));
                break;
            case R.id.about_this_app:
                startActivity(new Intent(this, AboutThisAppActivity.class));
                break;

            default:
                Log.d(TAG, "Id not found in list?");
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
