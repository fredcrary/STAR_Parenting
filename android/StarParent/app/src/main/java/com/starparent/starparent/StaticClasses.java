package com.starparent.starparent;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jeremy on 10/10/17.
 * This is a public class that allows us to define static classes the conform to the xml definitions
 * we expect to receive from starparent.com/app_data/...xml for data parsing
 */

public class StaticClasses {
    //Holder for the version of a given xml file from versions.xml
    public static class Version {
        public final double version;
        protected Version(double version) {
            this.version = version;
        }
    }

    //Holder for the Daily Tips from daily_tip.xml
    public static class DailyTip {
        public final String text;
        public final String explanation;
        public final String link;
        protected DailyTip(String text, String explanation, String link) {
            this.text = text;
            this.explanation = explanation;
            this.link = link;
        }
    }

    //StepElements exist within the context of a ProcessTutorialStep from process_tutorial.xml
    public static class StepElement {
        public final String name;
        public final String detail;
        protected StepElement(String name, String detail) {
            this.name = name;
            this.detail = detail;
        }
    }
    public static class ProcessTutorialStep {
        public final String name;
        public final StepElement element;
        protected ProcessTutorialStep(String name, StepElement element) {
            this.name = name;
            this.element = element;
        }
    }

    //PointsTutorialTools exist within the context of a PointsTutorial from points_tutorial.xml.  Note that
    //examples must be passed in as a String Array, even if only a single example for the tool exists.
    public static class PointsTutorialTool {
        public final String name;
        public final String goal;
        public final String howToTitle;
        public final String howToText;
        public final String[] examples;
        protected PointsTutorialTool(String name, String goal, String howToTitle, String howToText, String[] examples) {
            this.name = name;
            this.goal = goal;
            this.howToTitle = howToTitle;
            this.howToText  = howToText;
            this.examples   = examples;
        }
    }
    public static class PointsTutorialPoint {
        public final String name;
        public final String goal;
        public final String explanation;
        public final PointsTutorialTool tool;
        protected PointsTutorialPoint(String name, String goal, String explanation, PointsTutorialTool tool) {
            this.name = name;
            this.goal = goal;
            this.explanation = explanation;
            this.tool = tool;
        }
    }

    //Holder for QuickIdeas as downloaded from quick_ideas.xml
    public static class QuickIdeaTools {
        public final String display;
        public final String name;
        protected QuickIdeaTools(String name, String display) {
            this.name = name;
            this.display = display;
        }
    }

    //IdeasBankIdeas exist within the context of an IdeasBankProblem from ideas_bank.xml
    public static class IdeasBankIdea {
        public final String idea_text;
        public final String star_point;
        protected IdeasBankIdea(String idea_text, String star_point) {
            this.idea_text  = idea_text;
            this.star_point = star_point;
        }
    }

    //IdeasBankDescriptions are keyed against ageGroups and currently (temporarily?) are taking the place of the <description> String
    public static class IdeasBankDescription {
        public final String ageGroup;
        public final String text;
        protected IdeasBankDescription(String ageGroup, String text) {
            this.ageGroup = ageGroup;
            this.text = text;
        }
    }

    public static class IdeasBankProblem implements Serializable {
        public final String title;
        public final List<String> ageGroups;       //TODO: Verify a) that this is actually a String and b) that it's necessary at all
        public final String goal;
        public final String reality_check;
        public static transient List<IdeasBankDescription> descriptions;
        public static transient List<IdeasBankIdea> ideas;
        protected IdeasBankProblem(String title, List<String> ageGroups, List<IdeasBankDescription> descriptions, String goal, String reality_check, List<IdeasBankIdea> ideas) {
            this.title = title;
            this.ageGroups = ageGroups;
            this.descriptions = descriptions;
            this.goal = goal;
            this.reality_check = reality_check;
            this.ideas = ideas;
        }
    }

    //Resource Entries are ill-defined in the spec.  This is a best guess, subject to change
    public static class ResourceEntry {
        public final String entry;
        protected ResourceEntry(String entry) {
            this.entry = entry;
        }
    }
}
