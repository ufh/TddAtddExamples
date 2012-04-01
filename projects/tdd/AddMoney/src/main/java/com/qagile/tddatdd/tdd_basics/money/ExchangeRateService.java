package com.qagile.tddatdd.tdd_basics.money;

public class ExchangeRateService {
	
	public static enum VALID_CURRENCIES {CHF, EURO, DOLLAR};

	public double getRate(VALID_CURRENCIES from, VALID_CURRENCIES to) {

		if(from.equals(VALID_CURRENCIES.EURO)&&(to.equals(VALID_CURRENCIES.CHF))){
			return 1/1.2;
		}
		if(from.equals(VALID_CURRENCIES.CHF)&&(to.equals(VALID_CURRENCIES.EURO))){
			return 1.2;
		}
		
		if(from.equals(VALID_CURRENCIES.DOLLAR)&&(to.equals(VALID_CURRENCIES.EURO))){
			return 2;
		}
		if(from.equals(VALID_CURRENCIES.EURO)&&(to.equals(VALID_CURRENCIES.DOLLAR))){
			return 1/2;
		}
		
		return 1;
	}

}
