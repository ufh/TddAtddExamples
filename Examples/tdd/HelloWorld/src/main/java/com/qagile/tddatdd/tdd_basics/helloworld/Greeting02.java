package com.qagile.tddatdd.tdd_basics.helloworld;

import java.security.InvalidParameterException;

/**
 * Created by IntelliJ IDEA.
 * User: ful
 * Date: 18.03.12
 * Time: 21:05
 * To change this template use File | Settings | File Templates.
 */
public class Greeting02 {

    public String greet(String who) {

        if (who == null){
            throw new InvalidParameterException("Don't know who to greet");
        }
        return "Hello " + who;
    }

}
