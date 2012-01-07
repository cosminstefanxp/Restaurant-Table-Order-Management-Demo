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
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import citygrill.data.DataProvider;
import citygrill.data.Order;
import citygrill.data.TableOrder;
import citygrill.restaurant.Table;

import com.order.R;

/**
 * The Class MainActivity.
 */
public class TableActivity extends Activity implements OnClickListener, OnItemClickListener {
	
	/** The table. */
	Table table;
	
	/** The table order. */
	TableOrder tableOrder;
	
	/** The listview. */
	ListView listview;
	
    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState the saved instance state
     */
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.table);
	
	    //Get the table
	    Bundle b = getIntent().getExtras();
	    table = DataProvider.getTable(b.getInt("tableID"));
	    Log.d("CG","Table activity for "+table);
	    
	    //Set table order
	    if(table.tableOrder==null)
	    	table.tableOrder=new TableOrder();
	    tableOrder=table.tableOrder;
	    
	    //Set data
	    TextView text= (TextView) findViewById(R.id.tableTitle);
	    text.setText("Table "+table.id);
	    
	    text= (TextView) findViewById(R.id.tableCustomers);
	    text.setText("Customers: "+table.curClients+"/"+table.maxClients+"");
	    
	    listview = (ListView) findViewById(R.id.tableListView);
	    listview.setAdapter(new TableOrderAdapter(this, tableOrder));
	    listview.setOnItemClickListener(this);
	    
	    ImageButton button=(ImageButton) findViewById(R.id.orderAddButton);
	    button.setOnClickListener(this);

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
		Toast.makeText(this, "Created a new order for this table", Toast.LENGTH_SHORT).show();
	}

	/* Event triggered on click on one of the orders
	 * 
	 * (non-Javadoc)
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		Intent myIntent = new Intent(this, OrderActivity.class);
		myIntent.putExtra("tableID", table.id);
		myIntent.putExtra("orderID", table.tableOrder.orders.get(position).id);
		this.startActivity(myIntent);		
	}

}