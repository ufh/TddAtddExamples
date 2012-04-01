package com.qagile.tddatdd.tdd_basics.money;

import static org.junit.Assert.*;

import org.junit.Test;


public class AddCurrenciesTest {

	double wechselkursChf2Euro = 1.2;
	double wechselkursEuro2Chf = 1/1.2;
	
	ExchangeRateService.VALID_CURRENCIES chf = ExchangeRateService.VALID_CURRENCIES.CHF;
	ExchangeRateService.VALID_CURRENCIES euro = ExchangeRateService.VALID_CURRENCIES.EURO;
	ExchangeRateService.VALID_CURRENCIES dollar = ExchangeRateService.VALID_CURRENCIES.DOLLAR;	
	
	///////////////////////////////////////////
	//   Exercise: class that adds Euro and CHF
	//
	//   Wechselkurs
	//   W�hrung
	//   Zielw�hrung ber�cksichtigen!
	//   done Summands are changeable
	//   Wie kommt der Wechselkurs in die Methode?
	//   
	//   
	@Test
	public void addCurrency(){
		
		Adder adder = new Adder();
		ExchangeRateService service = new ExchangeRateService();
		
		assertEquals(5 + 10*service.getRate(chf, euro) ,adder.add(5,euro,10, chf, euro), 0.00001);
		assertEquals(7 + 10*service.getRate(chf, euro) ,adder.add(7,euro,10, chf, euro), 0.00001);
		assertEquals(7 + 10*service.getRate(chf, euro) ,adder.add(10,chf,7,euro, euro), 0.00001);
		assertEquals((7 + 10*service.getRate(chf, euro))*service.getRate(euro, chf) ,adder.add(10,chf,7,euro, chf), 0.00001);
		assertEquals(5 + 10 ,adder.add(5,euro,10, euro, euro), 0.00001);
		assertEquals(5 + 10 ,adder.add(5,chf,10, chf, chf), 0.00001);
		assertEquals(5 + 10*service.getRate(dollar, euro) ,adder.add(5,euro,10, dollar, euro), 0.00001);
		
	}
	
	@Test
	public void exchangeRateService(){
		
		ExchangeRateService service = new ExchangeRateService();
        assertEquals(wechselkursEuro2Chf, service.getRate(euro, chf), 0.00001);
        assertEquals(wechselkursChf2Euro, service.getRate(chf, euro), 0.00001);
        assertEquals(2, service.getRate(dollar, euro), 0.00001);
        assertEquals(1/2, service.getRate(euro,dollar), 0.00001);
		
	}
	
	
}
