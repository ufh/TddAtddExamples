package com.qagile.tddatdd.rest.agilefant;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ful
 * Date: 27.03.12
 * Time: 23:19
 * To change this template use File | Settings | File Templates.
 */
public class Story {

    public Story(){

    }

    String objectId;
    String name;
    String state;
    List children;
    List hourEntries;
    List responsibles;
    List tasks;
    List projects;
}
