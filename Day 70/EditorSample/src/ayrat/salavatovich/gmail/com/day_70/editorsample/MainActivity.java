package ayrat.salavatovich.gmail.com.day_70.editorsample;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	private EditText editText;
	private final int MAX_TEXT_SIZE = 72;
	private final int MIN_TEXT_SIZE = 8;
	private final int TEXT_SIZE_STEP = 1;
	private float currentTextSize;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		editText = (EditText) findViewById(R.id.editText);
		currentTextSize = editText.getTextSize();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonN:
			editText.setTypeface(null, Typeface.NORMAL);
			break;
		case R.id.buttonB:
			editText.setTypeface(null, Typeface.BOLD);
			break;
		case R.id.buttonI:
			editText.setTypeface(null, Typeface.ITALIC);
			break;
		case R.id.buttonIncrement:
			currentTextSize = (currentTextSize + TEXT_SIZE_STEP > MAX_TEXT_SIZE) ? currentTextSize
					: currentTextSize + TEXT_SIZE_STEP;
			editText.setTextSize(currentTextSize);
			break;
		case R.id.buttonDecrement:
			currentTextSize = (currentTextSize - TEXT_SIZE_STEP < MIN_TEXT_SIZE) ? currentTextSize
					: currentTextSize - TEXT_SIZE_STEP;
			editText.setTextSize(currentTextSize);
			break;
		default:
			break;
		}
	}

}
