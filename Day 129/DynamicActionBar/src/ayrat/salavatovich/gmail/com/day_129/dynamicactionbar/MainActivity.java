package ayrat.salavatovich.gmail.com.day_129.dynamicactionbar;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

public class MainActivity extends Activity {

	private final int MENU_ID = 1;

	private CheckBox checkBoxAddDel;
	private CheckBox checkBoxVisibleHide;

	private Fragment fragment1;
	private Fragment fragment2;
	private Fragment fragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		checkBoxAddDel = (CheckBox) findViewById(R.id.checkBoxAddDel);
		checkBoxVisibleHide = (CheckBox) findViewById(R.id.checkBoxVisibleHide);

		fragment = fragment1 = new Fragment1();
		fragment2 = new Fragment2();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		menu.setGroupVisible(R.id.groupVisible, checkBoxVisibleHide.isChecked());
		
		if (checkBoxAddDel.isChecked())
			menu.add(0, MENU_ID, 0, R.string.action_menu_item1)
					.setIcon(android.R.drawable.ic_delete)
					.setShowAsAction(
							MenuItem.SHOW_AS_ACTION_ALWAYS
									| MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		else
			menu.removeItem(MENU_ID);

		return true;
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.checkBoxAddDel:
		case R.id.checkBoxVisibleHide:

			invalidateOptionsMenu();

			break;

		case R.id.buttonFragment:

			fragment = (fragment == fragment1) ? fragment2 : fragment1;
			getFragmentManager().beginTransaction()
					.replace(R.id.fragment, fragment).commit();

		default:
			break;
		}
	}

}
