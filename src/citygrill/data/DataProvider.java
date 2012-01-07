/*
 * IOC - Tema2
 * 
 * Stefan-Dobrin Cosmin
 * 342C4
 */
package citygrill.data;

import java.util.ArrayList;

import com.order.R;

import citygrill.data.Product.Type;
import citygrill.restaurant.Table;

/**
 * The Class DataProvider.
 */
public class DataProvider {

	public static ArrayList<Table> tables;
	public static ArrayList<Product> products;
	public static ArrayList<ProductCategory> categories;
	
	/**
	 * Gets the tables in the restaurant.
	 *
	 * @return the tables
	 */
	public static void generateTables()
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
		
	}
	
	/**
	 * Gets the tables.
	 *
	 * @return the tables
	 */
	public static ArrayList<Table> getTables()
	{
		return tables;
	}
	
	/**
	 * Generate products.
	 */
	public static void generateProducts()
	{
		products=new ArrayList<Product>();
		products.add(new Product("Ciorba vacuta", 8, R.drawable.soup, Type.Soup));
		products.add(new Product("Ciorba pui", 8, R.drawable.soup, Type.Soup));
		products.add(new Product("Ciorba burta", 8.5f, R.drawable.soup, Type.Soup));
		products.add(new Product("Supa de taitei", 7, R.drawable.soup, Type.Soup));
		
		products.add(new Product("Clatite", 6, R.drawable.dessert, Type.Dessert));
		products.add(new Product("Papanasi", 12, R.drawable.dessert, Type.Dessert));
		products.add(new Product("Placinta de mere", 8, R.drawable.dessert, Type.Dessert));
		products.add(new Product("Placinta cu branza", 8, R.drawable.dessert, Type.Dessert));
		products.add(new Product("Negresa", 10.5f, R.drawable.dessert, Type.Dessert));
		
		products.add(new Product("Snitele pui", 14, R.drawable.main_dish, Type.MainDish));
		products.add(new Product("Snitele porc", 16, R.drawable.main_dish, Type.MainDish));
		products.add(new Product("Copanele la gratar", 12, R.drawable.main_dish, Type.MainDish));
		products.add(new Product("Tochitura dobrogeana", 13.5f, R.drawable.main_dish, Type.MainDish));
		products.add(new Product("Musaca de cartofi", 14, R.drawable.main_dish, Type.MainDish));
		products.add(new Product("Lasagna", 16, R.drawable.main_dish, Type.MainDish));
		products.add(new Product("Spaghete bolognese", 14, R.drawable.main_dish, Type.MainDish));
		products.add(new Product("Spaghete carbonara", 14.5f, R.drawable.main_dish, Type.MainDish));
		products.add(new Product("Spaghete milaneze", 13, R.drawable.main_dish, Type.MainDish));
		products.add(new Product("Platou pentru 2", 23, R.drawable.main_dish, Type.MainDish));
		products.add(new Product("Pomana porcului", 25, R.drawable.main_dish, Type.MainDish));
		
		products.add(new Product("Peroni", 7, R.drawable.alcohol, Type.Alcoholic));
		products.add(new Product("Bergenbier", 4.5f, R.drawable.alcohol, Type.Alcoholic));
		products.add(new Product("Ursus", 5, R.drawable.alcohol, Type.Alcoholic));
		products.add(new Product("Stella", 5, R.drawable.alcohol, Type.Alcoholic));
		products.add(new Product("Pina Colada", 14, R.drawable.alcohol, Type.Alcoholic));
		
		products.add(new Product("Pepsi", 4, R.drawable.non_alcoholic, Type.NonAlcoholic));
		products.add(new Product("Coca-Cola", 4, R.drawable.non_alcoholic, Type.NonAlcoholic));
		products.add(new Product("Fanta", 4, R.drawable.non_alcoholic, Type.NonAlcoholic));
		products.add(new Product("Prigat", 5, R.drawable.non_alcoholic, Type.NonAlcoholic));
		products.add(new Product("Virgin Mary", 8, R.drawable.non_alcoholic, Type.NonAlcoholic));
		
		products.add(new Product("Alune", 4, R.drawable.other, Type.Other));
		products.add(new Product("Nachos", 6.5f, R.drawable.other, Type.Other));
	}

	/**
	 * Gets the products.
	 *
	 * @return the products
	 */
	public static ArrayList<Product> getProducts()
	{
		return products;
	}
	
	/**
	 * Generates the product categories.
	 */
	public static void generateProductCategories()
	{
		categories=new ArrayList<ProductCategory>();
		categories.add(new ProductCategory("Bauturi Alcoolice", Type.Alcoholic, R.drawable.alcohol));
		categories.add(new ProductCategory("Bauturi Nealcoolice", Type.NonAlcoholic, R.drawable.non_alcoholic));
		categories.add(new ProductCategory("Mancaruri gatite", Type.MainDish, R.drawable.main_dish));
		categories.add(new ProductCategory("Supe/Ciorbe",Type.Soup, R.drawable.soup));
		categories.add(new ProductCategory("Deserturi", Type.Dessert, R.drawable.dessert));
		categories.add(new ProductCategory("Diverse", Type.Other, R.drawable.other));		
	}
	
	/**
	 * Gets the product categories.
	 *
	 * @return the product categories
	 */
	public static ArrayList<ProductCategory> getProductCategories()
	{
		return categories;
	}
	
	/**
	 * Gets the products from a given category (of a given type).
	 *
	 * @param category the category
	 * @return the products from category
	 */
	public ArrayList<Product> getProductsFromCategory(Type category)
	{
		ArrayList<Product> retProds=new ArrayList<Product>();
		for(Product p:products)
			if(p.type==category)
				retProds.add(p);
		return retProds;
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
	
	/**
	 * Gets an order product from a given order.
	 *
	 * @param order the order
	 * @param product the product
	 * @return the table
	 */
	public static OrderProduct getOrder(Order order, Product product)
	{
		for(OrderProduct o: order.products)
			if(o.product==product)
				return o;
		return null;
	}
}
