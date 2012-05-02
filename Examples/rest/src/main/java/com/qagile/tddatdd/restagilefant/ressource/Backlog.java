package com.qagile.tddatdd.restagilefant.ressource;

/**
 * Created by IntelliJ IDEA.
 * User: ful
 * Date: 25.03.12
 * Time: 22:56
 * To change this template use File | Settings | File Templates.
 */
public class Backlog {

    String objectId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;
    String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getObjectId() {

        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }


}
