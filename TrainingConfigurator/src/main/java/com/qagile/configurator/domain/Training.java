package com.qagile.configurator.domain;

import java.util.*;

/**
 * User: ful
 * Date: 22.04.12
 * Time: 21:39
 */
public class Training {

    private String version;
    private String language;
    private String moduleTitle;
    private String topicTitle;
    private String depthTitle;
    private String durationTitle;
    private String selectedTitle;
    private String targetGroupTitle;
    private String moduleNumberTitle;

    private Map<String, TrainingModule> modules = new HashMap<String, TrainingModule>();


    public String version(){
        return version;
    }

    public String language() {
        return language;
    }

    public void setVersion(String version) {
        this.version = version;
    }
    public void setLanguage(String language) {
        this.language = language;
    }

    public String getModuleTitle() {
        return moduleTitle;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public String getDepthTitle() {
        return depthTitle;
    }

    public String getDurationTitle() {
        return durationTitle;
    }

    public String getSelectedTitle() {
        return selectedTitle;
    }

    public String getTargetGroupTitle() {
        return targetGroupTitle;
    }

    public void setModuleTitle(String moduleTitle) {
        this.moduleTitle = moduleTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public void setDepthTitle(String depthTitle) {
        this.depthTitle = depthTitle;
    }

    public void setDurationTitle(String durationTitle) {
        this.durationTitle = durationTitle;
    }

    public void setSelectedTitle(String selectedTitle) {
        this.selectedTitle = selectedTitle;
    }

    public void setTargetGroupTitle(String targetGroupTitle) {
        this.targetGroupTitle = targetGroupTitle;
    }

    public void addModule(TrainingModule module) {
        modules.put(module.getNumber(), module);
    }

    public Map<String, TrainingModule> getModules() {
        return modules;
    }

    public String getModuleNumberTitle() {
        return moduleNumberTitle;
    }

    public void setModuleNumberTitle(String moduleNumberTitle) {
        this.moduleNumberTitle = moduleNumberTitle;
    }

    public String toTable() {

        StringBuffer table = new StringBuffer();
        table.append("-------------------------------------\n");
        Iterator<String> it = modules.keySet().iterator();
        TrainingModule module;
        while (it.hasNext()){
            module = modules.get(it.next());
            table.append(module.getNumber() + "| " + module.getName() + "\n");
            for(TrainingTopic topic : module.getTopics()){
                table.append("....");
                table.append(topic.name);
                for (TrainingAction action : topic.actions){
                    table.append("........");
                    table.append(action.depth + "|");
                    table.append(action.duration + "|");
                    table.append(action.targetGroup + "|");
                    table.append(action.selected + "\n");
                }
            }
        }
        table.append("-------------------------------------\n");
        return table.toString();
    }

    public TrainingModule getModuleById(String id) {

        return modules.get(id);
    }
}
