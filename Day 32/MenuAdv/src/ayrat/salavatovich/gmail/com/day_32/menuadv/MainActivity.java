package ayrat.salavatovich.gmail.com.day_32.menuadv;

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

	/*
	 * Вызывается только при первом показе меню
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 1, 0, R.string.menu_add);
		menu.add(0, 2, 0, R.string.menu_edit);
		menu.add(0, 3, 3, R.string.menu_delete);
		menu.add(1, 4, 1, R.string.menu_copy);
		menu.add(1, 5, 2, R.string.menu_paste);
		menu.add(1, 6, 4, R.string.menu_exit);
		return super.onCreateOptionsMenu(menu);
	}

	/*
	 * Вызывается каждый раз перед отображением меню
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		menu.setGroupVisible(1, checkBoxAdvanceMenu.isChecked());
		return super.onPrepareOptionsMenu(menu);
	}

	/*
	 * Вызывается при нажатии пункта меню
	 */
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
