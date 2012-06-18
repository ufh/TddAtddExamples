package com.qagile.bookstore.qatest;

import com.qagile.bookstore.ws.BuchDO;
import com.qagile.bookstore.ws.BuecherService;
import com.qagile.bookstore.ws.BuecherTO;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.xml.namespace.QName;
import javax.xml.ws.*;
import java.net.*;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

/**
 * User: ful
 * Tests with the bookstore service in a project of its own,
 * so simulating a client.
 * These tests assume that the service is running.
 * In the root directory of the service call
 * mvn jetty:run-war
 * TODO: works in IntelliJ but not from console. Make it work!!
 */
public class BookstoreTests {

    // config params
    private Service service = null;
    private BuecherService buecherService = null;
    private String url = "http://localhost:4434/SimpleWebService/ws/BuecherService";
    private String namespaceUri = "http://ws.bookstore.qagile.com/";
    private String localPart = "BuecherServiceImplService";

    private Logger logger = Logger.getLogger("com.qagile.bookstore.qatest");

    @BeforeClass
    public void getService() throws MalformedURLException {

        service = Service.create(
                new URL( url + "?wsdl" ),
                new QName( namespaceUri, localPart ) );
        buecherService = service.getPort( BuecherService.class );
    }

    @Test
    public void testFindBooks(){

        BuecherTO response = buecherService.findeBuecher(new BuchDO());
        assertEquals((Object) 0, response.getReturncode());
        logger.info("Number of books: " + response.getResults().size());
    }

    @Test
    public void testCreateBook() throws Exception {

        Long isbn = new Long(1234);

        BuchDO expectedBuch = new BuchDO();
        expectedBuch.setIsbn(isbn);
        expectedBuch.setTitel("Fun with Testing...");
        expectedBuch.setPreis(25.95);
        BuecherTO response = buecherService.createBuch(expectedBuch);
        assertEquals((Object) 0, response.getReturncode());

        response = buecherService.getBuchByIsbn(isbn.intValue());
        assertEquals(1, response.getResults().size());
        assertEquals("Buch mit ISBN " + isbn, response.getMessage());

        BuchDO actualBuch = response.getResults().get(0);
        assertEquals(25.95, actualBuch.getPreis());
        assertEquals("Fun with Testing...", actualBuch.getTitel());

    }
}
