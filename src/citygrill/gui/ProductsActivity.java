/*
 * IOC - Tema2
 * 
 * Stefan-Dobrin Cosmin
 * 342C4
 */
package citygrill.gui;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import citygrill.data.DataProvider;
import citygrill.data.Order;
import citygrill.data.OrderProduct;
import citygrill.data.Product;
import citygrill.data.Product.Type;
import citygrill.gui.adapters.ProductsAdapter;
import citygrill.restaurant.Table;

import com.order.R;

/**
 * The Class MainActivity.
 */
public class ProductsActivity extends Activity implements OnItemClickListener {
		
	/** The table. */
	Table table;
	
	/** The order. */
	Order order;
	
	/** The products. */
	ArrayList<Product> products;
	
	/** The category. */
	Type category;
	
	/** The listview. */
	GridView gridview;
	
    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState the saved instance state
     */
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.products);
	
	    //Get the table
	    Bundle b = getIntent().getExtras();
	    table = DataProvider.getTable(b.getInt("tableID"));
	    
	    //Set order
	    order=DataProvider.getOrder(table.id, b.getInt("orderID"));
	    Log.d("CG","Product Category activity for "+order);
	    
	    //Get the products
	    category=(Type) b.get("category");
	    products=DataProvider.getProductsFromCategory(category);
	    
	    //Prepare the grid
	    gridview = (GridView) findViewById(R.id.productsGridView);
	    gridview.setAdapter(new ProductsAdapter(getBaseContext(), products));

	    gridview.setOnItemClickListener(this);

	}

	/* Event triggered on click on one of the products
	 * 
	 * (non-Javadoc)
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		//Add the new product to the order
		Product selectedProduct=products.get(position);
		
		//Check if there is already such a product in the order
		boolean done=false;
		for(OrderProduct op: order.products)
			if(op.product.equals(selectedProduct))
			{
				op.quantity++;
				done=true;
				Toast.makeText(getBaseContext(),"Product "+selectedProduct.name+" already ordered.", Toast.LENGTH_SHORT).show();
				break;
			}
		
		//Add a new order product
		if(!done)
		{
			OrderProduct op=new OrderProduct(selectedProduct);
			order.products.add(op);
		}
		//Update cost and duration
		order.totalPrice+=selectedProduct.price;
		if(selectedProduct.duration>order.duration)
			order.duration=selectedProduct.duration;
		
		//Return success, so the parent knows it was a successful selection
		setResult(RESULT_OK);
		finish();
	}

}