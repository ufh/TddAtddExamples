package com.qagile.tddatdd.examples.mock.income;

/**
 * Created by IntelliJ IDEA.
 * User: ful
 * Date: 12.03.12
 * Time: 19:10
 * To change this template use File | Settings | File Templates.
 */
public interface ICalcMethod {

    public abstract double calc(Position position);
    public abstract double calcWithAge(Position position, int age);

}
