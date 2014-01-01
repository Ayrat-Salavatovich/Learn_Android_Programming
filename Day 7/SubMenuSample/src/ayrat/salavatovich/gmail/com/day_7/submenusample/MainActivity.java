package ayrat.salavatovich.gmail.com.day_7.submenusample;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final int EDIT = Menu.FIRST;
	private static final int CUT_MENU_ITEM = EDIT + 1;
	private static final int COPY_MENU_ITEM = CUT_MENU_ITEM + 1;
	private static final int PASTE_MENU_ITEM = COPY_MENU_ITEM + 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		int idx = 0;
		getMenuInflater().inflate(R.menu.main, menu);
		MenuItem menuItemFile = menu.findItem(R.id.action_file);
		menuItemFile.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		SubMenu subMenuEdit = menu.addSubMenu(R.string.action_edit);
		subMenuEdit.add(EDIT, CUT_MENU_ITEM, idx++, R.string.action_cut);
		subMenuEdit.add(EDIT, COPY_MENU_ITEM, idx++, R.string.action_copy);
		subMenuEdit.add(EDIT, PASTE_MENU_ITEM, idx++, R.string.action_paste);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_file:
		case R.id.action_new:
		case R.id.action_open:
		case R.id.action_save:
		case CUT_MENU_ITEM:
		case COPY_MENU_ITEM:
		case PASTE_MENU_ITEM:

			notification(item.getTitle());
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void notification(CharSequence text) {
		Toast toast = Toast.makeText(getApplicationContext(),
				getString(R.string.toast_notification, text),
				Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

}
