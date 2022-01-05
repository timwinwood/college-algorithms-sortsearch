package com.sellit.main;


/**
 *  ProductTypeException - a custom exception for incorrect Product Price
 *
 * @author Tim Winwood - x20213638
 * @version 1.0
 */
public class ProductPriceException extends Exception {
	
	// constructor
    public ProductPriceException(int min, int max){
    	
    	// call the constructor of the super type
        super("Product Price must be between " + CurrencyUtils.centsToDollarsCentsString(min) + " and " + CurrencyUtils.centsToDollarsCentsString(max));
        
    }
}
