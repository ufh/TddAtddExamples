package com.qagile.tddatdd.selenium.agilefant;

import com.qagile.tddatdd.agilefant.domain.Dsl;
import com.qagile.tddatdd.agilefant.domain.SeleniumDsl;
import com.qagile.tddatdd.agilefant.domain.Product;
import com.qagile.tddatdd.agilefant.domain.Story;
import com.qagile.tddatdd.agilefant.selenium.pages.*;
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
public class Exercise05_RefactorDomainSpecificLanguage {

    private final static Logger logger = Logger.getLogger("com.qagile.tddatdd.selenium.agilefant");

    private DriverTypes browser;

    private StartPage startPage;
    
    private Dsl dsl = new SeleniumDsl();

    /////////////////////////////
    // test data
    Product product = new Product();
    Story   story   = new Story();

    public Exercise05_RefactorDomainSpecificLanguage(DriverTypes browser){
        this.browser = browser;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] { { DriverTypes.CHROME }};// , { DriverTypes.FIREFOX } };
        return Arrays.asList(data);
    }

    /**
     * Open Browser and login
     */
    @Before
    public void start() throws UnsupportedDriverException {

        // setup product
        product.title = "Selenium created Product #01 (refactored)";
        product.description = "Product #01 is the coolest product ever!(refactored)";

        // setup story
        Story story = new Story();
        story.backlog = product;
        story.title = "Name of the Story (refactored)";
        story.estimation = "13";
        story.description = "Super urgent story!!!!";

        SeleniumProperties.browser = browser;
        dsl.setUp();

        dsl.authenticate(SeleniumProperties.adminName, SeleniumProperties.adminPwd);
    }

    @Test
    public void testCreateProduct() throws UnsupportedDriverException {

        dsl.createNewProduct(product);

    }

    @Test
    public void testCreateStory() throws UnsupportedDriverException {

        dsl.createNewStory(story);

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
