package com.qagile.configurator.domain;

import java.util.logging.Logger;

public class TrainingAction {

    Logger logger = Logger.getLogger("com.qagile.configurator.domain");

    public String depth = "";
    public String duration = "";
    public String targetGroup = "";
    public String selected = "";


    public TrainingAction() {
    }

    @Override
    public boolean equals(Object object){

        if ( object == null )
            return false;

        if ( object == this )
            return true;

        TrainingAction action2compare = (TrainingAction) object;
        if (!this.depth.equals(action2compare.depth)){
            logger.info("depth not equal");
            return false;
        }
        if (!this.duration.equals(action2compare.duration)){
            logger.info("duration not equal");
            return false;
        }
        if (!this.targetGroup.equals(action2compare.targetGroup)){
            logger.info("targetGroup not equal");
            return false;
        }
        if (!this.selected.equals(action2compare.selected)){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {

        int hash = depth.hashCode();
        hash += duration.hashCode();
        hash += targetGroup.hashCode();
        hash += selected.hashCode();
        return hash;
    }

}