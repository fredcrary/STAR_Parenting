package com.starparent.starparent;



import android.app.Activity;
import android.os.AsyncTask;

import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * A utility class that will be expanded with tools everyone needs.
 */

public class Utils extends AsyncTask<String, Void, List> {
    //TODO: When both data and network are reliable, check for network status and try to download from source
    public InputStream stream;
    public List list;
    private Activity parentActivity;
    XmlParser parser = new XmlParser();

    public Utils(Activity activity) {
        this.parentActivity = activity;
    }

    @Override
    protected List doInBackground(String[] params) {
        try {
            URL url = new URL(params[0]);
            String tag = params[1];
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                stream = new BufferedInputStream(urlConnection.getInputStream());
                list = parser.parse(stream, tag);
            } catch (java.io.IOException e) {

            } catch (XmlPullParserException e){

            } finally {
                urlConnection.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        return list;
    }

    @Override
    protected void onPostExecute(List list) {

    }






}