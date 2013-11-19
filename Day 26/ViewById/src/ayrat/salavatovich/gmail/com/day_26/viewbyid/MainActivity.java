package ayrat.salavatovich.gmail.com.day_26.viewbyid;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		TextView textViewMyText = (TextView) findViewById(R.id.textViewMyText);
		textViewMyText.setText(R.string.hello_portugal);

		Button buttonMyBtn = (Button) findViewById(R.id.buttonMyBtn);
		buttonMyBtn.setText(R.string.my_button);
		buttonMyBtn.setEnabled(false);

		CheckBox checkBoxMyChb = (CheckBox) findViewById(R.id.checkBoxMyChb);
		checkBoxMyChb.setChecked(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
