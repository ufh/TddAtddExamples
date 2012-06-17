package com.qagile.bookstore.ws;

/**
 * User: ful
 * DO -> Domain Object
 * without Web service and db information
 */
public class BuchDO {

    private Long   isbn;
    private String titel;
    private Double preis;

    public Long   getIsbn()  { return isbn;  }
    public String getTitel() { return titel; }
    public Double getPreis() { return preis; }
    public void setIsbn(  Long   isbn  ) { this.isbn  = isbn;  }
    public void setTitel( String titel ) { this.titel = titel; }
    public void setPreis( Double preis ) { this.preis = preis; }
}
