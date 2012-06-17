package com.qagile.bookstore.ws;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * User: ful
 * Service Interface
 */
@WebService
public interface BuecherService {
    BuecherTO createBuch(    @WebParam( name = "buch" ) BuchDO buch ) throws Exception;
    BuecherTO getBuchByIsbn( @WebParam( name = "isbn" ) Long   isbn );
    BuecherTO findeBuecher(  @WebParam( name = "buch" ) BuchDO buch );
}
