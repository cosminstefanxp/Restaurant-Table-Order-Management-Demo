/*
 * IOC - Tema2
 * 
 * Stefan-Dobrin Cosmin
 * 342C4
 */
package citygrill.gui.adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import citygrill.data.Order;
import citygrill.data.OrderProduct;

import com.order.R;

/**
 * The Class OrderAdapter.
 */
public class OrderAdapter extends BaseAdapter {
	
	/** The m context. */
	private final Context mContext;
	
	/** The activity. */
	private final Activity activity;
	
	/** The order. */
	private Order order;
	/** The inflater. */
	private LayoutInflater mInflater;

	/**
	 * Instantiates a new order adapter.
	 *
	 * @param c the c
	 * @param order the order
	 */
	public OrderAdapter(Activity a, Order order) {
		this.mContext = a.getBaseContext();
		this.order=order;
		mInflater = LayoutInflater.from(mContext);
		this.activity=a;
	}
	
	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		return order.products.size();
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int arg0) {
		return order.products.get(arg0);
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		
		//Attempt to inflate a pre-existing View
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.order_product_list, null);
			
			//Crate a new holder that stores references to the Views in each entry in the list
			holder = new ViewHolder();
			holder.productName = (TextView) convertView.findViewById(R.id.orderProdNameText);
			holder.quantity = (TextView) convertView.findViewById(R.id.orderProductQuantityText);
			holder.price = (TextView) convertView.findViewById(R.id.orderProductPriceText);
			holder.image = (ImageView) convertView.findViewById(R.id.orderProdImage);
			holder.buttonAdd = (ImageButton) convertView.findViewById(R.id.orderProductAddButton);
			holder.buttonRemove = (ImageButton) convertView.findViewById(R.id.orderProductRemoveButton);

			//Associate the view references as a tag, to the entry in the list
			convertView.setTag(holder);
		} 
		//I a convertView exists, just popup the already prepared references to the views
		else {
			holder = (ViewHolder) convertView.getTag();
		}

		//Assign the data accordingly
		OrderProduct product=order.products.get(position);
		
		holder.productName.setText(product.product.name);
		holder.quantity.setText("Quantity: "+product.quantity);
		holder.price.setText("Price: "+product.product.price);
		holder.image.setImageResource(product.product.resource);
		holder.buttonAdd.setOnClickListener(new ButtonClickHandler(activity, convertView, order, product, 1));
		holder.buttonRemove.setOnClickListener(new ButtonClickHandler(activity, convertView, order, product, -1));

		return convertView;
	}
	
	/**
	 * The Class ViewHolder.
	 */
	static class ViewHolder {

		/** The forecast date. */
		TextView productName;

		/** The temp max. */
		TextView quantity;
		
		/** The price. */
		TextView price;
		
		/** The button add. */
		ImageButton buttonAdd;
		
		/** The button remove. */
		ImageButton buttonRemove;
		
		/** The weather condition. */
		ImageView image;
	}
	
	/**
	 * The Class ButtonClickHandler that handles clicks on one of the quantity modifier buttons.
	 */
	private class ButtonClickHandler implements OnClickListener
	{
		
		/** The order . */
		Order order;
		
		/** The associated view. */
		View associatedView;
		
		/** The order prod. */
		OrderProduct orderProduct;
		
		/** The value with which the quantity is modified (should be 1 or -1). */
		int addValue;
		
		/** The activity. */
		Activity activity;
		
		/**
		 * Instantiates a new button click handler.
		 *
		 * @param orderProduct the order product
		 * @param addValue the add value
		 */
		public ButtonClickHandler(Activity activity, View view, Order order, OrderProduct orderProd, int addValue) {
			super();
			this.order=order;
			this.addValue=addValue;
			this.activity=activity;
			this.orderProduct=orderProd;
			this.associatedView=view;
			
		}

		/* (non-Javadoc)
		 * @see android.view.View.OnClickListener#onClick(android.view.View)
		 */
		@Override
		public void onClick(View v) {
			orderProduct.quantity+=addValue;
			order.totalPrice+=addValue*orderProduct.product.price;
			Log.d("CG", "Quantity for "+orderProduct+" modified by "+addValue);
			Toast.makeText(activity.getBaseContext(), "Quantity for '"+orderProduct.product.name+"' modified by "+addValue, Toast.LENGTH_SHORT).show();
			//Delete the product from the order, if the quantity is 0
			if(orderProduct.quantity==0)
				order.products.remove(orderProduct);
			//Refresh the graphics
			((ListView)(associatedView.getParent())).invalidateViews();
			((TextView)activity.findViewById(R.id.orderCostText)).setText("Cost: "+order.totalPrice);
		}
		
	}

}
