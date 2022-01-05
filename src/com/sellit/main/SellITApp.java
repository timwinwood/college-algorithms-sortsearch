package com.sellit.main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

/**
 * SellITApp - a class to analyse and manage SellIT product information
 *
 * @author Tim Winwood - x20213638
 * @version 1.0
 */
public class SellITApp {

	// initialise constants
	private static final String STR_PRODUCTS_CSV_PATH = "./resources/product_data.csv";

	/**
	 * main method for the SellITApp class
	 * 
	 * @param args Unused
	 */
	public static void main(String[] args) {

		// read products csv file to 'String[][] data'
		String[][] data = readCSV(STR_PRODUCTS_CSV_PATH, 1);

		// create 'Product[] products' from 'String[][] data'
		Product[] products = new Product[data.length];
		for (int i = 0; i < data.length; i++) {
			products[i] = new Product(Integer.parseInt(data[i][0]), data[i][1], data[i][2], (int)(Float.parseFloat(data[i][3])*100), data[i][4]);
		}

		// -- Part 1.Q1 - Bubble Sort --
		Product[] productsToBubbleSort = Arrays.copyOf(products, products.length);

		// uncomment to print array before bubble sort
		// System.out.println("-- Array Before Bubble Sort:");
		// printArray(productsToBubbleSort);
		// System.out.println("--");

		bubbleSort(productsToBubbleSort);

		// uncomment to print array after bubble sort
		// System.out.println("-- Array After Bubble Sort:");
		// printArray(productsToBubbleSort);
		// System.out.println("--");

		// -- Part 1.Q2 - Quick Sort --
		Product[] productsToQuickSort = Arrays.copyOf(products, products.length);

		// uncomment to print array before quick sort
		// System.out.println("-- Array Before Quick Sort:");
		// printArray(productsToQuickSort);
		// System.out.println("--");

		quickSort(productsToQuickSort, 0, productsToQuickSort.length - 1);

		// uncomment to print array after quick sort
		// System.out.println("-- Array After Quick Sort:");
		// printArray(productsToQuickSort);
		// System.out.println("--");

		// -- Part 1.Q3 - Algorithm Analysis --

		// initialise number of records and repeats
		int[] numRecords = { 10, 100, 1000, 10000 };
		int numRepeats = 5;

		// declare products
		Product[] randomProducts;
		Product[] productsToTimeBubbleSort;
		Product[] productsToTimeQuickSort;

		// timing variables
		long startTime;
		long endTime;

		// bubble sort timing variables
		long elapsedTimeBubbleSort;
		long sumTimeBubbleSort;
		long[] avgTimesBubbleSort = new long[numRecords.length];

		// quick sort timing variables
		long elapsedTimeQuickSort;
		long sumTimeQuickSort;
		long[] avgTimesQuickSort = new long[numRecords.length];

		// loop through numRecords array
		for (int i = 0; i < numRecords.length; i++) {

			// reset sums
			sumTimeBubbleSort = 0;
			sumTimeQuickSort = 0;

			// get x number of random products
			randomProducts = (Product[]) getXRandomProducts(products, numRecords[i]);

			// loop for numRepeats
			for (int j = 0; j < numRepeats; j++) {

				// assign randomProducts to bubble and quick sort products
				productsToTimeBubbleSort = randomProducts;
				productsToTimeQuickSort = randomProducts;

				// time bubble sort
				startTime = System.nanoTime();
				bubbleSort(productsToTimeBubbleSort);
				endTime = System.nanoTime();
				elapsedTimeBubbleSort = endTime - startTime;
				sumTimeBubbleSort += elapsedTimeBubbleSort;

				// time quick sort
				startTime = System.nanoTime();
				quickSort(productsToTimeQuickSort, 0, productsToTimeQuickSort.length - 1);
				endTime = System.nanoTime();
				elapsedTimeQuickSort = endTime - startTime;
				sumTimeQuickSort += elapsedTimeQuickSort;
			}

			// calculate average time
			avgTimesBubbleSort[i] = sumTimeBubbleSort / numRepeats;
			avgTimesQuickSort[i] = sumTimeQuickSort / numRepeats;

		}

		// initialise two-dimensional array to store timing data
		String[][] tableData = new String[3][numRecords.length + 1];
		tableData[0][0] = "Input Size";
		tableData[1][0] = "Bubble Sort";
		tableData[2][0] = "Quick Sort";

		// fill two-dimensional array with timing data
		for (int j = 0; j < numRecords.length; j++) {
			tableData[0][j + 1] = String.valueOf(numRecords[j]);
			tableData[1][j + 1] = String.valueOf(avgTimesBubbleSort[j]) + " ns";
			tableData[2][j + 1] = String.valueOf(avgTimesQuickSort[j]) + " ns";
		}

		// print timing data to screen
		System.out.println("-- Alogrithms Analysis:");
		printArrayAsTable(tableData);
		System.out.println("--");

		// -- Part 1.Q4 - Binary Search by ProductID --

		// initialise ProductIDComparator - a comparator used to compare a product with a productID
		ProductIDComparator productIDComparator = new ProductIDComparator();

		// initialise productsToBinarySearch and productIDSearch
		Product[] productsToBinarySearch = Arrays.copyOf(products, products.length);
		Integer productIDSearch = new Integer(100);

		// uncomment to print array before binary search
		// System.out.println("-- Array before binary search:");
		// printArray(productsToBinarySearch);
		// System.out.println("--");

		// perform the binary search
		int result = binarySearch(productsToBinarySearch, 0, productsToBinarySearch.length - 1, productIDSearch, productIDComparator);

		// process the binary search result
		if (result == -1) {
			System.out.println("A Product with ProductID=" + productIDSearch + " was NOT found.");
		} else {
			System.out.println("A Product with ProductID=" + productIDSearch + " was found.");
			System.out.println("-- Product Details:");
			System.out.println(productsToBinarySearch[result].toString());
			System.out.println("--");
		}
		
		
		// -- Part 2.Q1 - Check and Set productType --
		
		// initialise products and product type strings
		Product productToCheckAndSetType = products[0];
		String validProductType = "Software";
		String invalidProductType = "Vegetable";

		
		//uncomment to print productType before 'valid' check and set
		//System.out.println("-- productType before 'valid' check and set:");
		//System.out.println(productToCheckAndSet.getProductType());
		//System.out.println("--");
		
		System.out.println("-- Attempting to set productType to: " + validProductType);
		productToCheckAndSetType.setProductType(validProductType);
		
		//uncomment to print productType after 'valid' check and set
		//System.out.println("-- productType after 'valid' check and set:");
		//System.out.println(productToCheckAndSet.getProductType());
		//System.out.println("--");

		//uncomment to print productType before 'invalid' check and set
		//System.out.println("-- productType before 'invalid' check and set:");
		//System.out.println(productToCheckAndSet.getProductType());
		//System.out.println("--");
		
		System.out.println("-- Attempting to set productType to: " + invalidProductType);
		productToCheckAndSetType.setProductType(invalidProductType);
		
		//uncomment to print productType after 'invalid' check and set
		//System.out.println("-- productType after 'invalid' check and set:");
		//System.out.println(productToCheckAndSet.getProductType());
		//System.out.println("--");

		
		// -- Part 2.Q2 - Check productPrice --
		
		// initialise products and product type strings
		Product productToCheckAndSetPrice = products[2];
		int validProductPrice = 500000;
		int invalidLowProductPrice = 25;
		int invalidHighProductPrice = 1275000;

		// -- 'valid' price --
		//uncomment to print productPrice before 'valid' check and set
		System.out.println("-- productPrice before 'valid' check and set:");
		System.out.println(productToCheckAndSetPrice.getProductPriceAsString());
		System.out.println("--");
		
		System.out.println("-- Attempting to set productPrice to: " + CurrencyUtils.centsToDollarsCentsString(validProductPrice));
		productToCheckAndSetPrice.setProductPrice(validProductPrice);
		
		//uncomment to print productPrice after 'valid' check and set
		System.out.println("-- productPrice after 'valid' check and set:");
		System.out.println(productToCheckAndSetPrice.getProductPriceAsString());
		System.out.println("--");
		
		// -- 'invalid low' price --
		//uncomment to print productPrice before 'invalid low' check and set
		System.out.println("-- productPrice before 'invalid low' check and set:");
		System.out.println(productToCheckAndSetPrice.getProductPriceAsString());
		System.out.println("--");
		
		System.out.println("-- Attempting to set productPrice to: " + CurrencyUtils.centsToDollarsCentsString(invalidLowProductPrice));
		productToCheckAndSetPrice.setProductPrice(invalidLowProductPrice);
		
		//uncomment to print productPrice after 'invalid low' check and set
		System.out.println("-- productPrice after 'invalid low' check and set:");
		System.out.println(productToCheckAndSetPrice.getProductPriceAsString());
		System.out.println("--");
		
		// -- 'invalid high' price --
		//uncomment to print productPrice before 'invalid high' check and set
		System.out.println("-- productPrice before 'invalid high' check and set:");
		System.out.println(productToCheckAndSetPrice.getProductPriceAsString());
		System.out.println("--");
		
		System.out.println("-- Attempting to set productPrice to: " + CurrencyUtils.centsToDollarsCentsString(invalidHighProductPrice) );
		productToCheckAndSetPrice.setProductPrice(invalidHighProductPrice);
		
		//uncomment to print productPrice after 'invalid high' check and set
		System.out.println("-- productPrice after 'invalid high' check and set:");
		System.out.println(productToCheckAndSetPrice.getProductPriceAsString());
		System.out.println("--");
		

	}

	/**
	 * -- Part 1.Q1 - Bubble Sort --
	 * bubbleSort() sorts an array using bubble sort algorithm
	 *
	 * @param arr array to be sorted
	 */
	private static void bubbleSort(Comparable[] arr) {

		// get the array length
		int len = arr.length;

		// outer for loop - through the full array
		for (int i = 0; i < len; i++) {

			// inner for loop - loop through the array from the first element until (len-i)th element
			// on each iteration of the outer for loop one less element is traversed
			for (int j = 1; j < (len - i); j++) {

				// check if element on the left is more than element on the right
				if (arr[j - 1].compareTo(arr[j]) > 0) {

					// if so, swap the elements
					swap(arr, j - 1, j);

				}
			}
		}
	}

	/**
	 * -- Part 1.Q2 - Quick Sort --
	 * quickSort() recursive method sorts an array using quick sort algorithm
	 * - uses the last element in the array as the 'pivot'
	 * - moves the 'pivot' to the correct position by:
	 * -- moving smaller elements to the left of the 'pivot'
	 * -- moving larger elements to the right of the 'pivot'
	 * - sorts elements to the left of the 'pivot'
	 * - sorts elements to the right of the 'pivot'
	 * 
	 * @param arr array to be sorted
	 * @param int index to start from
	 * @param int index to stop at
	 */
	private static void quickSort(Comparable[] arr, int start, int end) {

		// base case - start is more than or equal to end (there are zero elements remaining to sort)
		if (start >= end) {
			return;
		}

		// recursive case - start is less than end (there are elements remaining to sort)

		// move the 'pivot' to the correct position
		int pi = partition(arr, start, end);

		// sort elements to the left of (lower than) the 'pivot'
		quickSort(arr, start, pi - 1);
		// sort elements to the right of (higher than) the 'pivot'
		quickSort(arr, pi + 1, end);

	}

	/**
	 * -- Part 1.Q2 - Quick Sort --
	 * partition() moves the 'pivot' to the correct position
	 * - uses the last element in the array as the 'pivot'
	 * - moves the 'pivot' to the correct position
	 * - returns the pivot index
	 *
	 * @param arr array to be sorted
	 * @param int index to start from
	 * @param int index to stop at
	 */
	private static int partition(Comparable[] arr, int start, int end) {

		// Use last element in the array as the 'pivot'
		Comparable pivot = arr[end];
		Comparable temp = null;

		// smaller element index
		int si = start;

		for (int j = start; j <= end - 1; j++) {

			// check if current is less than pivot
			if (arr[j].compareTo(pivot) < 0) {

				// swap current element and smaller element
				swap(arr, si, j);

				// increase smaller element index by 1
				si++;
			}
		}

		// finally, swap the 'pivot' after the last 'smaller' element
		swap(arr, si, end);
		return (si);
	}

	/**
	 * -- Part 1.Q4 - Binary Search
	 *
	 *
	 * @param arr array to perform search on
	 * @param start index to start from
	 * @param end index to stop at
	 * @param searchKey object to search for
	 * @param comp comparator used to compare the searchKey with the elements in the array
	 */
	private static int binarySearch(Object arr[], int start, int end, Object searchKey, Comparator comp) {

		// base case - start is more than or equal to end (there are zero elements remaining to sort)
		if (start >= end) {
			return -1;
		}

		// recursive case - start is less than end (there are elements remaining to sort)
		
		// index of middle element
		int mid = start + (end - start) / 2;

		// check if middle element is equal to searchKey
		// if so, return index of middle element
		if (comp.compare(arr[mid], searchKey) == 0) {
			return mid;
		}

		// check if middle element is more than the searchKey
		// if so, search elements to the left of (lower than) the middle element
		if (comp.compare(arr[mid], searchKey) > 0) {
			return binarySearch(arr, start, mid - 1, searchKey, comp);
		}

		// if the above conditions have not been met, the middle element must be less than the searchKey
		// if so, search elements to the right of (higher than) the middle element
		return binarySearch(arr, mid + 1, end, searchKey, comp);

	}

	/**
	 * swaps two elements in an array
	 *
	 * @param arr array in which to swap the elements
	 * @param int index of first element to swap
	 * @param int index of second element to swap
	 */
	private static void swap(Object[] arr, int x, int y) {
		Object temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}

	/**
	 * prints an array to the screen
	 *
	 * @param arr array to be printed
	 */
	private static void printArray(Object[] arr) {

		// loop array and print to screen
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i].toString());
		}

	}

	/**
	 * prints an array to the screen in a table format
	 *
	 * @param arr array to be printed
	 */
	private static void printArrayAsTable(Object[][] arr) {

		String border = "";

		// loop through the array
		for (int i = 0; i < arr.length; i++) {

			// generate and print the border
			border = "";
			for (int j = 0; j < arr[i].length; j++) {
				border += "+-----------------";
			}
			border += "+\n";
			System.out.print(border);

			// print the data in the cells
			for (int k = 0; k < arr[i].length; k++) {
				System.out.printf("| %-15s ", arr[i][k].toString());
			}
			System.out.print("|\n");

		}

		// print the border for the bottom row
		System.out.print(border);

	}

	/**
	 * gets x random products from an array of products
	 *
	 * @param products array to get the random products from
	 * @param x the number of random products to get
	 */
	private static Product[] getXRandomProducts(Product[] products, int x) {

		// initialise the xProducts array to store the products
		Product[] xProducts = new Product[x];

		// generate x random ints within the bounds of the array
		Random random = new Random();
		int[] randomInts = random.ints(x, 0, products.length).toArray();

		// loop through the random ints
		for (int i = 0; i < x; i++) {

			// assign xProducts[i] to the product at the index of the random int
			xProducts[i] = products[randomInts[i]];

		}

		// return xProducts
		return xProducts;

	}

	/**
	 * reads a csv file to String[][]
	 * 
	 * @param csvPath path to the csv file
	 * @param headers number of header rows to skip. Set to zero include headers.
	 * 
	 * @return String[][] the data
	 */
	private static String[][] readCSV(String csvPath, int headers) {

		// Check that headers is a positive number
		if (headers < 0) {
			throw new IllegalArgumentException("headers must be a positive number");
		}

		String[][] data = null;
		int i = 0;
		String str = "";

		try {

			// initialise the variables
			Path path = Paths.get(csvPath);
			File file = new File(csvPath);
			int intLines = (int) Files.lines(path).count() - headers;
			Scanner scanner = new Scanner(file);

			// initialise data[][] with number of lines as size
			data = new String[intLines][];

			// skip the headers
			for (int j = 0; j < headers; j++) {
				if (scanner.hasNextLine())
					scanner.nextLine();
			}

			// read the lines and convert to csv
			while (scanner.hasNextLine()) {

				str = scanner.nextLine();
				str = str.replace("\"", "");
				data[i] = str.split(",");
				i++;

			}

			// close the scanner
			scanner.close();

		} catch (IOException e) {

			// print stack trace if an IOException is thrown
			e.printStackTrace();

		}

		// return data[][]
		return data;

	}
	


}


