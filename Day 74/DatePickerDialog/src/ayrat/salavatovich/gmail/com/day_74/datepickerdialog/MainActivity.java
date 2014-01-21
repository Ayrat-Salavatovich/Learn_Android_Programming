package ayrat.salavatovich.gmail.com.day_74.datepickerdialog;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Bundle;
import android.text.format.Time;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

public class MainActivity extends Activity {

	private final int ID_DIALOG_DATE = 0;
	private int day;
	private int month;
	private int year;
	private final int TEXT_VIEW_ID = R.id.textView;
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textView = (TextView) findViewById(TEXT_VIEW_ID);
		Time today = new Time(Time.getCurrentTimezone());
		today.setToNow();

		setDay(today.monthDay);
		setMonth(today.month);
		setYear(today.year);
	}

	private int getYear() {
		return year;
	}

	private int getMonth() {
		return month;
	}

	private int getDay() {
		return day;
	}

	private void setYear(int year) {
		this.year = year;
	}

	private void setMonth(int month) {
		this.month = month;
	}

	private void setDay(int day) {
		this.day = day;
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case TEXT_VIEW_ID:
			showDialog(ID_DIALOG_DATE);

			break;

		default:
			break;
		}
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case ID_DIALOG_DATE:
			DatePickerDialog dpd = new DatePickerDialog(this,
					myDateSetListener, getYear(), getMonth(), getDay());

			return dpd;

		default:
			return super.onCreateDialog(id);
		}
	}

	private OnDateSetListener myDateSetListener = new OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int month, int day) {
			setYear(year);
			setMonth(month);
			setDay(day);

			outputDate();
		}
	};

	private void outputDate() {
		textView.setText(getString(R.string.date, getDay(), getMonth() + 1,
				getYear()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
