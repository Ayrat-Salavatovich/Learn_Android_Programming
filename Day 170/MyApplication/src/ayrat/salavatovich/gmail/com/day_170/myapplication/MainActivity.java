package ayrat.salavatovich.gmail.com.day_170.myapplication;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		MyApplication myApplication = (MyApplication) getApplication();
		((TextView) findViewById(R.id.textViewCountry)).setText(getString(
				R.string.text_view_country, myApplication.getCountry()));
		((TextView) findViewById(R.id.textViewCapital)).setText(getString(
				R.string.text_view_capital, myApplication.getCapital()));
		((TextView) findViewById(R.id.textViewOfficialLanguages))
				.setText(getString(R.string.text_view_official_languages,
						myApplication.getOfficialLanguages()));
		((TextView) findViewById(R.id.textViewFoundation)).setText(getString(
				R.string.text_view_foundation, myApplication.getFoundation()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
