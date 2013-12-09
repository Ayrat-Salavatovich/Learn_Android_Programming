package ayrat.salavatovich.gmail.com.day_42.getintentaction.action.show;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import ayrat.salavatovich.gmail.com.day_42.getintentaction.R;

public class Info extends Activity {

	TextView textViewInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info);

		Intent intent = getIntent();
		String action = intent.getAction();

		textViewInfo = (TextView) findViewById(R.id.textViewInfo);

		if (action
				.equals("ayrat.salavatovich.gmail.com.day_42.getintentaction.action.showtime"))
			showTime();
		else if (action
				.equals("ayrat.salavatovich.gmail.com.day_42.getintentaction.action.showdate"))
			showDate();

	}

	private void showTime() {
		textViewInfo.setText(getResources().getString(R.string.info_time,
				formatCurrentDateTime("HH:mm:ss")));
	}

	private void showDate() {
		textViewInfo.setText(getResources().getString(R.string.info_date,
				formatCurrentDateTime("dd.MM.yyyy")));
	}

	private String formatCurrentDateTime(String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(new Date(System.currentTimeMillis()));
	}

}
