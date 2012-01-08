/*
 * IOC - Tema2
 * 
 * Stefan-Dobrin Cosmin
 * 342C4
 */
package citygrill.data;

import java.util.Random;

/**
 * The Class Product.
 */
public class Product {
	
	/**
	 * The type of food.
	 */
	public enum Type {
		/** The Dessert. */
		Dessert,
		/** The Soup. */
		Soup,
		/** The Main dish. */
		MainDish,
		/** The Alcoholic drink. */
		Alcoholic,
		/** The Non alcoholic drink. */
		NonAlcoholic,
		/** The Other kind of products. */
		Other
		
	}
	
	/** The global id. */
	public static int globalID=0;
	
	/** The id. */
	public int id;
	
	/** The description. */
	public String name;
	
	/** The ingredients. */
	public String[] ingredients;
	
	/** The price. */
	public float price;
	
	/** The resource. */
	public int resource;
	
	/** The type. */
	public Type type;
	
	/** The duration. */
	public int duration;
	
	/** The random generator. */
	private static Random rand=new Random();
	
	/**
	 * Instantiates a new product.
	 *
	 * @param name the name
	 * @param price the price
	 * @param resource the resource
	 * @param type the type
	 */
	public Product(String name, float price, int resource, Type type) {
		super();
		this.name = name;
		this.price = price;
		this.resource = resource;
		this.id = ++globalID;
		this.type = type;
		
		this.duration=1+rand.nextInt()%15;	//generate a random duration, up to 15 minutes
	}
}
