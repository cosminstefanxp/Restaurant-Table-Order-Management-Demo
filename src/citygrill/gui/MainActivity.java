/*
 * IOC - Tema2
 * 
 * Stefan-Dobrin Cosmin
 * 342C4
 */
package citygrill.gui;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import citygrill.data.DataProvider;
import citygrill.restaurant.Table;

import com.order.R;

/**
 * The Class MainActivity.
 */
public class MainActivity extends Activity implements OnItemClickListener {
	
	/** The tables. */
	ArrayList<Table> tables;
	GridView gridview;

    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState the saved instance state
     */
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);
	    
	    //Prepare the tables
	    tables=DataProvider.getTables();

	    //Prepare the grid
	    gridview = (GridView) findViewById(R.id.gridView);
	    gridview.setAdapter(new TableAdapter(this, tables));

	    gridview.setOnItemClickListener(this);
	}

	/* When a table is clicked on the Grid View with the tables.
	 * 
	 * (non-Javadoc)
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		
		Toast.makeText(MainActivity.this, "Table "+position+" selected.",Toast.LENGTH_SHORT).show();
		final Table table=tables.get(position);
		Log.d("CG","Clicked on table "+table);
		
		//If the table is empty, select number of customers
		if(table.empty)
		{
			//Generate numbers
			final CharSequence[] customers= new CharSequence[table.maxClients];
			for(int i=0;i<customers.length;i++)
				customers[i]=Integer.toString(i+1);

			//Create the dialog
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Number of customers");
			builder.setItems(customers, new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog, int item) {
			        Toast.makeText(getApplicationContext(), customers[item] + " customers selected.", Toast.LENGTH_SHORT).show();
			        table.empty=false;
			        table.curClients=item+1;
			        MainActivity.this.gridview.invalidateViews();
			    }
			});
			
			//Show the dialog
			AlertDialog alert = builder.create();
			alert.show();
		}
		//If the table is not empty, start the Table Activity
		else {
			Intent myIntent = new Intent(this, TableActivity.class);
			myIntent.putExtra("table", table);
			this.startActivity(myIntent);
		}
	}

}