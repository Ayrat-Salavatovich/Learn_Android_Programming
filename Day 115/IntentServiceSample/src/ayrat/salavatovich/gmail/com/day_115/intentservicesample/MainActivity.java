package ayrat.salavatovich.gmail.com.day_115.intentservicesample;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {

	private final int REQUEST_CODE = 1;
	private OperationObject operation;
	private TextView textViewResult;
	private EditText editTextNumber1, editTextNumber2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textViewResult = (TextView) findViewById(R.id.textViewResult);

		editTextNumber1 = (EditText) findViewById(R.id.editTextNumber1);
		editTextNumber2 = (EditText) findViewById(R.id.editTextNumber2);

		Context context = this;
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				context, R.array.operations,
				android.R.layout.simple_spinner_item);
		// Указываем какой layout использовать для прорисовки пунктов
		// выпадающего списка.
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		Spinner spinnerOperator = (Spinner) findViewById(R.id.spinnerOperator);
		spinnerOperator.setOnItemSelectedListener(itemSelectedListener);
		spinnerOperator.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonEqual:

			calculate();

			break;

		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK)
			if (requestCode == REQUEST_CODE) {
				double result = data.getDoubleExtra(getString(R.string.result),
						.0);
				showResult(String.valueOf(result));
			}
	}

	private void calculate() {
		PendingIntent pendingIntent = createPendingResult(REQUEST_CODE,
				new Intent(), 0);

		Intent service = new Intent(this, MyService.class);
		service.putExtra(getString(R.string.operation), operation);
		service.putExtra(getString(R.string.number_1), getNumber1());
		service.putExtra(getString(R.string.number_2), getNumber2());
		service.putExtra(getString(R.string.pending_intent), pendingIntent);
		startService(service);
	}

	private OnItemSelectedListener itemSelectedListener = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {

			operation = new OperationObject(parent.getItemAtPosition(position)
					.toString());
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {

		}

	};

	private void showResult(String text) {
		textViewResult.setText(text);
	}

	private double getNumber1() {
		return Double.valueOf(editTextNumber1.getText().toString());
	}

	private double getNumber2() {
		return Double.valueOf(editTextNumber2.getText().toString());
	}

}
