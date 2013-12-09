package ayrat.salavatovich.gmail.com.day_41.intentfilter.action.showtime;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import ayrat.salavatovich.gmail.com.day_41.intentfilter.R;

public class ActivityTime extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.time);

		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String time = sdf.format(new Date(System.currentTimeMillis()));

		((TextView) findViewById(R.id.textViewTime)).setText(time);
	}

}
