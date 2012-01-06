/*
 * IOC - Tema2
 * 
 * Stefan-Dobrin Cosmin
 * 342C4
 */
package citygrill.data;

import java.util.ArrayList;

import citygrill.restaurant.Table;

/**
 * The Class DataProvider.
 */
public class DataProvider {

	/**
	 * Gets the tables in the restaurant.
	 *
	 * @return the tables
	 */
	public static ArrayList<Table> getTables()
	{
		ArrayList<Table> tables=new ArrayList<Table>();
		tables.add(new Table(0,3));
		tables.add(new Table(1,4));
		tables.add(new Table(2,4));
		tables.add(new Table(3,2));
		tables.add(new Table(4,6));
		tables.add(new Table(5,8));
		tables.add(new Table(6,4));
		tables.add(new Table(7,2));
		
		
		return tables;
	}
}
