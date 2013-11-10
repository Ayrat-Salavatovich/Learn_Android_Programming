package ayrat.salavatovich.gmail.com.day_16.converter38parrots;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		editText = (EditText) findViewById(R.id.editText);
		textView = (TextView) findViewById(R.id.textView);
		radioButtonConvertFromMeters = (RadioButton) findViewById(R.id.radioButtonConvertFromMeters);
		radioButtonConvertFromParrots = (RadioButton) findViewById(R.id.radioButtonConvertFromParrots);
		radioButtonConvertFromAnacondas = (RadioButton) findViewById(R.id.radioButtonConvertFromAnacondas);
		radioButtonConvertToMeters = (RadioButton) findViewById(R.id.radioButtonConvertToMeters);
		radioButtonConvertToParrots = (RadioButton) findViewById(R.id.radioButtonConvertToParrots);
		radioButtonConvertToAnacondas = (RadioButton) findViewById(R.id.radioButtonConvertToAnacondas);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private double convert() {
		double value = Double.valueOf(editText.getText().toString());
		double result;

		int from = 0, to = 0;
		if (radioButtonConvertFromMeters.isChecked())
			from = METER;
		else if (radioButtonConvertFromParrots.isChecked())
			from = PARROT;
		else if (radioButtonConvertFromAnacondas.isChecked())
			from = ANACONDA;
		if (radioButtonConvertToMeters.isChecked())
			to = METER;
		else if (radioButtonConvertToParrots.isChecked())
			to = PARROT;
		else if (radioButtonConvertToAnacondas.isChecked())
			to = ANACONDA;

		result = value * convert[from] / convert[to];

		return result;
	}

	public void onClickConvertHandler(View v) {
		switch (v.getId()) {
		case R.id.button:
			if (editText.getText().length() == 0) {
				Toast.makeText(getApplicationContext(), R.string.lenght_empty,
						Toast.LENGTH_SHORT).show();
				editText.setError(getString(R.string.lenght_empty));
				editText.requestFocus();
				return;
			}
			textView.setText(getString(R.string.result, convert()));
			break;
		default:
			break;
		}
	}

	private EditText editText;
	private TextView textView;
	private RadioButton radioButtonConvertToMeters;
	private RadioButton radioButtonConvertToParrots;
	private RadioButton radioButtonConvertToAnacondas;
	private RadioButton radioButtonConvertFromMeters;
	private RadioButton radioButtonConvertFromParrots;
	private RadioButton radioButtonConvertFromAnacondas;
	private final int METER = 0;
	private final int PARROT = 1;
	private final int ANACONDA = 2;
	private final double[] convert = { 1, 0.13157894736, 5 };

}
