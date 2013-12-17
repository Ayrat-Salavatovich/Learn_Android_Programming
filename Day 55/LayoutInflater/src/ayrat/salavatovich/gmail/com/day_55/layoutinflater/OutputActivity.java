package ayrat.salavatovich.gmail.com.day_55.layoutinflater;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class OutputActivity extends Activity {

	public static final String OUT = "ayrat.salavatovich.gmail.com.day_55.layoutinflater.OUT";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.log);

		((TextView) findViewById(R.id.textViewOut)).setText(getIntent()
				.getStringExtra(OUT));
	}

}
