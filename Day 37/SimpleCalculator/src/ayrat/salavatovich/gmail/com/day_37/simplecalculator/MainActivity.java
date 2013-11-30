package ayrat.salavatovich.gmail.com.day_37.simplecalculator;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements Calculate {

	EditText editTextNumberDecimal1, editTextNumberDecimal2;

	Button buttonAdd, buttonSub, buttonMul, buttonDiv;

	TextView textViewResult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		editTextNumberDecimal1 = (EditText) findViewById(R.id.editTextNumberDecimal1);
		editTextNumberDecimal2 = (EditText) findViewById(R.id.editTextNumberDecimal2);

		buttonAdd = (Button) findViewById(R.id.buttonAdd);
		buttonSub = (Button) findViewById(R.id.buttonSub);
		buttonMul = (Button) findViewById(R.id.buttonMul);
		buttonDiv = (Button) findViewById(R.id.buttonDiv);

		textViewResult = (TextView) findViewById(R.id.textViewResult);
	}

	public void onClick(View v) {
		if (!isValidEditTexts())
			return;

		switch (v.getId()) {
		case R.id.buttonAdd:
			add();

			break;
		case R.id.buttonSub:
			sub();

			break;
		case R.id.buttonMul:
			mul();

			break;
		case R.id.buttonDiv:
			div();

			break;

		default:
			break;
		}
	}

	public void add() {
		showResult(R.string.add, calculate(R.string.add));
	}

	public void sub() {
		showResult(R.string.sub, calculate(R.string.sub));
	}

	public void mul() {
		showResult(R.string.mul, calculate(R.string.mul));
	}

	public void div() {
		if (getNumberDecimals()[1] == .0f) {
			editTextNumberDecimal2.requestFocus();
			Toast.makeText(this, R.string.warning_attributes,
					Toast.LENGTH_SHORT).show();
			return;
		}

		showResult(R.string.div, calculate(R.string.div));
	}

	private float calculate(final int oper) {
		float num1 = getNumberDecimals()[0];
		float num2 = getNumberDecimals()[1];

		switch (oper) {
		case R.string.add:
			return num1 + num2;

		case R.string.sub:
			return num1 - num2;

		case R.string.mul:
			return num1 * num2;

		case R.string.div:
			return num1 / num2;

		default:
			return .0f;
		}
	}

	private void showResult(final int oper, float result) {
		textViewResult.setText(getString(R.string.result,
				getNumberDecimals()[0], getString(oper),
				getNumberDecimals()[1], result));
	}

	private float[] getNumberDecimals() {
		return new float[] {
				Float.valueOf(editTextNumberDecimal1.getText().toString()),
				Float.valueOf(editTextNumberDecimal2.getText().toString()) };
	}

	private boolean isValidEditTexts() {
		return !isEmpty(editTextNumberDecimal1)
				&& !isEmpty(editTextNumberDecimal2);
	}

	private boolean isEmpty(EditText editText) {
		return TextUtils.isEmpty(editText.getText().toString().trim());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_reset:
			textViewResult.setText("");
			editTextNumberDecimal1.setText("0.0");
			editTextNumberDecimal2.setText("0.0");

			break;
		case R.id.action_quit:
			finish();

			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	final int MENU_RESET_ID = 1;
	final int MENU_QUIT_ID = 2;

}
