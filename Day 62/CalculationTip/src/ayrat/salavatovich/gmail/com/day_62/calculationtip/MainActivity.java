package ayrat.salavatovich.gmail.com.day_62.calculationtip;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView textViewTip5, textViewResult5, textViewTip10,
			textViewResult10, textViewTip15, textViewResult15, textViewTip20,
			textViewResult20, textViewTip25, textViewResult25,
			textViewTipCustom, textViewResultCustom, textViewLabelCustom;
	private EditText editTextAmount;
	private SeekBar seekBarCustomTip;
	private final int PERCENT_5 = 5;
	private final int PERCENT_10 = 10;
	private final int PERCENT_15 = 15;
	private final int PERCENT_20 = 20;
	private final int PERCENT_25 = 25;
	private double currentAmount;
	private int currentCustomPercent;
	private final static String AMOUNT = "ayrat.salavatovich.gmail.com.day_62.calculationtip.AMOUNT";
	private final static String CUSTOM_PERCENT = "ayrat.salavatovich.gmail.com.day_62.calculationtip.CUSTOM_PERCENT";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		if (savedInstanceState == null) {
			currentAmount = Double.valueOf(getString(R.string.default_amount));
			currentCustomPercent = Integer
					.valueOf(getString(R.string.default_percent));
		} else {
			currentAmount = savedInstanceState.getDouble(AMOUNT);
			currentCustomPercent = savedInstanceState.getInt(CUSTOM_PERCENT);
		}

		textViewTip5 = (TextView) findViewById(R.id.textViewTip5);
		textViewResult5 = (TextView) findViewById(R.id.textViewResult5);
		textViewTip10 = (TextView) findViewById(R.id.textViewTip10);
		textViewResult10 = (TextView) findViewById(R.id.textViewResult10);
		textViewTip15 = (TextView) findViewById(R.id.textViewTip15);
		textViewResult15 = (TextView) findViewById(R.id.textViewResult15);
		textViewTip20 = (TextView) findViewById(R.id.textViewTip20);
		textViewResult20 = (TextView) findViewById(R.id.textViewResult20);
		textViewTip25 = (TextView) findViewById(R.id.textViewTip25);
		textViewResult25 = (TextView) findViewById(R.id.textViewResult25);
		textViewTipCustom = (TextView) findViewById(R.id.textViewTipCustom);
		textViewResultCustom = (TextView) findViewById(R.id.textViewResultCustom);
		editTextAmount = (EditText) findViewById(R.id.editTextAmount);
		seekBarCustomTip = (SeekBar) findViewById(R.id.seekBarCustomTip);
		seekBarCustomTip
				.setOnSeekBarChangeListener(seekBarCustomTipChangeListener);
		textViewLabelCustom = (TextView) findViewById(R.id.textViewLabelCustom);
		showPercent(getCurrentCustomPercent());
		editTextAmount.addTextChangedListener(editTextAmountTextWatcher);
		showAmount(getCurrentAmount());

		update(getCurrentAmount(), getCurrentCustomPercent());
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		outState.putDouble(AMOUNT, getCurrentAmount());
		outState.putInt(CUSTOM_PERCENT, getCurrentCustomPercent());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void update(double amount, int customPercent) {
		updateTip(amount, customPercent);
		updateResult(amount, customPercent);
		updateLabelCustomPercent(customPercent);
	}

	private void updateTip(double amount, int customPercent) {
		textViewTip5.setText(formatted(calculateTip(amount, PERCENT_5)));
		textViewTip10.setText(formatted(calculateTip(amount, PERCENT_10)));
		textViewTip15.setText(formatted(calculateTip(amount, PERCENT_15)));
		textViewTip20.setText(formatted(calculateTip(amount, PERCENT_20)));
		textViewTip25.setText(formatted(calculateTip(amount, PERCENT_25)));
		updateCustomTip(amount, customPercent);
	}

	private void updateCustomTip(double amount, int percent) {
		textViewTipCustom.setText(formatted(calculateTip(amount, percent)));
	}

	private void updateResult(double amount, int customPercent) {
		textViewResult5.setText(formatted(calculateResult(amount,
				calculateTip(amount, PERCENT_5))));
		textViewResult10.setText(formatted(calculateResult(amount,
				calculateTip(amount, PERCENT_10))));
		textViewResult15.setText(formatted(calculateResult(amount,
				calculateTip(amount, PERCENT_15))));
		textViewResult20.setText(formatted(calculateResult(amount,
				calculateTip(amount, PERCENT_20))));
		textViewResult25.setText(formatted(calculateResult(amount,
				calculateTip(amount, PERCENT_25))));
		updateCustomResult(amount, calculateTip(amount, customPercent));
	}

	private void updateCustomResult(double amount, double tip) {
		textViewResultCustom.setText(formatted(calculateResult(amount, tip)));
	}

	private double calculateTip(double amount, int percent) {
		return amount * percent / 100;
	}

	private double calculateResult(double amount, double tip) {
		return amount + tip;
	}

	private String formatted(double result) {
		return getString(R.string.result_value, result);
	}

	private double getCurrentAmount() {
		return currentAmount;
	}

	private void showAmount(double amount) {
		editTextAmount.setText(String.valueOf(getCurrentAmount()));
	}

	private void showPercent(int percent) {
		seekBarCustomTip.setProgress(percent);
	}

	private void updateLabelCustomPercent(int customPercent) {
		textViewLabelCustom.setText(getString(R.string.label_percent,
				customPercent));
	}

	private int getCurrentCustomPercent() {
		return currentCustomPercent;
	}

	private OnSeekBarChangeListener seekBarCustomTipChangeListener = new OnSeekBarChangeListener() {

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {

		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {

		}

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			currentCustomPercent = progress;
			updateLabelCustomPercent(currentCustomPercent);
			updateCustomTip(getCurrentAmount(), currentCustomPercent);
			updateCustomResult(getCurrentAmount(),
					calculateTip(getCurrentAmount(), currentCustomPercent));
		}
	};

	private TextWatcher editTextAmountTextWatcher = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			try {
				currentAmount = Double.valueOf(editTextAmount.getText()
						.toString());
			} catch (NumberFormatException ignore) {
				showAmount(getCurrentAmount());
			}
			update(currentAmount, getCurrentCustomPercent());
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {

		}

		@Override
		public void afterTextChanged(Editable s) {

		}
	};
}
