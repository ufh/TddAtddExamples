package com.qagile.configurator.excel;

/**
 * User: ful
 * Date: 30.04.12
 * Time: 22:34
 */
public interface LanguageSupport {

    public static String NameTrainingSheet = "TrainingX";
    public static String ModuleNumberTitle = "ModuleNumberX";
    public static String ModuleTitle = "ModuleX";
    public static String TopicTitle = "TopicX";
    public static String DepthTitle = "DepthX";
    public static String DurationTitle = "DurationX";
    public static String TargetGroupsTitle = "Target GroupsX";
    public static String SelectedTitle = "SelectedX";

    public String NameTrainingSheet();
    public String ModuleNumberTitle();
    public String ModuleTitle();
    public String TopicTitle();
    public String ActionTitle();
    public String DurationActionTitle();
    public String DurationTopicTitle();
    public String DurationModuleTitle();
    public String DurationTrainingTitle();
    public String TargetGroupsTitle();
    public String SelectedTitle();
}
