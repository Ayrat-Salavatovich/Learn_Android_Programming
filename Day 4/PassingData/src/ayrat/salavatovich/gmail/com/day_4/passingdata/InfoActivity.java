package ayrat.salavatovich.gmail.com.day_4.passingdata;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class InfoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info);

		TextView textViewInfo = (TextView) findViewById(R.id.textViewInfo);
		String username = "Жывотное";
		String description = "дырку от бублика";

		username = getIntent().getStringExtra(
				"ayrat.salavatovich.gmail.com.day_4.passingdata.USERNAME");
		description = getIntent().getStringExtra(
				"ayrat.salavatovich.gmail.com.day_4.passingdata.DESCRIPTION");

		textViewInfo.setText(username + " , вам передали " + description);

	}

}
