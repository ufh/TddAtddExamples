package com.qagile.tddatdd.examples.mock.income;

/**
 * Created by IntelliJ IDEA.
 * User: ful
 * Date: 12.03.12
 * Time: 20:53
 * To change this template use File | Settings | File Templates.
 */
public interface IDbObject {
    public String doQuery(String query) throws NotFoundException;
}
