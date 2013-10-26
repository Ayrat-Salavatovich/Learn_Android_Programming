package ayrat.salavatovich.gmail.com.day_2.temperatureconverter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText editText;
	private RadioButton celsiusRadioButton, fahrenheitRadioButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		editText = (EditText) findViewById(R.id.editText);
		celsiusRadioButton = (RadioButton) findViewById(R.id.celsiusRadioButton);
		fahrenheitRadioButton = (RadioButton) findViewById(R.id.fahrenheitRadioButton);
	}
	
	public void myOnClickHandler(View v) {
		switch (v.getId()) {
		case R.id.calculateButton:
			if (editText.getText().length() == 0) {
				Toast.makeText(this, "Please enter a valid number",
                        Toast.LENGTH_LONG).show();
				return;
			}
			
			float inputValue = Float.valueOf(editText.getText().toString());
			if (celsiusRadioButton.isChecked())
				editText.setText(String.valueOf(convertFahrenheitToCelsius(inputValue)));
			else
				editText.setText(String.valueOf(convertCelsiusToFahrenheit(inputValue)));
			
			switchToOtherRadioButton();
			break;

		default:
			break;
		}
	}
	
	private void switchToOtherRadioButton() {
		if (celsiusRadioButton.isChecked()) {
			celsiusRadioButton.setChecked(false);
			fahrenheitRadioButton.setChecked(true);
		} else {
			fahrenheitRadioButton.setChecked(false);
			celsiusRadioButton.setChecked(true);
		}
	}
	
	private float convertFahrenheitToCelsius(float fahrenheit) {
		return ((fahrenheit - 32) * 5 / 9);
	}
	
	private float convertCelsiusToFahrenheit(float celsius) {
        return ((celsius * 9) / 5) + 32;
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
