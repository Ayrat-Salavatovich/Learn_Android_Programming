package ayrat.salavatovich.gmail.com.day_125.actionbarnavigation;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class TabsActivity extends Activity implements ActionBar.TabListener {

	private final String LOG_TAG = TabsActivity.class.getCanonicalName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.empty);

		ActionBar actionBar = getActionBar();

		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);

		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		Tab tab = actionBar.newTab();
		tab.setText(R.string.tab_1);
		tab.setTabListener(this);
		actionBar.addTab(tab);

		tab = actionBar.newTab();
		tab.setText(R.string.tab_2);
		tab.setTabListener(this);
		actionBar.addTab(tab);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:

			finish();

			break;

		default:
			return super.onContextItemSelected(item);
		}

		return true;
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		Log.d(LOG_TAG, getString(R.string.reselected_tab_is, tab.getText()));
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		Log.d(LOG_TAG, getString(R.string.selected_tab_is, tab.getText()));
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		Log.d(LOG_TAG, getString(R.string.unselected_tab_is, tab.getText()));
	}

}
