package ayrat.salavatovich.gmail.com.day_149.astrologicalsign;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ListView listView = (ListView) findViewById(R.id.listView);

		setAdapterForListView(listView);
	}

	private void setAdapterForListView(final ListView listView) {
		ListAdapter adapter = null;
		final int adapterId = loadPreferencesIdAdapter();
		switch (adapterId) {
		case R.string.array_adapter:
			adapter = new CustomArrayAdapter(this, R.layout.list_item,
					R.id.textViewSignName, R.array.signs);

			break;

		case R.string.base_adapter:
			adapter = new CustomBaseAdapter(this);

			break;

		default:
			break;
		}

		if (adapter != null)
			listView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private class CustomArrayAdapter extends ArrayAdapter<String> {

		public CustomArrayAdapter(Context context, int resource,
				int textViewResourceId, int resArray) {
			super(context, resource, textViewResourceId, getResources()
					.getStringArray(resArray));
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = getLayoutInflater();
			View sign = inflater.inflate(R.layout.list_item, parent, false);
			sign.setBackgroundColor((position & 1) == 1 ? Color.WHITE
					: Color.LTGRAY);
			TextView textViewSignName = (TextView) sign
					.findViewById(R.id.textViewSignName);
			textViewSignName.setText(getResources().getStringArray(
					R.array.signs)[position]);
			TextView textViewPerion = (TextView) sign
					.findViewById(R.id.textViewPeriod);
			textViewPerion.setText(getResources().getStringArray(
					R.array.periods)[position]);
			ImageView imageViewSymbol = (ImageView) sign
					.findViewById(R.id.imageViewSymbol);
			imageViewSymbol.setImageDrawable(getResources().getDrawable(
					getResources()
							.getIdentifier(
									getResources().getStringArray(
											R.array.symbols)[position],
									"drawable", getPackageName())));

			return sign;
		}

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.action_settings:
			Intent intent = new Intent(this, SettingsActivity.class);
			startActivity(intent);

		default:
			break;
		}

		return true;
	}

	private int loadPreferencesIdAdapter() {
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this);

		String regular = preferences.getString(
				getString(R.string.preference_adapter),
				getString(R.string.array_adapter));
		int adapter = R.string.array_adapter;

		if (regular.contains(getString(R.string.array_adapter)))
			adapter = R.string.array_adapter;
		else if (regular.contains(getString(R.string.base_adapter)))
			adapter = R.string.base_adapter;

		return adapter;
	}

	public class CustomBaseAdapter extends BaseAdapter {
		private LayoutInflater layoutInflater;

		public CustomBaseAdapter(Context context) {
			this.layoutInflater = LayoutInflater.from(context);
		}

		public int getCount() {
			return getResources().getStringArray(R.array.signs).length;
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public String getString(int position) {
			return getResources().getStringArray(R.array.signs)[position];
		}

		public View getView(int position, View convertView, ViewGroup parent) {

			if (convertView == null)
				convertView = layoutInflater.inflate(R.layout.list_item, null);

			TextView textViewSignName = (TextView) convertView
					.findViewById(R.id.textViewSignName);
			textViewSignName.setText(getResources().getStringArray(
					R.array.signs)[position]);
			TextView textViewPerion = (TextView) convertView
					.findViewById(R.id.textViewPeriod);
			textViewPerion.setText(getResources().getStringArray(
					R.array.periods)[position]);
			ImageView imageViewSymbol = (ImageView) convertView
					.findViewById(R.id.imageViewSymbol);
			imageViewSymbol.setImageDrawable(getResources().getDrawable(
					getResources()
							.getIdentifier(
									getResources().getStringArray(
											R.array.symbols)[position],
									"drawable", getPackageName())));

			return convertView;
		}
	}

}
