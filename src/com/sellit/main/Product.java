/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sellit.main;

import java.util.Comparator;

/**
 * @author Tim Winwood - x20213638
 * @author yalemisew
 */
class Product implements Comparable<Object> {

	private int productID;
	private String productName;
	private String productDescription;
	private int productPrice;
	private String productType;
	
	private static final int MIN_PRODUCT_PRICE = 100;
	private static final int MAX_PRODUCT_PRICE = 999900;

	// ProductType enum
	// Used to specify a set list of product types
	public enum ProductType {
		HARDWARE("Hardware"), SOFTWARE("Software"), SERVICE("Service");

		private String productType;

		ProductType(String productType) {
			this.productType = productType;
		}

		@Override
		public String toString() {
			return productType;
		}

	}

	// constructor
	public Product(int productID, String productName, String productDescription, int productPrice, String productType) {
		setProductID(productID);
		setProductName(productName);
		setProductDescription(productDescription);
		setProductPrice(productPrice);
		setProductType(productType);

	}

	public int getProductID() {
		return productID;
	}

	private void setProductID(int productID) {
		this.productID = productID;

	}

	public String getProductName() {
		return productName;
	}

	private void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	private void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	
	/**
	 * -- Part 2.Q2 - Check productPrice --
	 * setProductPrice() checks and sets the productPrice
	 *
	 * @param productPrice the product price int
	 */
	public void setProductPrice(int productPrice) {

		try {
			
			// check product price, throws ProductPriceException if invalid
			checkProductPrice(productPrice);

			// set productPrice
			this.productPrice = productPrice;
			
		// catch the ProductPriceException	
		} catch (ProductPriceException e) {
			
			this.productPrice = productPrice;
			
			// print the stack trace (list of executing methods)
			e.printStackTrace();
			
		}

	}
	
	/**
	 * -- Part 2.Q2 - Check productPrice --
	 * checkProductPrice() checks if the productPrice is between MIN_PRODUCT_PRICE and MAX_PRODUCT_PRICE
	 *
	 * @param productPrice the product price int
	 * @throws ProductPriceException
	 */
	private static boolean checkProductPrice(int productPrice) throws ProductPriceException {

		// check if productPrice is inside bounds
		if(productPrice >= MIN_PRODUCT_PRICE && productPrice <= MAX_PRODUCT_PRICE) {
			return true;
		}
		
		// if the above did not return true, the productPrice is outside bounds
		// throw a new ProductPriceException
		throw new ProductPriceException(MIN_PRODUCT_PRICE,MAX_PRODUCT_PRICE);

	}
	

	public int getProductPrice() {
		return productPrice;
	}
	
	public String getProductPriceAsString() {
		return CurrencyUtils.centsToDollarsCentsString(productPrice);
	}

	

	public void setProductType(ProductType productType) {
		this.productType = productType.toString();
	}

	
	/**
	 * -- Part 2.Q1 - Check and Set productType --
	 * setProductType() checks and sets the productType
	 *
	 * @param productType the product type string
	 */
	public void setProductType(String productType) {

		try {
			
			// check product type, throws ProductTypeException if invalid
			checkProductType(productType);

			// set productType
			this.productType = productType;
			
		// catch the ProductTypeException	
		} catch (ProductTypeException e) {

			
			// print the stack trace (list of executing methods)
			e.printStackTrace();
			
		}

	}

	/**
	 * -- Part 2.Q1 - Check and Set productType --
	 * checkProductType() checks if the productType is in the ProductType enum
	 *
	 * @param productType the product type string
	 * @throws ProductTypeException
	 */
	private static boolean checkProductType(String productType) throws ProductTypeException {
		
		// Get the ProductTypes in the enum
		ProductType[] pts = ProductType.values();

		// loop through the ProductTypes in the enum
		for (int i = 0; i < pts.length; i++) {
			
			// check if the current ProductType from the enum matches the productType string
			if (pts[i].toString().equals(productType)) {
				
				// if so, return true
				return true;
			}
		}
		
		// if the above did not return true, the enum does not contain the productType string
		// throw a new ProductTypeException
		throw new ProductTypeException();

	}

	public String getProductType() {
		return productType;
	}


	// so the product objects can be compared when sorting/searching
	// NOTE: this will only allow comparisons based on the productName
	@Override
	public int compareTo(Object obj) {
		Product prod = (Product) obj;
		return productName.compareTo(prod.getProductName());
	}

	public int compare(Object o1, Object o2) {
		return productID;

	}

	// return a String containing the product details
	@Override
	public String toString() {
		return productID + " " + productName + " " + productDescription + " " + productPrice + " " + productType;
	}

}
