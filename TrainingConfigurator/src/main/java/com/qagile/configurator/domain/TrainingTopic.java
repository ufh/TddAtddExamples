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

    @Override
    public boolean equals(Object object){

        if ( object == null )
            return false;

        if ( object == this )
            return true;

        TrainingTopic topic2compare = (TrainingTopic) object;
        if (!this.name.equals(topic2compare.name)){
            return false;
        }
        if (!this.actions.equals(topic2compare.actions)){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {

        int hash = name.hashCode();
        hash += actions.hashCode();
        return hash;
    }
}
