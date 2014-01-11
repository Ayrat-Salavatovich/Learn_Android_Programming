package ayrat.salavatovich.gmail.com.day_82.customadapter;

import java.text.NumberFormat;
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class MarketAdapter extends BaseAdapter {

	private Context ctx;
	private ArrayList<SimpleProduct> products;
	private LayoutInflater layoutInflater;

	public MarketAdapter(Context ctx, ArrayList<SimpleProduct> products) {
		this.ctx = ctx;
		this.products = products;
		layoutInflater = (LayoutInflater) ctx
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return products.size();
	}

	@Override
	public Object getItem(int position) {
		return products.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		NumberFormat currencyInstance = NumberFormat.getCurrencyInstance();

		View view = (convertView == null) ? layoutInflater.inflate(
				R.layout.item, parent, false) : convertView;

		SimpleProduct product = (SimpleProduct) getItem(position);

		((TextView) view.findViewById(R.id.textViewDescription))
				.setText(product.getDescription());
		((TextView) view.findViewById(R.id.textViewPrice))
				.setText(currencyInstance.format(product.getPrice()));
		CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox);
		checkBox.setText(product.getName());
		checkBox.setTag(position);
		checkBox.setChecked(product.isCart());
		checkBox.setOnCheckedChangeListener(myCheckedChangeListener);

		return view;
	}

	public ArrayList<SimpleProduct> getCart() {
		ArrayList<SimpleProduct> cart = new ArrayList<SimpleProduct>();
		for (SimpleProduct product : products)
			if (product.isCart())
				cart.add(product);

		return cart;
	}

	OnCheckedChangeListener myCheckedChangeListener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			((SimpleProduct) getItem((Integer) buttonView.getTag()))
					.setCart(isChecked);
		}

	};

}
