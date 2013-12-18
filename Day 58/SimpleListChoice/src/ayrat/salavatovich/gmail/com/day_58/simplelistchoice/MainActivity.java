package ayrat.salavatovich.gmail.com.day_58.simplelistchoice;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
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
		listViewMain.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.countries,
				android.R.layout.simple_list_item_single_choice);
		listViewMain.setAdapter(adapter);

		findViewById(R.id.buttonChecked).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						Toast.makeText(
								context,
								getString(R.string.checked_is,
										countries[listViewMain
												.getCheckedItemPosition()]),
								Toast.LENGTH_SHORT).show();
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
