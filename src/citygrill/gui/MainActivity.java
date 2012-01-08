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
import citygrill.data.TableOrder;
import citygrill.gui.adapters.TableAdapter;
import citygrill.restaurant.Table;

import com.order.R;

/**
 * The Class MainActivity.
 */
public class MainActivity extends Activity implements OnItemClickListener {
	
	/** The tables. */
	ArrayList<Table> tables;
	GridView gridview;

	static boolean initFinished;
	
	/**
	 * One time init.
	 */
	public static void oneTimeInit()
	{
		if(!initFinished)
		{
			initFinished=true;
		    //Prepare the tables
		    DataProvider.generateTables();
		    DataProvider.generateProducts();
		    DataProvider.generateProductCategories();
		}
	}
	
	int customersNo;
	
    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState the saved instance state
     */
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);
	    
	    oneTimeInit();
	    
	    //Check if it's a restricted tables activity
	    Bundle b = getIntent().getExtras();
	    if(b!=null)
	    {
	    	customersNo = b.getInt("customersNO");
	    	Log.d("CG","Main activity for no customers: "+customersNo);
	    }
	    else customersNo=0;
	    
	    
	    //Get Tables
	    tables=new ArrayList<Table>(DataProvider.getTables());
	    
	    //If a certain number of customers are searching for a table, restrict the rest
	    
	    if(customersNo>0)
	    {
	    	ArrayList<Table> toDelete=new ArrayList<Table>();
	    	for(Table t:tables)
	    	{
	    		if(!t.empty)
	    			toDelete.add(t);
	    		if(t.empty && t.maxClients<customersNo)
	    			toDelete.add(t);		
	    	}
	    	tables.removeAll(toDelete);
	    }
	    //Prepare the grid
	    gridview = (GridView) findViewById(R.id.gridView);
	    gridview.setAdapter(new TableAdapter(this, tables));

	    gridview.setOnItemClickListener(this);
	}
	
	protected void onStart()
	{
		super.onStart();
		gridview.invalidateViews();
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
		final Activity mactivity=this;
		
		//If the table is empty, select number of customers
		if(table.empty)
		{
			//Generate numbers
			final CharSequence[] customers= new CharSequence[table.maxClients];
			for(int i=0;i<customers.length;i++)
				customers[i]=Integer.toString(i+1);

			//If the number of customers is already selected
			if(customersNo>0)
			{
				table.empty=false;
		        table.curClients=customersNo;
		        table.tableOrder=new TableOrder();
		        
		        Intent myIntent = new Intent(mactivity, TableActivity.class);
				myIntent.putExtra("tableID", table.id);
				mactivity.startActivity(myIntent);
				
				mactivity.finish();
			}
			else
			{
				//Create the dialog
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("Number of customers");
				builder.setItems(customers, new DialogInterface.OnClickListener() {
				    public void onClick(DialogInterface dialog, int item) {
				        Toast.makeText(getApplicationContext(), customers[item] + " customers selected.", Toast.LENGTH_SHORT).show();
				        table.empty=false;
				        table.curClients=item+1;
				        table.tableOrder=new TableOrder();
				        
				        Intent myIntent = new Intent(mactivity, TableActivity.class);
						myIntent.putExtra("tableID", table.id);
						mactivity.startActivity(myIntent);
	       
				    }
				});
				
				//Show the dialog
				AlertDialog alert = builder.create();
				alert.show();
			}
		}
		//If the table is not empty, start the Table Activity
		else {
			Intent myIntent = new Intent(this, TableActivity.class);
			myIntent.putExtra("tableID", table.id);
			this.startActivity(myIntent);
			
			//Kill the intermediate activity if it's a search type
			if(customersNo>0)
				finish();
		}
	}
	
	 /* (non-Javadoc)
 	 * @see android.app.Activity#onSearchRequested()
 	 */
	@Override
	public boolean onSearchRequested() {
		
		//Generate numbers
		final CharSequence[] customers= new CharSequence[8];
		for(int i=0;i<customers.length;i++)
			customers[i]=Integer.toString(i+1);
		final Activity mactivity=this;
		
		//Create the dialog
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Number of customers");
		builder.setItems(customers, new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int item) {
		        Toast.makeText(getApplicationContext(), customers[item] + " customers selected.", Toast.LENGTH_SHORT).show();
		        
		        Intent myIntent = new Intent(mactivity, MainActivity.class);
				myIntent.putExtra("customersNO", (item+1));
				mactivity.startActivity(myIntent);
		    }
		});
		
		//Show the dialog
		AlertDialog alert = builder.create();
		alert.show();
		
		return false; // don't go ahead and show the search box
	}


}