/*
 * IOC - Tema2
 * 
 * Stefan-Dobrin Cosmin
 * 342C4
 */
package citygrill.data;

import java.util.HashMap;

/**
 * The Class Order.
 */
public class Order {

	/** The products, as a map of product and quantities. */
	HashMap<Product, Integer> products;
	
	/** The total price. */
	float totalPrice;
}
