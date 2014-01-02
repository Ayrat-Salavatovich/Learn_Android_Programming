package ayrat.salavatovich.gmail.com.day_71.tabhostsamplewithicon;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends TabActivity {

	private TabHost tabHost;
	private Resources resources;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		tabHost = getTabHost();
		resources = getResources();

		addTab(TabAndroid.class, getString(R.string.id_android),
				getString(R.string.indicator_android), R.drawable.android);
		addTab(TabJava.class, getString(R.string.id_java),
				getString(R.string.indicator_java), R.drawable.java);
		addTab(TabClojure.class, getString(R.string.id_clojure),
				getString(R.string.indicator_clojure), R.drawable.clojure);

		tabHost.setCurrentTab(2);

	}

	private void addTab(Class<?> cls, String tag, String label, int drawableId) {
		Intent intent = new Intent(this, cls);
		TabHost.TabSpec spec = tabHost.newTabSpec(tag);

		View tabIndicator = LayoutInflater.from(this).inflate(
				R.layout.tab_indicator, getTabWidget(), false);
		TextView title = (TextView) tabIndicator.findViewById(R.id.title);
		title.setText(label);
		ImageView icon = (ImageView) tabIndicator.findViewById(R.id.icon);
		icon.setImageResource(drawableId);

		spec.setIndicator(tabIndicator);
		spec.setContent(intent);
		tabHost.addTab(spec);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
