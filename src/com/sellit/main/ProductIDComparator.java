package com.sellit.main;

import java.util.Comparator;

/**
 * ProductIDComparator - a Comparator class to compare a product with an productID
 *
 * @author Tim Winwood - x20213638
 * @version 1.0
 */
class ProductIDComparator implements Comparator<Object> {
	public int compare(Object o1, Object o2) {
		if (o1 == null)
			throw new NullPointerException();
		if (o2 == null)
			throw new NullPointerException();
		if (!(o1 instanceof Product))
			throw new IllegalArgumentException("o1 must be an instance of Product");
		if (!(o2 instanceof Integer))
			throw new IllegalArgumentException("02 must be an instance of Integer");

		return ((Integer) ((Product) o1).getProductID()).compareTo((Integer) o2);
	}
}
