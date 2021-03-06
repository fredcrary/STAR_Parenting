package com.starparent.starparent;

import android.util.Log;
import android.util.Xml;

import com.starparent.starparent.StaticClasses.*;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
        List entries = new ArrayList<>();
        parser.require(XmlPullParser.START_TAG, ns, tag);
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            // Look at the parent tags and pass the xml to the appropriate 'read' method.
            switch (tag) {
                case "ideas_bank2":
                    entries.add(readIdeasBank(parser));
                    break;
                case "quick_ideas":
                    entries.add(readQuickIdeas(parser));
                    break;
                case "resources":
                    entries.add(readResources(parser));
                    break;
                case "points_tutorial":
                    entries.add(readPointsTutorial(parser));
                    break;
                case "process_tutorial":
                    entries.add(readProcessTutorial(parser));
                    break;
                case "daily_tip":
                    entries.add(readDailyTips(parser));
                    break;
                //TODO: Various other case statements
                default:
                    Log.d(TAG, "Unexpected XML tag, nothing to render.");
                    break;
            }
        }
        return entries;
    }

    private DailyTip readDailyTips(XmlPullParser parser) throws XmlPullParserException, IOException {
        Log.d(TAG, "Reading daily_tip.xml");
        String text = null;
        String expl = null;
        String link = null;

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String tagName = parser.getName();
            switch (tagName) {
                case "text":
                    text = readText(parser);
                    break;
                case "explanation":
                    expl = readText(parser);
                    break;
                case "link":
                    link = readText(parser);
                    break;
                default:
                    break;
            }
        }
        return new DailyTip(text, expl, link);
    }

    private ProcessTutorialStep readProcessTutorial(XmlPullParser parser) throws XmlPullParserException, IOException {
        Log.d(TAG, "Reading process_tutorial.xml");
        String name = null;
        String detail = null;
        List<StepElement> elements = new ArrayList<>();

        parser.require(XmlPullParser.START_TAG, ns, "step");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String tagName = parser.getName();
            switch (tagName) {
                case "name":
                    name = readText(parser);
                    break;
                case "detail":
                    detail = readText(parser);
                    break;
                case "element":
                    elements.add(readStepElement(parser));
                    break;
                default:
                    break;
            }
        }
        return new ProcessTutorialStep(name, detail, elements);
    }

    private StepElement readStepElement(XmlPullParser parser) throws XmlPullParserException, IOException {
        String name = null;
        String detail = null;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String tagName = parser.getName();
            switch (tagName) {
                case "name":
                    name = readText(parser);
                    break;
                case "detail":
                    detail = readText(parser);
                    break;
                default:
                    break;
            }
        }
        return new StepElement(name, detail);
    }

    private PointsTutorialPoint readPointsTutorial(XmlPullParser parser) throws XmlPullParserException, IOException {
        Log.d(TAG, "Reading points_tutorial.xml");
        String name = null;
        String goal = null;
        String explanation = null;
        List<PointsTutorialTool> tools = new ArrayList<>();

        parser.require(XmlPullParser.START_TAG, ns, "point");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String tagName = parser.getName();
            switch (tagName) {
                case "name":
                    name = readText(parser);
                    break;
                case "goal":
                    goal = readText(parser);
                    break;
                case "explanation":
                    explanation = readText(parser);
                    break;
                case "tool":
                    tools.add(readPointsTutorialTool(parser));
                    break;
                default:
                    break;
            }
        }
        return new PointsTutorialPoint(name, goal, explanation, tools);
    }

    private PointsTutorialTool readPointsTutorialTool(XmlPullParser parser) throws XmlPullParserException, IOException {
        String name = null;
        String goal = null;
        String howToTitle = null;
        String howToText = null;
        String example = null;

        parser.require(XmlPullParser.START_TAG, ns, "tool");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String tagName = parser.getName();
            switch (tagName) {
                case "name":
                    name = readText(parser);
                    break;
                case "goal":
                    goal = readText(parser);
                    break;
                case "how_to_title":
                    howToTitle = readText(parser);
                    break;
                case "how_to_text":
                    howToText = readText(parser);
                    break;
                case "example":
                    example = readText(parser);
                    break;
                default:
                    break;
            }
        }
        return new PointsTutorialTool(name, goal, howToTitle, howToText, example);
    }

    private QuickIdeaTools readQuickIdeas(XmlPullParser parser) throws XmlPullParserException, IOException {
        Log.d(TAG, "Reading quick_ideas.xml");
        String tool_name = null;
        String display   = null;

        parser.require(XmlPullParser.START_TAG, ns, "tool");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String tagName = parser.getName();
            switch (tagName) {
                case "display":
                    display = readText(parser);
                    break;
                case "tool_name":
                    tool_name = readText(parser);
                    break;
                default:
                    skip(parser);
            }
        }
        return new QuickIdeaTools(tool_name, display);
    }
    private ResourceEntry readResources(XmlPullParser parser)  throws XmlPullParserException, IOException {
        Log.d(TAG, "Reading resources.xml");
        String entry = null;
        String tagName = parser.getName();
        switch (tagName) {
            case "entry":
                entry = readText(parser);
                break;
            default:
                skip(parser);
        }
        return new ResourceEntry(entry);
    }

    private IdeasBankProblem readIdeasBank(XmlPullParser parser) throws XmlPullParserException, IOException {
        Log.d(TAG, "Reading ideas_bank2.xml");
        String title      = null;
        String goal       = null;
        String reality    = null;
        String desc       = null;
        List<IdeasBankIdea> ideas           = new ArrayList<>();
        List<String> ageGroups              = new ArrayList<>();

        parser.require(XmlPullParser.START_TAG, ns, "problem");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String tagName = parser.getName();
            switch (tagName) {
                case "title":
                    title = readText(parser);
                    break;
                case "age_group":
                    ageGroups.add(readText(parser));
                    break;
                case "description":
                    desc = (readText(parser));
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
        return new IdeasBankProblem(title, ageGroups, desc, goal, reality, ideas);
    }

    private IdeasBankIdea readIdeaBankIdea(XmlPullParser parser)  throws XmlPullParserException, IOException {
        String star_tool  = null;
        String idea_text   = null;
        parser.require(XmlPullParser.START_TAG, ns, "idea");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            switch (name) {
                case "star_tool":
                    star_tool = readText(parser);
                    break;
                case "idea_text":
                    idea_text = readText(parser);
                    break;
                default:
                    skip(parser);
            }
        }
        return new IdeasBankIdea(idea_text, star_tool);
    }

    private IdeasBankDescription readIdeasBankDescription(XmlPullParser parser) throws XmlPullParserException, IOException {
        String ageGroup  = null;
        String text      = null;
        parser.require(XmlPullParser.START_TAG, ns, "description");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            switch (name) {
                case "example_age":
                    ageGroup = readText(parser);
                    break;
                case "example_text":
                    text = readText(parser);
                    break;
                default:
                    skip(parser);
            }
        }
        return new IdeasBankDescription(ageGroup, text);
    }

    // For the tags title and summary, extracts their text values.
    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return maybeReplaceTokens(result);
    }

    private String maybeReplaceTokens(String string) {
        return string.replace("[", "<").replace("]", ">");
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