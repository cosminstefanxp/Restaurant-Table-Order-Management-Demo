package citygrill.gui;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import citygrill.data.Order;
import citygrill.data.TableOrder;

public class TableOrderAdapter extends BaseAdapter{

	private final Context mContext;
	private TableOrder tableOrder;

	public TableOrderAdapter(Context c, TableOrder tableOrder) {
		this.mContext = c;
		this.tableOrder=tableOrder;
	}

	
	@Override
	public int getCount() {
		return tableOrder.orders.size();
	}


	@Override
	public Object getItem(int position) {
		return tableOrder.orders.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		TextView orderButton;
		Order order=tableOrder.orders.get(position);
		//Try to use a recycled button
		if (convertView == null) { // if it's not recycled, initialize some
									// attributes
			orderButton = new TextView(mContext);
			orderButton.setGravity(Gravity.CENTER);
			orderButton.setPadding(8, 8, 8, 8);
			orderButton.setTextColor(Color.WHITE);
		} else {
			orderButton = (TextView) convertView;
		}
		
		//Add the specific data
		orderButton.setText("Order "+order.id+"\n\nProducts: "+order.products.size()+"\t\tTotal Price: "+order.totalPrice);
		
		//tableButton.setImageResource(mThumbIds[position]);
		return orderButton;
	}

}
