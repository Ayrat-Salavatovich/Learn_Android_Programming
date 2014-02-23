package ayrat.salavatovich.gmail.com.day_15.listactivitywithimage;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setListAdapter(new MyListAdapter(this,
				android.R.layout.simple_list_item_1, R.id.textViewNameClub,
				R.array.night_clubs_portugal));

	}

	private class MyListAdapter extends ArrayAdapter<String> {

		public MyListAdapter(Context context, int resource,
				int textViewResourceId, int resArray) {
			super(context, resource, textViewResourceId, getResources()
					.getStringArray(resArray));
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View view = inflater.inflate(R.layout.club, parent, false);
			String[] clubs = getResources().getStringArray(
					R.array.night_clubs_portugal);

			ImageView imageViewClub = (ImageView) view
					.findViewById(R.id.imageViewClub);
			TextView textViewNameClub = (TextView) view
					.findViewById(R.id.textViewNameClub);

			textViewNameClub.setText(clubs[position]);

			if (clubs[position].equals(getString(R.string.docks_club)))
				imageViewClub.setImageResource(R.drawable.docks_club);
			else if (clubs[position].equals(getString(R.string.fragil)))
				imageViewClub.setImageResource(R.drawable.fragil);
			else if (clubs[position].equals(getString(R.string.incognito)))
				imageViewClub.setImageResource(R.drawable.incognito);
			else if (clubs[position].equals(getString(R.string.lust)))
				imageViewClub.setImageResource(R.drawable.lust);
			else if (clubs[position].equals(getString(R.string.lux)))
				imageViewClub.setImageResource(R.drawable.lux);
			else if (clubs[position].equals(getString(R.string.main)))
				imageViewClub.setImageResource(R.drawable.main);
			else if (clubs[position]
					.equals(getString(R.string.ministerium_club)))
				imageViewClub.setImageResource(R.drawable.ministerium_club);
			else if (clubs[position].equals(getString(R.string.musicbox)))
				imageViewClub.setImageResource(R.drawable.musicbox);
			else if (clubs[position].equals(getString(R.string.op_art_cafe)))
				imageViewClub.setImageResource(R.drawable.op_art_cafe);
			else if (clubs[position].equals(getString(R.string.plateau)))
				imageViewClub.setImageResource(R.drawable.plateau);

			return view;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
