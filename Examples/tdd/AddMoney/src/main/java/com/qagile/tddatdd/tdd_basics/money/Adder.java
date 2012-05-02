package com.qagile.tddatdd.tdd_basics.money;


public class Adder {

	ExchangeRateService.VALID_CURRENCIES defaultCurrency = ExchangeRateService.VALID_CURRENCIES.EURO;
	ExchangeRateService rateService = new ExchangeRateService();
	
	public Double add(double i, ExchangeRateService.VALID_CURRENCIES waehrung1, double j, ExchangeRateService.VALID_CURRENCIES waehrung2, ExchangeRateService.VALID_CURRENCIES zielwaehrung) {		
		
		i = normalize(i, waehrung1);
		j = normalize(j, waehrung2);
				
    	return (i + j)* rateService.getRate(defaultCurrency, zielwaehrung); 
	}

	private double normalize(double i, ExchangeRateService.VALID_CURRENCIES waehrung1) {
		if(!waehrung1.equals(defaultCurrency)){
			i = i*rateService.getRate(waehrung1,defaultCurrency);
		}
		return i;
	}

}
