package com.qagile.simplestws;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 * User: ful
 * Date: 15.05.12
 * Time: 16:04
 */
public class HelloWorldTestClient {

    public static void main( final String[] args ) throws Throwable
    {
        String url = ( args.length > 0 ) ? args[0] : "http://localhost:4434/com/qagile/simplestws";
        Service service = Service.create(
                new URL( url + "?wsdl" ),
                new QName( "http://simplestws.qagile.com/", "HelloWorldImplService" ) );
        HelloWorld halloWelt = service.getPort( HelloWorld.class );
        System.out.println( "\n" + halloWelt.hallo( args.length > 1 ? args[1] : "" ) );
    }
}
