package com.sellit.main;


/**
 *  ProductTypeException - a custom exception for incorrect Product Type
 *
 * @author Tim Winwood - x20213638
 * @version 1.0
 */
public class ProductTypeException extends Exception  {

	// constructor
    public ProductTypeException(){
    	
    	// call the constructor of the super type
        super("Product Type can only be \"Hardware\", \"Software\" or \"Service\". Please correct the productType.");
    }
    
	
}
