package ayrat.salavatovich.gmail.com.day_84.headerfooter;

import android.app.Activity;
import android.graphics.Path.FillType;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private ListView listView;
	private View header1, header2;
	private View footer1, footer2;
	private ArrayAdapter<CharSequence> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		listView = (ListView) findViewById(R.id.listView);
		adapter = ArrayAdapter.createFromResource(this, R.array.countries,
				android.R.layout.simple_list_item_1);

		header1 = createHeader(R.string.header_1);
		header2 = createHeader(R.string.header_2);

		footer1 = createFooter(R.string.footer_1);
		footer2 = createFooter(R.string.footer_2);

		fillList();
	}

	public void onClick(View v) {
		listView.removeHeaderView(header2);
		listView.removeFooterView(footer2);
	}

	protected View createHeader(final int resId) {
		View header = createHeaderView(R.layout.header);
		setTextForHeaderView(header, R.id.textViewHeaderText, resId);

		return header;
	}

	protected View createFooter(final int resId) {
		View footer = createHeaderView(R.layout.footer);
		setTextForHeaderView(footer, R.id.textViewFooterText, resId);

		return footer;
	}

	protected void fillList() {
		listView.addHeaderView(header1, getString(R.string.data_for_header_1),
				false);
		listView.addHeaderView(header2);
		listView.addFooterView(footer1, getString(R.string.data_for_footer_1),
				false);
		listView.addFooterView(footer2);
		listView.setAdapter(adapter);
	}

	private View createHeaderView(final int resource) {
		return getLayoutInflater().inflate(resource, null);
	}

	private void setTextForHeaderView(final View v, final int id,
			final int resId) {
		((TextView) v.findViewById(id)).setText(resId);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
