package ayrat.salavatovich.gmail.com.day_43.intentextras;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	EditText editTextFirstName, editTextLastName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		editTextFirstName = (EditText) findViewById(R.id.editTextFirstName);
		editTextLastName = (EditText) findViewById(R.id.editTextLastName);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void submit(View v) {
		Intent intent = new Intent(MainActivity.this, ViewActivity.class);
		intent.putExtra(Name.FIRST_NAME, getFirstName());
		intent.putExtra(Name.LAST_NAME, getLastName());

		startActivity(intent);
	}

	private String getFirstName() {
		return editTextFirstName.getText().toString();
	}

	private String getLastName() {
		return editTextLastName.getText().toString();
	}

}
