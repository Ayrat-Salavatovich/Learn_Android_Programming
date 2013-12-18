package ayrat.salavatovich.gmail.com.day_58.simplelistmultiplechoice;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ListView listViewMain;
	private String[] countries;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		context = getApplicationContext();
		countries = getResources().getStringArray(R.array.countries);

		listViewMain = (ListView) findViewById(R.id.listViewMain);
		listViewMain.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				context, R.array.countries,
				android.R.layout.simple_list_item_multiple_choice);
		listViewMain.setAdapter(adapter);
	}

	public void onClick(View v) {
		if (v.getId() == R.id.buttonChecked) {
			StringBuilder sb = new StringBuilder();
			SparseBooleanArray sparseBooleanArray = listViewMain
					.getCheckedItemPositions();
			for (int i = 0; i < sparseBooleanArray.size(); i++) {
				int key = sparseBooleanArray.keyAt(i);
				if (sparseBooleanArray.get(key)) {
					sb.append(getString(R.string.checked, getCountryName(key)))
							.append(System.lineSeparator());
				}
			}

			Toast.makeText(context, sb.toString(), Toast.LENGTH_LONG).show();
		}
	}

	private String getCountryName(int key) {
		return countries[key];
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
