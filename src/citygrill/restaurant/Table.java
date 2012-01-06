/*
 * IOC - Tema2
 * 
 * Stefan-Dobrin Cosmin
 * 342C4
 */
package citygrill.restaurant;

import java.io.Serializable;

/**
 * The Class Table.
 */
public class Table implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6785961774305587584L;

	/**
	 * Instantiates a new table.
	 *
	 * @param id the id
	 * @param maxClients the max clients
	 */
	public Table(int id, int maxClients) {
		super();
		this.id = id;
		this.maxClients = maxClients;
		this.curClients=0;
		this.empty=true;
	}

	/** The id. */
	public int id;
	
	/** The empty. */
	public boolean empty;
	
	/** The max clients. */
	public int maxClients;
	
	/** The cur clients. */
	public int curClients;

	@Override
	public String toString() {
		return "Table [id=" + id + ", empty=" + empty + ", curClients=" + curClients + ", maxClients="
				+ maxClients + "]";
	}
}
