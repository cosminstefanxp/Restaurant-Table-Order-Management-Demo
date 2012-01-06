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

	public static ArrayList<Table> tables;
	
	/**
	 * Gets the tables in the restaurant.
	 *
	 * @return the tables
	 */
	public static ArrayList<Table> getTables()
	{
		tables=new ArrayList<Table>();
		tables.add(new Table(0,3));
		tables.add(new Table(1,4));
		tables.add(new Table(2,4));
		tables.add(new Table(3,2));
		tables.add(new Table(4,6));
		tables.add(new Table(5,8));
		tables.add(new Table(6,4));
		tables.add(new Table(7,2));
		
		tables.get(4).empty=false; tables.get(4).curClients=5;
		tables.get(2).empty=false; tables.get(2).curClients=3;
		
		return tables;
	}
	
	/**
	 * Gets a table with the given id.
	 *
	 * @param id the id
	 * @return the table
	 */
	public static Table getTable(int id)
	{
		for(Table t: tables)
			if( t.id==id)
				return t;
		return null;
	}
	
	/**
	 * Gets an order with a given id.
	 *
	 * @param tableID the table id
	 * @param orderID the order id
	 * @return the table
	 */
	public static Order getOrder(int tableID, int orderID)
	{
		for(Order o: tables.get(tableID).tableOrder.orders)
			if(o.id==orderID)
				return o;
		return null;
	}
}
