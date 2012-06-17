package com.qagile.simplestws;

import javax.xml.ws.Endpoint;

/**
 * User: ful
 * Date: 15.05.12
 * Time: 16:03
 */
public class HelloWorldTestServer {

    public static void main( final String[] args )
    {
        String url = ( args.length > 0 ) ? args[0] : "http://localhost:4434/com/qagile/simplestws";
        Endpoint.publish(url, new HelloWorldImpl());
    }
}
