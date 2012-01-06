/*
 * IOC - Tema2
 * 
 * Stefan-Dobrin Cosmin
 * 342C4
 */
package citygrill.data;

import java.util.ArrayList;

/**
 * The Class TableOrder.
 */
public class TableOrder {
	
	/**
	 * Instantiates a new table order.
	 */
	public TableOrder() {
		super();
		orders=new ArrayList<Order>();
	}

	/** The orders. */
	public ArrayList<Order> orders;
	
	/** The duration, in minutes. */
	public int duration;
}
