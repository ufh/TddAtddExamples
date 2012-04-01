package com.qagile.tddatdd.agilefant.domain;

import com.qagile.tddatdd.config.UnsupportedDriverException;

/**
 * Created by IntelliJ IDEA.
 * User: ful
 * Date: 23.03.12
 * Time: 16:35
 * To change this template use File | Settings | File Templates.
 */
public interface Dsl {

    public void setUp() throws UnsupportedDriverException;
    public void authenticate(String user, String pwd) throws UnsupportedDriverException;
    public void createNewStory(Story story) throws UnsupportedDriverException;
    public void createNewProduct(Product product) throws UnsupportedDriverException;

}
