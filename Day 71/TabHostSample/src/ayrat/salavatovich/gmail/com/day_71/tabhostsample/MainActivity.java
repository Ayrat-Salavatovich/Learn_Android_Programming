package ayrat.salavatovich.gmail.com.day_71.tabhostsample;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TabHost;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
		tabHost.setup();

		TabHost.TabSpec tabSpec;

		tabSpec = tabHost.newTabSpec(getString(R.string.tag_page_1));
		tabSpec.setContent(R.id.tabPage1);
		tabSpec.setIndicator(getString(R.string.indicator_page_1));
		tabHost.addTab(tabSpec);

		tabSpec = tabHost.newTabSpec(getString(R.string.tag_page_2));
		tabSpec.setContent(R.id.tabPage2);
		tabSpec.setIndicator(getString(R.string.indicator_page_2));
		tabHost.addTab(tabSpec);

		tabSpec = tabHost.newTabSpec(getString(R.string.tag_page_3));
		tabSpec.setContent(R.id.tabPage3);
		tabSpec.setIndicator(getString(R.string.indicator_page_3));
		tabHost.addTab(tabSpec);

		tabHost.setCurrentTab(2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
