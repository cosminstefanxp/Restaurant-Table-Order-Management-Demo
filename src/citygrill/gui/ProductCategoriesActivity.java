/*
 * IOC - Tema2
 * 
 * Stefan-Dobrin Cosmin
 * 342C4
 */
package citygrill.gui;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import citygrill.data.DataProvider;
import citygrill.data.Order;
import citygrill.data.ProductCategory;
import citygrill.data.TableOrder;
import citygrill.restaurant.Table;

import com.order.R;

/**
 * The Class MainActivity.
 */
public class ProductCategoriesActivity extends Activity implements OnItemClickListener {
		
	/** The table. */
	Table table;
	
	/** The order. */
	Order order;
	
	/** The categories. */
	ArrayList<ProductCategory> categories;
	
	/** The listview. */
	GridView gridview;
	
    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState the saved instance state
     */
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.categories);
	
	    //Get the categories
	    categories=DataProvider.getProductCategories();
	    
	    //Get the table
	    Bundle b = getIntent().getExtras();
	    table = DataProvider.getTable(b.getInt("tableID"));
	    
	    //Set order
	    order=DataProvider.getOrder(table.id, b.getInt("orderID"));
	    Log.d("CG","Product Category activity for "+order);
	    
	    //Prepare the grid
	    gridview = (GridView) findViewById(R.id.categoriesGridView);
	    gridview.setAdapter(new CategoriesAdapter(getBaseContext(), categories));

	    gridview.setOnItemClickListener(this);

	}

	/* Event triggered on click on one of the orders
	 * 
	 * (non-Javadoc)
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

	}

}