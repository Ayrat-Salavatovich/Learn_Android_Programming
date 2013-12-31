package ayrat.salavatovich.gmail.com.day_74.datepickerdialogsample;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

public class MainActivity extends Activity {

	private Calendar calendar;
	private TextView textViewDate;
	private final int ID_ALERT_DIALOG = 0;
	private final Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textViewDate = (TextView) findViewById(R.id.textViewDate);
		calendar = Calendar.getInstance();
		updateDate();
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

	private void updateDate() {
		textViewDate.setText(getString(R.string.date, calendar));
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case ID_ALERT_DIALOG:
			DatePickerDialog dialog = new DatePickerDialog(context,
					mOnDateSetListener, getYear(), getMonth(), getDay());

			return dialog;

		default:
			return super.onCreateDialog(id);
		}
	}

	private int getYear() {
		return calendar.get(Calendar.YEAR);
	}

	private int getMonth() {
		return calendar.get(Calendar.MONTH);
	}

	private int getDay() {
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	private void setYear(int year) {
		calendar.set(Calendar.YEAR, year);
	}

	private void setMonth(int month) {
		calendar.set(Calendar.MONTH, month);
	}

	private void setDay(int dayOfMonth) {
		calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private OnDateSetListener mOnDateSetListener = new OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int month,
				int dayOfMonth) {
			setYear(year);
			setMonth(month);
			setDay(dayOfMonth);

			updateDate();
		}
	};

}
