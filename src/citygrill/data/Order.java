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
	
	/** The global id. */
	private static int globalID=0;

	/** The id. */
	public int id;

	/** The products, as a map of product and quantities. */
	public HashMap<Product, Integer> products;
	
	/** The total price. */
	public float totalPrice;
	
	/** The duration, in minutes. */
	public int duration;
	
	/**
	 * Instantiates a new order.
	 */
	public Order() {
		id=globalID++;
		products=new HashMap<Product, Integer>();
		totalPrice=0;
		duration=10;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Order [id=" + id + ", products=" + products + ", totalPrice=" + totalPrice + "]";
	}
}
