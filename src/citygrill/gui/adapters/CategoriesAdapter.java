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
import citygrill.data.ProductCategory;

import com.order.R;

/**
 * The Class OrderAdapter.
 */
public class CategoriesAdapter extends BaseAdapter {
	
	/** The m context. */
	@SuppressWarnings("unused")
	private final Context mContext;
	
	/** The inflater. */
	private LayoutInflater mInflater;
	
	/** The categories. */
	private ArrayList<ProductCategory> categories;

	/**
	 * Instantiates a new order adapter.
	 *
	 * @param c the c
	 * @param order the order
	 */
	public CategoriesAdapter(Context c, ArrayList<ProductCategory> categories) {
		this.mContext = c;
		this.categories=categories;
		mInflater = LayoutInflater.from(c);
	}
	
	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		return categories.size();
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int arg0) {
		return categories.get(arg0);
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
			convertView = mInflater.inflate(R.layout.categories_list, null);
			
			//Crate a new holder that stores references to the Views in each entry in the list
			holder = new ViewHolder();
			holder.name = (TextView) convertView.findViewById(R.id.categoryNameText);
			holder.image = (ImageView) convertView.findViewById(R.id.categoryImage);

			//Associate the view references as a tag, to the entry in the list
			convertView.setTag(holder);
		} 
		//I a convertView exists, just popup the already prepared references to the views
		else {
			holder = (ViewHolder) convertView.getTag();
		}

		//Assign the data accordingly
		holder.name.setText(categories.get(position).name);
		holder.image.setImageResource(categories.get(position).resource);

		return convertView;
	}
	
	/**
	 * The Class ViewHolder.
	 */
	static class ViewHolder {

		/** The name. */
		TextView name;
		
		/** The image for the category. */
		ImageView image;
	}

}
