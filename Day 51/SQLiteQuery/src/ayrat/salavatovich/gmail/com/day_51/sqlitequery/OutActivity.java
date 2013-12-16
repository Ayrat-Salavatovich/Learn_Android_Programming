package ayrat.salavatovich.gmail.com.day_51.sqlitequery;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class OutActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.out);

		TextView textViewOut = (TextView) findViewById(R.id.textViewOut);
		String records = getIntent().getStringExtra(MainActivity.RECORDS);
		textViewOut.setText(records);
	}

}
