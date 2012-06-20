package com.qagile.tddatdd.selenium.agilefant;

import com.qagile.tddatdd.agilefant.selenium.pages.GeneralPage;
import com.qagile.tddatdd.agilefant.selenium.pages.LoginPage;
import com.qagile.tddatdd.agilefant.selenium.pages.NewProductPage;
import com.qagile.tddatdd.agilefant.selenium.pages.NewStoryPage;
import com.qagile.tddatdd.agilefant.selenium.pages.StartPage;
import com.qagile.tddatdd.config.DriverTypes;
import com.qagile.tddatdd.config.SeleniumProperties;
import com.qagile.tddatdd.config.UnsupportedDriverException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: ful
 * Date: 19.03.12
 * Time: 18:54
 * To change this template use File | Settings | File Templates.
 */
@RunWith(value = Parameterized.class)
public class Exercise04_RefactorPageObjects {

    private final static Logger logger = Logger.getLogger("com.qagile.tddatdd.selenium.agilefant");

    private DriverTypes browser;
    
    private StartPage startPage;

    public Exercise04_RefactorPageObjects(DriverTypes browser){
        this.browser = browser;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] { { DriverTypes.CHROME }, { DriverTypes.FIREFOX } };
        return Arrays.asList(data);
    }

    /**
     * Open Browser and login
     */
    @Before
    public void start() throws UnsupportedDriverException {
        // Create a new instance of the Firefox driver
        // Notice that the remainder of the code relies on the interface,
        // not the implementation.
        SeleniumProperties.browser = browser;
        GeneralPage.setDriver(SeleniumProperties.browser);
        GeneralPage.start();

        LoginPage loginPage = new LoginPage();
        startPage = loginPage.login(SeleniumProperties.adminName, SeleniumProperties.adminPwd);

    }

    @Test
    public void testCreateProduct() throws UnsupportedDriverException {


        //////////////////////////////////////////////////////////////////////////////////////
        //now we are logged in, let's open a new product
        //click createNewProduct
        NewProductPage newProductPage = startPage.navigateToNewProduct();

        //enter name and description
        newProductPage.compileFormular("Selenium created Product #03 (Exercise #4)" + browser, "Product #03 is the coolest product ever!(refactored)");

        //submit
        newProductPage.submit();

    }

    @Test
    public void testCreateStory() throws UnsupportedDriverException {

        //////////////////////////////////////////////////////////////////////////////////////
        NewStoryPage newStoryPage = startPage.navigateToNewStory();

        //enter mandatory data for the story
        // id s are generated - there is no chance to identify the elements!
        newStoryPage.compileFormular("Selenium created Product #03 (Exercise #4)" + browser, "Name of the Story (refactored)", "13", "Super urgent story!!!!");

        //submit
        newStoryPage.submit();
    }

    @After
    public void cleanUp(){
        ///////////////////////////////////////////////////////////////////////////////////////
        // at the end log out and close selenium drivers
        startPage.logout();

        logger.info("Logged out, now closing browser... ");

        GeneralPage.stop();
    }

}
