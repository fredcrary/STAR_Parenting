package com.starparent.starparent;

import android.util.Log;
import android.util.Xml;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import com.starparent.starparent.StaticClasses.*;

/**
 * Mostly lifted from here: https://developer.android.com/training/basics/network-ops/xml.html
 * with modifications for source URL and override for consumption of local xml
 */

public class XmlParser {
    private static final String TAG = "XmlParser";
    private static final String ns = null;


    //Pass me an InputStream containing valid XML.  I'm hungry!  om nom nom nom :{}
    public List parse(InputStream in, String tag) throws XmlPullParserException, IOException {
        Log.d(TAG, "Starting parsing");
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readFeed(parser, tag);
        } finally {
            in.close();
        }
    }

    private List readFeed(XmlPullParser parser, String tag) throws XmlPullParserException, IOException {
        Log.d(TAG, "Reading a new feed, tag: " + tag);
        List entries = new ArrayList();
        parser.require(XmlPullParser.START_TAG, ns, tag);
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            // Look at the parent tags and pass the xml to the appropriate 'read' method.
            switch (tag) {
                case "ideas_bank":
                    entries = new ArrayList<>();
                    entries.add(readIdeasBank(parser));
                //TODO: Various other case statements
                default:
                    //Probably throw a log statement here...
            }
        }
        return entries;
    }

    private IdeasBankProblem readIdeasBank(XmlPullParser parser) throws XmlPullParserException, IOException {
        Log.d(TAG, "Reading IdeasBank xml");
        String title      = null;
        String ageGroup   = null;
        String desc       = null;
        String goal       = null;
        String reality    = null;
        List<IdeasBankIdea> ideas = new ArrayList<>();

        parser.require(XmlPullParser.START_TAG, ns, "problem");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            switch (name) {
                case "title":
                    title = readText(parser);
                    break;
                case "age_group":
                    ageGroup = readText(parser);
                    break;
                case "description":
                    desc = readText(parser);
                    break;
                case "goal":
                    goal = readText(parser);
                    break;
                case "reality_check":
                    reality = readText(parser);
                    break;
                case "idea":
                    ideas.add(readIdeaBankIdea(parser));
                    break;
                default:
                    skip(parser);
            }
        }
        return new IdeasBankProblem(title, ageGroup, desc, goal, reality, ideas);
    }

    private IdeasBankIdea readIdeaBankIdea(XmlPullParser parser)  throws XmlPullParserException, IOException {
        String star_point  = null;
        String idea_text   = null;
        parser.require(XmlPullParser.START_TAG, ns, "idea");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            switch (name) {
                case "star_point":
                    star_point = readText(parser);
                    break;
                case "idea_text":
                    idea_text = readText(parser);
                    break;
                default:
                    skip(parser);
            }
        }
        return new IdeasBankIdea(idea_text, star_point);
    }

    // For the tags title and summary, extracts their text values.
    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }

    //TODO: Currently unused.  Will need to verify network state and perms, xml versioning, etc
    protected InputStream downloadUrl(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000 /* milliseconds */);
        conn.setConnectTimeout(15000 /* milliseconds */);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        // Starts the query
        conn.connect();
        return conn.getInputStream();
    }
}