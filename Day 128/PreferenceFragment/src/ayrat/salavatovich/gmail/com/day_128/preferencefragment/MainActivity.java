package ayrat.salavatovich.gmail.com.day_128.preferencefragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonPreferenceFragment:

			startActivity(new Intent(this, MyPreferenceFragment.class));

			break;

		case R.id.buttonPreferenceActivity:

			startActivity(new Intent(this, MyPreferenceActivity.class));

			break;

		default:
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
