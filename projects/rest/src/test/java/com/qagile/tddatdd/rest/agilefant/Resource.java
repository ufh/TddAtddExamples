package com.qagile.tddatdd.rest.agilefant;

/**
 * Created by IntelliJ IDEA.
 * User: ful
 * Date: 27.03.12
 * Time: 22:26
 * To change this template use File | Settings | File Templates.
 * "resource":
 * [
 * {"@path":"/story/{storyId}",
 *  "param":
 *    {"@xmlns:xs":"http://www.w3.org/2001/XMLSchema",
 *     "@name":"storyId","@style":"template"},
 *     "method":
 *        {"@id":"get","@name":"GET","response":
 *             {"representation":
 *               [
 *                  {"@mediaType":"application/xml"},
 *                  {"@mediaType":"text/xml"}
 *               ]
 *               }
 *               }
 *               }
 */
public class Resource {
    String path;
    Param param;


}
