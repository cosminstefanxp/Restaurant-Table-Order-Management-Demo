/*
 * IOC - Tema2
 * 
 * Stefan-Dobrin Cosmin
 * 342C4
 */
package citygrill.gui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;
import citygrill.data.DataProvider;
import citygrill.data.Order;
import citygrill.restaurant.Table;

import com.order.R;

/**
 * The Class MainActivity.
 */
public class OrderActivity extends Activity implements OnClickListener {
	
	/** The table. */
	Table table;
	
	/** The order. */
	Order order;
	
	/** The listview. */
	ListView listview;
	
    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState the saved instance state
     */
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.order);
	
	    //Get the table
	    Bundle b = getIntent().getExtras();
	    table = DataProvider.getTable(b.getInt("tableID"));
	    
	    //Set order
	    order=DataProvider.getOrder(table.id, b.getInt("orderID"));
	    Log.d("CG","Order activity for "+order);
	    
	    //Set graphical data
	    TextView text=(TextView) findViewById(R.id.orderTitle);
	    text.setText("Order "+order.id);
	    
	    text=(TextView) findViewById(R.id.orderCostText);
	    text.setText("Cost: "+order.totalPrice);
	    
	    text=(TextView) findViewById(R.id.orderDurationText);
	    text.setText("Duration: "+order.duration+" min");
	    
	}

	/* Event triggered at click on "New Order button"
	 * 
	 * (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View arg0) {
		table.tableOrder.orders.add(new Order());
		this.listview.invalidateViews();
	}

}