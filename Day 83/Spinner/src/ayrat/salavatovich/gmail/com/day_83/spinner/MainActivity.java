package ayrat.salavatovich.gmail.com.day_83.spinner;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {

	private ArrayAdapter<CharSequence> adapter;
	private TextView textViewAnswer;
	private int DEFAULT_ANSWER = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textViewAnswer = (TextView) findViewById(R.id.textViewAnswer);

		adapter = ArrayAdapter.createFromResource(getApplicationContext(),
				R.array.answers, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		Spinner spinner = (Spinner) findViewById(R.id.spinner);
		spinner.setOnItemSelectedListener(myItemSelectedListener);
		spinner.setAdapter(adapter);
		spinner.setSelection(DEFAULT_ANSWER);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private OnItemSelectedListener myItemSelectedListener = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			if (parent.getItemAtPosition(position).toString()
					.equals(getString(R.string.none))) {
				return;
			}

			if (parent.getItemAtPosition(position).toString()
					.equals(getString(R.string.portugal)))
				textViewAnswer.setText(R.string.correct_answer);
			else
				textViewAnswer.setText(R.string.incorrect_answer);
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
		}

	};

}
