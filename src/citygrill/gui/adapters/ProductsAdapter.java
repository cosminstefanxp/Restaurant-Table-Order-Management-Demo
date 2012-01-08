/*
 * IOC - Tema2
 * 
 * Stefan-Dobrin Cosmin
 * 342C4
 */
package citygrill.gui.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import citygrill.data.Product;

import com.order.R;

/**
 * The Class OrderAdapter.
 */
public class ProductsAdapter extends BaseAdapter {
	
	/** The m context. */
	@SuppressWarnings("unused")
	private final Context mContext;
	
	/** The inflater. */
	private LayoutInflater mInflater;
	
	/** The categories. */
	private ArrayList<Product> products;

	/**
	 * Instantiates a new order adapter.
	 *
	 * @param c the c
	 * @param order the order
	 */
	public ProductsAdapter(Context c, ArrayList<Product> products) {
		this.mContext = c;
		this.products=products;
		mInflater = LayoutInflater.from(c);
	}
	
	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		return products.size();
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int arg0) {
		return products.get(arg0);
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
			convertView = mInflater.inflate(R.layout.products_list, null);
			
			//Crate a new holder that stores references to the Views in each entry in the list
			holder = new ViewHolder();
			holder.name = (TextView) convertView.findViewById(R.id.productNameText);
			holder.price = (TextView) convertView.findViewById(R.id.productPriceText);
			holder.image = (ImageView) convertView.findViewById(R.id.productImage);

			//Associate the view references as a tag, to the entry in the list
			convertView.setTag(holder);
		} 
		//If a convertView exists, just popup the already prepared references to the views
		else {
			holder = (ViewHolder) convertView.getTag();
		}

		//Assign the data accordingly
		holder.name.setText(products.get(position).name);
		holder.price.setText("Price: "+Float.toString(products.get(position).price));
		holder.image.setImageResource(products.get(position).resource);

		return convertView;
	}
	
	/**
	 * The Class ViewHolder.
	 */
	static class ViewHolder {

		/** The name. */
		TextView name;
		
		/** The price. */
		TextView price;
		
		/** The image for the category. */
		ImageView image;
	}

}
