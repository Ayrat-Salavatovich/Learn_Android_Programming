package ayrat.salavatovich.gmail.com.day_125.actionbarnavigation;

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
		case R.id.buttonTabs:

			startActivity(new Intent(this, TabsActivity.class));

			break;

		case R.id.buttonSpinnerItems:

			startActivity(new Intent(this, SpinnerItemsActivity.class));

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
