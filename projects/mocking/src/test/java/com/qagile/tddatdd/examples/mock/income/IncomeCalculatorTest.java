package com.qagile.tddatdd.examples.mock.income;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

/**
 * Created by IntelliJ IDEA.
 * User: ful
 * Date: 12.03.12
 * Time: 19:12
 * To change this template use File | Settings | File Templates.
 */
public class IncomeCalculatorTest {
    
    private ICalcMethod calcMethodMock;
    private ICalcMethod secondCalcMethodMock;
    private IncomeCalculator calc;
    
    @Before
    public void setUp() throws Exception{
        calcMethodMock = EasyMock.createMock(ICalcMethod.class);
        calc = new IncomeCalculator();
    }

    @Test
    public void testCalc(){

        // Setting up the expected value of the method call calc
        EasyMock.expect(calcMethodMock.calc(Position.BOSS)).andReturn(70000.0).times(2);
        EasyMock.expect(calcMethodMock.calc(Position.PROGRAMMER)).andReturn(50000.0);
        // Setup is finished need to activate the mock
        EasyMock.replay(calcMethodMock);
        
        // calc has no position set
        calc.setCalcMethod(calcMethodMock);
        try{
            calc.calc();
            fail("Exception expected!");
        }catch (PositionException e) {

        }

        // set position and check calculation (as given in the mock setup)
        calc.setPosition(Position.BOSS);
        assertEquals(70000.0, calc.calc());
        // make sure that the mock reacts always the same
        assertEquals(70000.0, calc.calc());
        
        calc.setPosition(Position.PROGRAMMER);
        assertEquals(50000.0, calc.calc());

        calc.setPosition(Position.SURFER);
        EasyMock.verify(calcMethodMock);

    }

    @Test(expected = CalcMethodException.class)
    public void testNoCalc() {
        calc.setPosition(Position.SURFER);
        calc.calc();
    }

    @Test(expected = PositionException.class)
    public void testNoPosition() {
        EasyMock.expect(calcMethodMock.calc(Position.BOSS)).andReturn(70000.0);
        EasyMock.replay(calcMethodMock);
        calc.setCalcMethod(calcMethodMock);
        calc.calc();
    }

    @Test
    public void testSecondMethod() throws AgeException {
        EasyMock.expect(calcMethodMock.calcWithAge(Position.BOSS,45)).andReturn(100000.0);
        EasyMock.replay(calcMethodMock);
        
        calc.setCalcMethod(calcMethodMock);
        calc.setPosition(Position.BOSS);
        calc.setAge(45);

        assertEquals(100000.0, calc.calcWithAge());
    }

    @Test
    public void testSecondMockOfSameClass() throws AgeException {

        calcMethodMock = EasyMock.createMock(ICalcMethod.class);
        EasyMock.expect(calcMethodMock.calcWithAge(Position.BOSS,45)).andReturn(100000.0);
        EasyMock.replay(calcMethodMock);

        calc.setCalcMethod(calcMethodMock);
        calc.setPosition(Position.BOSS);
        calc.setAge(45);
        
        secondCalcMethodMock = EasyMock.createMock(ICalcMethod.class);
        EasyMock.expect(secondCalcMethodMock.calcWithAge(Position.SURFER,27)).andReturn(27000.0);
        EasyMock.replay(secondCalcMethodMock);

        IncomeCalculator calc02 = new IncomeCalculator();
        calc02.setCalcMethod(secondCalcMethodMock);
        calc02.setPosition(Position.SURFER);
        calc02.setAge(27);
        
        assertEquals(100000.0, calc.calcWithAge());
        assertEquals(27000.0, calc02.calcWithAge());
    }
}
