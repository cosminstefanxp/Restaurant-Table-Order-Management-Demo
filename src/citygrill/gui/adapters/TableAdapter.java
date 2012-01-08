package citygrill.gui.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import citygrill.restaurant.Table;

import com.order.R;

public class TableAdapter extends BaseAdapter {
	private final Context mContext;
	private ArrayList<Table> tables;

	public TableAdapter(Context c, ArrayList<Table> tables) {
		this.mContext = c;
		this.tables=tables;
	}

	public int getCount() {
		return tables.size();
	}

	public Object getItem(int position) {
		return tables.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	// create a new Button for each item referenced by the Adapter
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView tableButton;
		Table table=tables.get(position);
		//Try to use a recycled button
		if (convertView == null) { // if it's not recycled, initialize some
									// attributes
			tableButton = new TextView(mContext);
			tableButton.setLayoutParams(new GridView.LayoutParams(200, 200));
			tableButton.setGravity(Gravity.TOP);
			tableButton.setGravity(Gravity.CENTER_HORIZONTAL);
			tableButton.setPadding(8, 30, 8, 8);
			tableButton.setTextColor(Color.WHITE);
		} else {
			tableButton = (TextView) convertView;
		}
		
		//Add the specific data
		tableButton.setText("Table "+table.id+"\n\n("+table.curClients+"/"+table.maxClients+")");
		if(tables.get(position).empty)
			tableButton.setBackgroundResource(R.drawable.empty_table);
		else
			tableButton.setBackgroundResource(R.drawable.busy_table);
		
		//tableButton.setImageResource(mThumbIds[position]);
		return tableButton;
	}
}
