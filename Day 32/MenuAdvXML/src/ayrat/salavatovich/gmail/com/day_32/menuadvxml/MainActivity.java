package ayrat.salavatovich.gmail.com.day_32.menuadvxml;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.Resources;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends Activity {

	private CheckBox checkBoxAdvanceMenu;
	private TextView textViewOut;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		checkBoxAdvanceMenu = (CheckBox) findViewById(R.id.checkBoxAdvanceMenu);
		textViewOut = (TextView) findViewById(R.id.textViewOut);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		menu.setGroupVisible(R.id.group_1, checkBoxAdvanceMenu.isChecked());
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Resources res = getResources();
		textViewOut.setText(res.getString(R.string.info_out,
				String.valueOf(item.getGroupId()),
				String.valueOf(item.getItemId()),
				String.valueOf(item.getOrder()), item.getTitle()));
		return super.onOptionsItemSelected(item);
	}

}
