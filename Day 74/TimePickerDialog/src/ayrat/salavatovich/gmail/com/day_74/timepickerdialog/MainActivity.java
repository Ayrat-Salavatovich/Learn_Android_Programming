package ayrat.salavatovich.gmail.com.day_74.timepickerdialog;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.os.Bundle;
import android.text.format.Time;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends Activity {

	private final boolean IS_24_HOUR_VIEW = true;
	private final int DIALOG_TIME = 1;
	private final int RES_ID_TEXT_VIEW = R.id.textView;
	private TextView textView;
	private int hour;
	private int minute;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textView = (TextView) findViewById(RES_ID_TEXT_VIEW);
		Time today = new Time(Time.getCurrentTimezone());
		today.setToNow();
		setHour(today.hour);
		setMinute(today.minute);
	}

	protected void setHour(int hour) {
		this.hour = hour;
	}

	protected void setMinute(int minute) {
		this.minute = minute;
	}

	protected int getHour() {
		return hour;
	}

	protected int getMinute() {
		return minute;
	}

	protected void showTime(MainActivity activity) {
		activity.textView.setText(getString(R.string.time, getHour(),
				getMinute()));
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case RES_ID_TEXT_VIEW:
			showDialog(DIALOG_TIME);

			break;

		default:
			break;
		}
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DIALOG_TIME:
			TimePickerDialog tpd = new TimePickerDialog(this,
					myTimeSetListener, getHour(), getMinute(), IS_24_HOUR_VIEW);

			return tpd;

		default:
			return super.onCreateDialog(id);
		}
	}

	OnTimeSetListener myTimeSetListener = new OnTimeSetListener() {

		@Override
		public void onTimeSet(TimePicker view, int hour, int minute) {
			setHour(hour);
			setMinute(minute);
			showTime(MainActivity.this);
		}

	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
