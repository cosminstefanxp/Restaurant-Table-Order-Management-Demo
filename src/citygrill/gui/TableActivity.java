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
import android.widget.TextView;
import citygrill.data.TableOrder;
import citygrill.restaurant.Table;

import com.order.R;

/**
 * The Class MainActivity.
 */
public class TableActivity extends Activity {
	
	/** The table. */
	Table table;
	
	/** The table order. */
	TableOrder tableOrder;
	
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
	    table = (Table)b.get("table");
	    Log.d("CG","Table activity for "+table);
	    
	    //Set table order
	    tableOrder=new TableOrder();
	    
	    //Set data
	    TextView title= (TextView) findViewById(R.id.tableTitle);
	    title.setText("Table "+table.id+" ("+table.curClients+"/"+table.maxClients+")");

	}

}