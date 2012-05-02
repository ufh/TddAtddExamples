package com.qagile.tddatdd.agilefant.domain;

import com.qagile.tddatdd.agilefant.selenium.pages.*;
import com.qagile.tddatdd.config.SeleniumProperties;
import com.qagile.tddatdd.config.UnsupportedDriverException;

/**
 * Created by IntelliJ IDEA.
 * User: ful
 * Date: 23.03.12
 * Time: 16:28
 * To change this template use File | Settings | File Templates.
 */
public class SeleniumDsl implements Dsl {

    private static StartPage startPage;
    
    @Override
    public void setUp() throws UnsupportedDriverException {
        // Create a new instance of the Firefox driver
        // Notice that the remainder of the code relies on the interface,
        // not the implementation.
        GeneralPage.setDriver(SeleniumProperties.browser);
        GeneralPage.start();
    }

    @Override
    public void authenticate(String user, String pwd) throws UnsupportedDriverException {
        LoginPage loginPage = new LoginPage();
        startPage = loginPage.login(user, pwd);
    }

    @Override
    public void createNewStory(Story story) throws UnsupportedDriverException {
        //////////////////////////////////////////////////////////////////////////////////////
        NewStoryPage newStoryPage = startPage.navigateToNewStory();

        //enter mandatory data for the story
        // id s are generated - there is no chance to identify the elements!
        newStoryPage.compileFormular(story);

        //submit
        newStoryPage.submit();
    }

    @Override
    public void createNewProduct(Product product) throws UnsupportedDriverException {
        //////////////////////////////////////////////////////////////////////////////////////
        //now we are logged in, let's open a new product
        //click createNewProduct
        NewProductPage newProductPage = startPage.navigateToNewProduct();

        //enter name and description
        newProductPage.compileFormular(product);

        //submit
        newProductPage.submit();        
    }
}
