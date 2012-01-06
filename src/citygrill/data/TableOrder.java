/*
 * IOC - Tema2
 * 
 * Stefan-Dobrin Cosmin
 * 342C4
 */
package citygrill.data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The Class TableOrder.
 */
public class TableOrder implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6577234748166232293L;

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
