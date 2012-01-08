/*
 * IOC - Tema2
 * 
 * Stefan-Dobrin Cosmin
 * 342C4
 */
package citygrill.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import citygrill.data.DataProvider;
import citygrill.data.Order;
import citygrill.gui.adapters.OrderAdapter;
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
	    
	    
	    //Set adapters and handlers
	    
	    listview=(ListView) findViewById(R.id.orderListView);
	    listview.setAdapter(new OrderAdapter(this, order));	 
	    
	    ImageButton button=(ImageButton) findViewById(R.id.orderProductAdd);
	    button.setOnClickListener(this);
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onStart()
	 */
	protected void onStart()
	{
		super.onStart();
		
		//Set graphical data
	    TextView text=(TextView) findViewById(R.id.orderTitle);
	    text.setText("Order "+order.id);
	    
	    text=(TextView) findViewById(R.id.orderCostText);
	    text.setText("Cost: "+order.totalPrice);
	    
	    text=(TextView) findViewById(R.id.orderDurationText);
	    text.setText("Duration: "+order.duration+" min");
	    
	    listview.invalidateViews();
		
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onContentChanged()
	 */
	public void onContentChanged()
	{
		super.onContentChanged();
		//onStart();
	}

	/* Event triggered at click on "New Product button"
	 * 
	 * (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View arg0) {
		Intent myIntent = new Intent(this, ProductCategoriesActivity.class);
		myIntent.putExtra("tableID", table.id);
		myIntent.putExtra("orderID", order.id);
		this.startActivityForResult(myIntent,PICK_PRODUCT);
	}
	
	private final static int PICK_PRODUCT=2;
	/* (non-Javadoc)
	 * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.d("CG","Returned from Category selection with "+resultCode);
		if (requestCode == PICK_PRODUCT) 
		{
			Log.d("CG","Request code is for PICK_PRODUCT");
			if (resultCode == RESULT_OK) 
			{
				Toast.makeText(getBaseContext(), "Produs selectat cu succes!", Toast.LENGTH_SHORT).show();
			}
		}
	}

}