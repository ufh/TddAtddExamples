package com.qagile.simplestws;

import javax.jws.*;

/**
 * User: ful
 * Date: 15.05.12
 * Time: 15:59
 */
@WebService
public interface HelloWorld {
    public String hallo( @WebParam( name = "wer" ) String wer );
}
