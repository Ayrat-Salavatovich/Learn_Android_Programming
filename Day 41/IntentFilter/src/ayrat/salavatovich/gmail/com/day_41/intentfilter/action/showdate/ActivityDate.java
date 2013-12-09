package ayrat.salavatovich.gmail.com.day_41.intentfilter.action.showdate;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import ayrat.salavatovich.gmail.com.day_41.intentfilter.R;

public class ActivityDate extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.date);

		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		String date = sdf.format(new Date(System.currentTimeMillis()));

		((TextView) findViewById(R.id.textViewDate)).setText(date);
	}

}
