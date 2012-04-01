package com.qagile.tddatdd.rest.agilefant;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ful
 * Date: 26.03.12
 * Time: 23:12
 * To change this template use File | Settings | File Templates.
 */
public class ProductInfo {

    private String id;
    private String name;

    public void createProductInfo(Map propertyMap) {
        id = String.valueOf(propertyMap.get("objectId"));
        name = String.valueOf(propertyMap.get("name"));
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
