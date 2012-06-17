package com.qagile.simplestws;

import javax.jws.WebService;

/**
 * User: ful
 * Date: 15.05.12
 * Time: 16:01
 */
@WebService( endpointInterface="com.qagile.simplestws.HelloWorld" )
public class HelloWorldImpl implements HelloWorld {

    public String hallo( String wer )
    {
        return "Hallo " + wer;
    }
}
