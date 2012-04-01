package com.qagile.tddatdd.rest.agilefant;

import org.junit.Test;

import java.util.logging.Logger;

//import static com.jayway.restassured.RestAssured.as;
import static com.jayway.restassured.path.xml.XmlPath.from;
import static com.jayway.restassured.RestAssured.given;


import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: ful
 * Date: 26.03.12
 * Time: 18:43
 * To change this template use File | Settings | File Templates.
 */
public class ReadObjectTest {

    private static Logger logger = Logger.getLogger("bla");

    @Test
    public void testMapXML2Object(){

       String response = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><message content=\"this is the message\"/>";
       String messageString = from(response).get("message");
       logger.info("message get from string: " + messageString);
//       Message message = from(response).get("message").as(Message.class);
//       assertEquals("this is the message", message.getContent());
    }

}
