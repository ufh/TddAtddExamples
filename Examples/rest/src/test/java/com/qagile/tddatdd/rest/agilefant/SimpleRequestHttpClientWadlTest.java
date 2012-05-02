package com.qagile.tddatdd.rest.agilefant;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import net.sf.json.JSON;
import net.sf.json.xml.XMLSerializer;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

/**
 * Created by IntelliJ IDEA.
 * User: ful
 * Date: 23.03.12
 * Time: 19:53
 * To change this template use File | Settings | File Templates.
 */
public class SimpleRequestHttpClientWadlTest {

    private static Logger logger = Logger.getLogger("com.qagile.tddatdd.rest.agilefant");
    private HttpClient client ;
    private HttpMethod method;
    private final String GET_WADL     = "http://localhost:8080/agilefant/rs/application.wadl";
    private final String GET_PRODUCTS = "http://localhost:8080/agilefant/rs/product/list";
    private final String GET_PRODUCT  = "http://localhost:8080/agilefant/rs/product/";
    private final String GET_STORY    = "http://localhost:8080/agilefant/rs//story/";

    @Before
    public void setUp(){
        client = new HttpClient();
        client.getParams().setAuthenticationPreemptive(true);
        Credentials defaultcreds = new UsernamePasswordCredentials("agilefant", "agilefant");
        client.getState().setCredentials(new AuthScope("localhost", 8080, AuthScope.ANY_REALM), defaultcreds);
    }

    @Test
    public void testReadWadl() throws IOException {

        method = new GetMethod(GET_WADL);
        int statusCode = client.executeMethod(method);

        if (statusCode != HttpStatus.SC_OK) {
            String warning = "Executing method [" + method + "] return !ok: " + statusCode;
            logger.log(Level.WARNING, warning);
            fail(warning);
        }

        byte[] responseBody = method.getResponseBody();
        logger.info("--------   R E S P O N S E -----------\n" + method.getResponseBodyAsString());
        XMLSerializer xmlSerializer = new XMLSerializer();
        JSON json = xmlSerializer.read( method.getResponseBodyAsString() );
        logger.info("------------  J A S O N ------------\n" + json);
    }

    @Test
    public void testConvertXml2json() throws IOException {

        method = new GetMethod(GET_WADL);
        int statusCode = client.executeMethod(method);

        if (statusCode != HttpStatus.SC_OK) {
            String warning = "Executing method [" + method + "] return !ok: " + statusCode;
            logger.log(Level.WARNING, warning);
            fail(warning);
        }

        byte[] responseBody = method.getResponseBody();

        XMLSerializer xmlSerializer = new XMLSerializer();
        JSON json = xmlSerializer.read( method.getResponseBodyAsString() );
        logger.info("----------------- Json  --------------------\n" + json );

    }

    @Test
    public void testReadProductList() throws IOException {
        method = new GetMethod(GET_PRODUCTS);
        JSON json = getResponseJson(method);
        String jsonString = json.toString().replace("@","");
        
        Gson gson = new Gson();
        List<Map> productList = gson.fromJson(jsonString, List.class);
        assertEquals(3,productList.size());

        logger.info("Found: " + productList);
        Products products = new Products();
        Iterator<Map> it = productList.iterator();
        while(it.hasNext() ){            
            Map next = it.next();
            ProductInfo p = new ProductInfo();
            p.createProductInfo(next);
            products.getProducts().add(p);
            logger.info("Name: " + p.getName());
        }
    }

    @Test
    public void testReadProductsFromJson(){
        
        String jResponse = "[{\"@objectId\":\"3\",\"@name\":\"ProductTest02\"},{\"@objectId\":\"1\",\"@name\":\"TDD_ATDD_Training\"},{\"@objectId\":\"2\",\"@name\":\"ProductTest01\"}]";
        jResponse = jResponse.replace("@","");

        Gson gson = new Gson();
        Products products = gson.fromJson(jResponse, Products.class);

        assertEquals(3,products.getProducts().size());
    }


    @Test
    public void testProductListAsString(){

        String json = "[{\"@objectId\":\"17\",\"@name\":\"blabla\"},{\"@objectId\":\"18\",\"@name\":\"murks\"}]";
        json = json.replace("@","");
        Gson gson = new Gson();
        List<Map> productList = gson.fromJson(json, List.class);
        assertEquals(2,productList.size());
        
        logger.info("Found: " + productList);
        Products products = new Products();
        Iterator<Map> it = productList.iterator();
        while(it.hasNext() ){
            Map next = it.next();
            ProductInfo p = new ProductInfo();
            p.createProductInfo(next);
            products.getProducts().add(p);
        }

        method = new GetMethod(GET_WADL);
    }


    @Test
    public void testReadProduct() throws Exception{
        method = new GetMethod(GET_PRODUCT + "1");

        JSON response = getResponseJson(method);
        String jsonString = response.toString().replace("@","");

        Gson gson = new Gson();

        Product product = gson.fromJson(jsonString.toString(), Product.class);
        logger.info("Name: " + product.name);
        logger.info("desc: " + product.description);
        logger.info("id: " + product.objectId);
        logger.info("Stories: " + product.stories.objectId);
        logger.info("Stories: " + product.stories.name);
        logger.info("Stories: " + product.stories.state);

    }

    @Test
    public void testreadStory(){
        String storyString = "{\"@objectId\":\"1\",\"@name\":\"First Story Bla\",\"@state\":\"NOT_STARTED\",\"children\":[],\"hourEntries\":[],\"responsibles\":[],\"tasks\":[]}";
        storyString = storyString.replace("@","");
        Gson gson = new Gson();
        
        Story story = gson.fromJson(storyString, Story.class);
        assertEquals("1", story.objectId);
        assertEquals("First Story Bla", story.name);

        method = new GetMethod(GET_WADL);
        
    }
    
    private JSON getResponseJson(HttpMethod httpMethod) throws IOException {
        int statusCode = client.executeMethod(httpMethod);
        if (statusCode != HttpStatus.SC_OK) {
            String warning = "Executing method [" + httpMethod + "] return !ok: " + statusCode;
            logger.log(Level.WARNING, warning);
            fail(warning);
        }

        logger.info("XML response: " + httpMethod.getResponseBodyAsString());
        XMLSerializer xmlSerializer = new XMLSerializer();
        JSON json = xmlSerializer.read( httpMethod.getResponseBodyAsString() );
        logger.info("----------------- Json  --------------------\n" + json );
        return json;
    }

    private String getResponseString(HttpMethod httpMethod) throws IOException {
        
        int statusCode = client.executeMethod(httpMethod);
        if (statusCode != HttpStatus.SC_OK) {
            String warning = "Executing method [" + httpMethod + "] return !ok: " + statusCode;
            logger.log(Level.WARNING, warning);
            fail(warning);
        }

        return httpMethod.getResponseBodyAsString();
    }

    @After
    public void cleanUp(){
        method.releaseConnection();
    }
}
