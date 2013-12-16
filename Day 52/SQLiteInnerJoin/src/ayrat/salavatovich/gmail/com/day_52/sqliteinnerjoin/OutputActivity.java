package ayrat.salavatovich.gmail.com.day_52.sqliteinnerjoin;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class OutputActivity extends Activity {

	public static final String OUTPUT = "ayrat.salavatovich.gmail.com.day_52.sqliteinnerjoin.RECORDS.OUTPUT";
	public static final String RECORDS = "ayrat.salavatovich.gmail.com.day_52.sqliteinnerjoin.RECORDS";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.out);

		TextView textViewOut = (TextView) findViewById(R.id.textViewOut);
		String records = getIntent().getStringExtra(RECORDS);
		textViewOut.setText(records);
	}

}
