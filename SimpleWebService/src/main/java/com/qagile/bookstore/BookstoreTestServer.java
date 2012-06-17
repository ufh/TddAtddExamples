package com.qagile.bookstore;

import javax.swing.JOptionPane;
import javax.xml.ws.Endpoint;
import com.qagile.bookstore.ws.BuecherServiceImpl;
import com.qagile.simplestws.HelloWorldImpl;

/**
 * User: ful
 * Date: 15.05.12
 * Time: 21:48
 */
public class BookstoreTestServer {
    public static void main( final String[] args )
    {

        String   url = ( args.length > 0 ) ? args[0] : "http://localhost:4434/buecherservice";
        System.out.println("url: " + url);
        Endpoint ep = Endpoint.publish( url, new BuecherServiceImpl() );
        System.out.println("published");
        JOptionPane.showMessageDialog( null, "TestWsServer beenden" );
        ep.stop();
    }
}
