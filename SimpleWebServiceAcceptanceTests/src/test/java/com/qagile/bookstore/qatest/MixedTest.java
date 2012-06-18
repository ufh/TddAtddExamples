package com.qagile.bookstore.qatest;

import com.qagile.bookstore.ws.BuchDO;
import com.qagile.bookstore.ws.BuecherService;
import com.qagile.bookstore.ws.BuecherTO;
import com.qagile.tddatdd.agilefant.domain.Dsl;
import com.qagile.tddatdd.agilefant.domain.Product;
import com.qagile.tddatdd.agilefant.domain.SeleniumDsl;
import com.qagile.tddatdd.agilefant.domain.Story;
import com.qagile.tddatdd.agilefant.selenium.pages.GeneralPage;
import com.qagile.tddatdd.agilefant.selenium.pages.LoginPage;
import com.qagile.tddatdd.agilefant.selenium.pages.NewProductPage;
import com.qagile.tddatdd.agilefant.selenium.pages.StartPage;
import com.qagile.tddatdd.config.DriverTypes;
import com.qagile.tddatdd.config.SeleniumProperties;
import com.qagile.tddatdd.config.UnsupportedDriverException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;
import java.util.List;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * User: ful
 * Date: 17.06.12
 * In this test a web service and a GUI are involved to demonstrate
 * end 2 end tests.
 * This test needs a running Agilefant and bookstore service
 */
public class MixedTest {

    // webservice  params
    private Service webService = null;
    private BuecherService buecherService = null;
    private String url = "http://localhost:4434/SimpleWebService/ws/BuecherService";
    private String namespaceUri = "http://ws.bookstore.qagile.com/";
    private String localPart = "BuecherServiceImplService";

    // Selenium params
    Product product = new Product();
    private DriverTypes browser = DriverTypes.FIREFOX;
    private StartPage startPage;

    private Logger logger = Logger.getLogger("com.qagile.bookstore.qatest");


    @BeforeClass
    public void setUp() throws Exception {

        // enabling Webservice
        webService = Service.create(
                new URL( url + "?wsdl" ),
                new QName( namespaceUri, localPart ) );
        buecherService = webService.getPort( BuecherService.class );

        // creating some books in the store
        createTestBooks(3);

        // enabling Selenium Gui driver service
        // and login
        product.title = "Missing Books Project";
        product.description = "Contains a story for every missing book...";

        SeleniumProperties.browser = browser;
        GeneralPage.setDriver(SeleniumProperties.browser);
        GeneralPage.start();

        LoginPage loginPage = new LoginPage();
        startPage = loginPage.login(SeleniumProperties.adminName, SeleniumProperties.adminPwd);

    }

    @Test
    public void createStoryForEachBooks() throws UnsupportedDriverException {

        List<BuchDO> buecherListe = readBooks();

        createProjectForMissingBooks();

        for (BuchDO buch : buecherListe){
            createStory(buch);
        }

    }

    @AfterClass
    public void tearDown(){

        startPage.logout();

        logger.info("Logged out, now closing browser... ");

        GeneralPage.stop();
    }

    private void createProjectForMissingBooks() throws UnsupportedDriverException {
        //////////////////////////////////////////////////////////////////////////////////////
        //now we are logged in, let's open a new product
        //click createNewProduct
        NewProductPage newProductPage = startPage.navigateToNewProduct();

        //enter name and description
        newProductPage.compileFormular("Mixed Test Project", "Add a story for every missing book ftom Bookstore webservice.");

        //submit
        newProductPage.submit();
    }

    private void createStory(BuchDO buch) {
        Story story = new Story();
        story.backlog = product;
        story.title = "Title: " + buch.getTitel();
        story.estimation = "10";
        story.description = "Isbn: " + buch.getIsbn() + "/ Preis: " + buch.getPreis();
    }

    private List<BuchDO> readBooks() {

        BuecherTO response =  buecherService.findeBuecher(new BuchDO());
        assertEquals((Object) 0, response.getReturncode());
        assertTrue(response.getResults().size() > 0);

        return response.getResults();
    }

    private void createTestBooks(int count) throws Exception {

        Long startIsbn = new Long(1000);
        for (int i=0; i<count; i++ ){
            BuchDO newBook = new BuchDO();
            newBook.setIsbn(startIsbn + i);
            newBook.setTitel("Fun with Testing Part " + i);
            newBook.setPreis(25.95);
            BuecherTO response = buecherService.createBuch(newBook);
            assertEquals((Object) 0, response.getReturncode());
        }

    }

}
