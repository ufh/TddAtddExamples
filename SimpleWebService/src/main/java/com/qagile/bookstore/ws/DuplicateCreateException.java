package com.qagile.bookstore.ws;

/**
 * User: ful
 * Date: 15.05.12
 * Time: 21:35
 */
public class DuplicateCreateException extends Exception {
    private static final long serialVersionUID = 1L;

    // (der dummy-Parameter wird erst spaeter benoetigt)
    public DuplicateCreateException( String err, Object dummy )
    {
        super( err );
    }
}
