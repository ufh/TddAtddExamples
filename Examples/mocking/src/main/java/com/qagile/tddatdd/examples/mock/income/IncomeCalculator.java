package com.qagile.tddatdd.examples.mock.income;

/**
 * Created by IntelliJ IDEA.
 * User: ful
 * Date: 12.03.12
 * Time: 19:11
 * To change this template use File | Settings | File Templates.
 */
public class IncomeCalculator{

    private ICalcMethod calcMethod;
    private Position position;
    private int age;

    public void setCalcMethod(ICalcMethod calcMethod){
        this.calcMethod = calcMethod;
    }
    public void setPosition(Position position){
        this.position = position;
    }
    public double calc (){
        if (calcMethod==null){
            throw new CalcMethodException("CalcMethod not yet maintained");
        }
        if (position==null){
            throw new PositionException("Position not yet maintained");
        }
        return calcMethod.calc(position);
    }


    public void setAge(int age) {
        this.age = age;
    }

    public double calcWithAge() throws AgeException {
        if (calcMethod==null){
            throw new CalcMethodException("CalcMethod not yet maintained");
        }
        if (position==null){
            throw new PositionException("Position not yet maintained");
        }
        if (age < 20 && age > 70){
            throw new AgeException("Unbelievable age: " + age);
        }

        return calcMethod.calcWithAge(position, age);
    }
}
