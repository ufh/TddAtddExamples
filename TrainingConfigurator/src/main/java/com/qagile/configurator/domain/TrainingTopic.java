package com.qagile.configurator.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * User: ful
 * Date: 01.05.12
 * Time: 18:32
 */
public class TrainingTopic {

    public String name = "";

    public List<TrainingAction> actions = new ArrayList<TrainingAction>();

    public List<TrainingAction> getActions() {
        return actions;
    }
}
