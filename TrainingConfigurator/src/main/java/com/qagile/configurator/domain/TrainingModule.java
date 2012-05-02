package com.qagile.configurator.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * User: ful
 * Date: 01.05.12
 * Time: 17:13
 */
public class TrainingModule {

    private String number;
    private String name;
    private List<TrainingTopic> topics = new ArrayList<TrainingTopic>();

    public TrainingModule(String number, String name) {

        this.number = number;
        this.name = name;
    }

    public TrainingModule() {
        super();
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TrainingTopic> getTopics() {
        return topics;
    }

    public void setTopics(List<TrainingTopic> topics) {
        this.topics = topics;
    }
}
