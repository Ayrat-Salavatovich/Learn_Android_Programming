package ayrat.salavatovich.gmail.com.day_4.passingdata;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonTransmit:
			transmit();
			break;

		default:
			break;
		}
	}

	private void transmit() {
		EditText editTextUserName = (EditText) findViewById(R.id.editTextUserName);
		EditText editDescription = (EditText) findViewById(R.id.editTextDescription);
		Intent intent = new Intent(MainActivity.this, InfoActivity.class);
		intent.putExtra(
				"ayrat.salavatovich.gmail.com.day_4.passingdata.USERNAME",
				editTextUserName.getText().toString());
		intent.putExtra(
				"ayrat.salavatovich.gmail.com.day_4.passingdata.DESCRIPTION",
				editDescription.getText().toString());
		startActivity(intent);
	}

}
