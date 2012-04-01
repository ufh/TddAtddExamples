package com.qagile.tddatdd.tdd_basics.helloworld;

import com.qagile.tddatdd.tdd_basics.helloworld.Greeting01;
import com.qagile.tddatdd.tdd_basics.helloworld.Greeting02;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.security.InvalidParameterException;

import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: ful
 * Date: 18.03.12
 * Time: 21:03
 * To change this template use File | Settings | File Templates.
 */
public class HelloWorldTest {

    private Greeting02 greets;
    
    @Before
    public void setup(){
         greets =  new Greeting02();
    }
    
    /**
     * The simplest possible tdd example :-)
     */
    @Test
    public void testSimpleGreeting(){

        Greeting01 greets = new Greeting01();
        assertEquals("Hello SQS", greets.greet());
    }

    /**
     * The simplest possible tdd example :-)
     */
    @Test
    public void testGreeting02(){

        assertEquals("Hello SQS", greets.greet("SQS"));
        assertEquals("Hello TDD", greets.greet("TDD"));
    }

    @Test(expected = InvalidParameterException.class)
    public void testExpectedException(){
        greets.greet(null);
    }

    @After
    public void cleanUp(){
        System.out.println("Cleaning up something ...");
    }
    
}
