package com.qagile.tddatdd.rest.agilefant;

/**
 * Created by IntelliJ IDEA.
 * User: ful
 * Date: 27.03.12
 * Time: 22:32
 * To change this template use File | Settings | File Templates.
 * *     "method":
 *        {"@id":"get",
 *        "@name":"GET",
 *        "response":
 *             {"representation":
 *               [
 *                  {"@mediaType":"application/xml"},
 *                  {"@mediaType":"text/xml"}
 *               ]
 *               }
 */
public class Method {
    String id;
    String name;
    Representation response;
    
}
