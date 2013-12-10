package ayrat.salavatovich.gmail.com.day_43.intentextras;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ViewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view);

		Intent intent = getIntent();
		String firstName = getFirstNameFormIntent(intent);
		String lastName = getLastNameFormIntent(intent);
		viewName(firstName, lastName);
	}

	private String getFirstNameFormIntent(final Intent intent) {
		return intent.getStringExtra(Name.FIRST_NAME);
	}

	private String getLastNameFormIntent(final Intent intent) {
		return intent.getStringExtra(Name.LAST_NAME);
	}

	private void viewName(String firstName, String lastName) {
		((TextView) findViewById(R.id.textViewView)).setText(getString(
				R.string.view, firstName, lastName));
	}

}
