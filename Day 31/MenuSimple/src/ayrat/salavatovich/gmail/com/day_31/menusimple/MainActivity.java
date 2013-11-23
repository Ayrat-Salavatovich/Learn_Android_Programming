package ayrat.salavatovich.gmail.com.day_31.menusimple;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private TextView textViewOut;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textViewOut = (TextView) findViewById(R.id.textViewOut);
		print("");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		for (int i = 1; i <= 4; i++)
			menu.add(getString(R.string.menu_item, i));

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		notifyMessage(getString(R.string.menu_item_selected, item.getTitle()));
		return super.onOptionsItemSelected(item);
	}

	private void notifyMessage(final String text) {
		print(text);
		toastShow(text);
	}

	private void print(final String text) {
		if (textViewOut != null && text != null && text.length() > 0)
			textViewOut.setText(text);
	}

	private void toastShow(final String text) {
		Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT)
				.show();
	}

}
