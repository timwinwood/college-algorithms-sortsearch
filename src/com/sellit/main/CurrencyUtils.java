package com.sellit.main;

public class CurrencyUtils {
	
	
	  /**
     * converts centsIn to dollars and cents format
     *
     * @param centsIn the amount in cents
     * @return
     */
    public static String centsToDollarsCentsString(int centsIn) {
    	
        int centsTemp = centsIn % 100;
        int dollarsTemp = (centsIn - centsTemp) / 100;

        String centsFormatted;
        if (centsTemp > 9) {
        	centsFormatted = "" + centsTemp;
        } else {
        	centsFormatted = 0 + "" + centsTemp;
        	
        }
        
        return "$" + dollarsTemp + "." + centsFormatted;

    }

}
