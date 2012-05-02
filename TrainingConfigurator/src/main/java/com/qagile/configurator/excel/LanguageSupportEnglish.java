package com.qagile.configurator.excel;

/**
 * User: ful
 * Date: 30.04.12
 * Time: 22:30
 */
public class LanguageSupportEnglish implements LanguageSupport {

    public static String NameTrainingSheet = "Training";
    public static String ModuleNumberTitle = "#";
    public static String ModuleTitle = "Module";
    public static String TopicTitle = "Topic";
    public static String ActionTitle = "Action";
    public static String DurationActionTitle = "Duration Action [min]";
    public static String DurationTopicTitle = "Duration Topic [min]";
    public static String DurationModuleTitle = "Duration Module [min]";
    public static String DurationTrainingTitle = "Duration Training [min]";

    public static String TargetGroupsTitle = "Target Groups";
    public static String SelectedTitle = "Selected";

    @Override
    public String NameTrainingSheet() {
        return NameTrainingSheet;
    }

    @Override
    public String ModuleNumberTitle() {
        return ModuleNumberTitle;
    }


    @Override
    public String ModuleTitle() {
        return ModuleTitle;
    }

    @Override
    public String TopicTitle() {
        return TopicTitle;
    }

    @Override
    public String ActionTitle() {
        return ActionTitle;
    }

    @Override
    public String DurationActionTitle() {
        return DurationActionTitle;
    }

    @Override
    public String DurationTopicTitle() {
        return DurationTopicTitle;
    }

    @Override
    public String DurationModuleTitle() {
        return DurationModuleTitle;
    }

    @Override
    public String DurationTrainingTitle() {
        return DurationTrainingTitle;
    }

    @Override
    public String TargetGroupsTitle() {
        return TargetGroupsTitle;
    }

    @Override
    public String SelectedTitle() {
        return SelectedTitle;
    }
}
