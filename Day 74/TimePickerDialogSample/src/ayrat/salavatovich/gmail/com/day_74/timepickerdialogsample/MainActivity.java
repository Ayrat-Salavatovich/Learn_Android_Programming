package ayrat.salavatovich.gmail.com.day_74.timepickerdialogsample;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends Activity {

	private final boolean IS_24_HOUR_VIEW = true;
	private Calendar calendar;
	private TextView textViewTime;
	private final int ID_ALERT_DIALOG = 0;
	private final Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textViewTime = (TextView) findViewById(R.id.textViewTime);
		calendar = Calendar.getInstance();
		updateTime();
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button:
			showDialog(ID_ALERT_DIALOG);

			break;

		default:
			break;
		}
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case ID_ALERT_DIALOG:
			TimePickerDialog dialog = new TimePickerDialog(context,
					mOnTimeSetListener, getHour(), getMinute(), IS_24_HOUR_VIEW);

			return dialog;

		default:
			return super.onCreateDialog(id);
		}
	}

	private int getHour() {
		return (IS_24_HOUR_VIEW) ? calendar.get(Calendar.HOUR_OF_DAY)
				: calendar.get(Calendar.HOUR);
	}

	private int getMinute() {
		return calendar.get(Calendar.MINUTE);
	}

	private void setHour(int hour) {
		if (IS_24_HOUR_VIEW)
			calendar.set(Calendar.HOUR_OF_DAY, hour);
		else
			calendar.set(Calendar.HOUR, hour);
	}

	private void setMinute(int minute) {
		calendar.set(Calendar.MINUTE, minute);
	}

	private void updateTime() {
		if (IS_24_HOUR_VIEW)
			textViewTime.setText(getString(R.string.time_24, calendar));
		else
			textViewTime.setText(getString(R.string.time_12, calendar));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private OnTimeSetListener mOnTimeSetListener = new OnTimeSetListener() {

		@Override
		public void onTimeSet(TimePicker view, int hour, int minute) {
			setHour(hour);
			setMinute(minute);

			updateTime();
		}
	};

}
