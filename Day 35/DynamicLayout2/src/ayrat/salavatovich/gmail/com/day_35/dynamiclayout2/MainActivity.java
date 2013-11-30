package ayrat.salavatovich.gmail.com.day_35.dynamiclayout2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioGroup;

public class MainActivity extends Activity {

	private LinearLayout linearLayoutMain;
	private RadioGroup radioGroupGravity;
	private EditText editTextButtonName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		linearLayoutMain = (LinearLayout) findViewById(R.id.linearLayoutMain);
		radioGroupGravity = (RadioGroup) findViewById(R.id.radioGroupGravity);
		editTextButtonName = (EditText) findViewById(R.id.editTextButtonName);
	}

	public void RemoveAll(View v) {
		linearLayoutMain.removeAllViews();
	}

	public void Add(View v) {
		final int WRAP_CONTENT = LayoutParams.WRAP_CONTENT;
		LinearLayout.LayoutParams layoutParamsButton = new LinearLayout.LayoutParams(
				WRAP_CONTENT, WRAP_CONTENT);
		layoutParamsButton.gravity = getSelectedGravity();

		Button newButton = new Button(this);
		newButton.setText(editTextButtonName.getText().toString());

		linearLayoutMain.addView(newButton, layoutParamsButton);
	}

	private int getSelectedGravity() {
		switch (radioGroupGravity.getCheckedRadioButtonId()) {
		case R.id.radioButtonLeft:
			return Gravity.LEFT;

		case R.id.radioButtonCenter:
			return Gravity.CENTER;

		case R.id.radioButtonRight:
			return Gravity.RIGHT;

		default:
			return Gravity.LEFT;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
