package ayrat.salavatovich.gmail.com.day_124.actionbaritems;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private boolean hidden;
	private Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		button = (Button) findViewById(R.id.button);
		setHiddenActionBar(hidden);
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	public void onClick(View v) {
		if (v == button) {
			hidden = !hidden;
			setHiddenActionBar(hidden);
		}
	}

	private void setHiddenActionBar(boolean hidden) {
		if (hidden) {
			getActionBar().hide();
			button.setText(R.string.show_action_bar);
		} else {
			getActionBar().show();
			button.setText(R.string.hide_action_bar);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:

			Toast.makeText(this, R.string.home, Toast.LENGTH_SHORT).show();

			break;
		case R.id.item_1:

			Toast.makeText(this, R.string.item_1, Toast.LENGTH_SHORT).show();

			break;
		case R.id.item_2:

			Toast.makeText(this, R.string.item_2, Toast.LENGTH_SHORT).show();

			break;
		case R.id.item_3:

			Toast.makeText(this, R.string.item_3, Toast.LENGTH_SHORT).show();

			break;
		case R.id.item_4:

			Toast.makeText(this, R.string.item_4, Toast.LENGTH_SHORT).show();

			break;

		default:
			return super.onOptionsItemSelected(item);
		}

		return true;
	}

}
