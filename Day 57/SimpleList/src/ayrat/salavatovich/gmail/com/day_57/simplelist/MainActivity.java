package ayrat.salavatovich.gmail.com.day_57.simplelist;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ListView listViewMain = (ListView) findViewById(R.id.listViewMain);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.my_list_item, getResources().getStringArray(
						R.array.countries));

		listViewMain.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
