package com.qagile.tddatdd.tdd_basics.helloworld;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: ful
 * Date: 18.03.12
 * Time: 21:20
 * To change this template use File | Settings | File Templates.
 */
@RunWith(Parameterized.class)
public class HelloWorldParameterizedTest {

    private String whoToGreet;
    
    public HelloWorldParameterizedTest(String who){
        this.whoToGreet = who;
    }
    
    @Test
    public void testValidGreetingStrings(){

        Greeting02 greets = new Greeting02();
        assertEquals("Test with param:" + whoToGreet, "Hello " + whoToGreet, greets.greet(whoToGreet));
    }


    @Parameterized.Parameters
    public static List<String[]> generateData(){
    //public static Collection<Object[]> generateData(){

        List<String[]> list = new ArrayList<String[]>();
        
        list.add(new String[]{"Hans"});
        list.add(new String[]{"Erika"});
        list.add(new String[]{"Mueller Luedenscheid"});

        return  list;
    }
    
}
