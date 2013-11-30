package ayrat.salavatovich.gmail.com.day_33.contextmenu;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView textViewColor, textViewSize;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textViewColor = (TextView) findViewById(R.id.textViewColor);
		textViewSize = (TextView) findViewById(R.id.textViewSize);
		resetTextViewProp();
		registerContextMenuForTextView();
	}

	public void resetTextViewProp() {
		textViewColor.setText(R.string.text_color);
		textViewColor.setTextSize(26);
		textViewColor.setTextColor(Color.BLACK);
		textViewSize.setText(R.string.text_size);
		textViewSize.setTextSize(22);
		textViewSize.setTextColor(Color.BLACK);
	}

	private void registerContextMenuForTextView() {
		registerForContextMenu(textViewColor);
		registerForContextMenu(textViewSize);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		switch (v.getId()) {
		case R.id.textViewColor:
			getMenuInflater().inflate(R.menu.color, menu);

			break;
		case R.id.textViewSize:
			getMenuInflater().inflate(R.menu.size, menu);

			break;
		default:
			break;
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.blue:
			changeTextColor(Color.BLUE);

			break;
		case R.id.green:
			changeTextColor(Color.GREEN);

			break;
		case R.id.red:
			changeTextColor(Color.RED);

			break;
		case R.id.size_22:
			changeTextSize(22);

			break;
		case R.id.size_26:
			changeTextSize(26);

			break;
		case R.id.size_30:
			changeTextSize(30);

			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void changeTextColor(final int color) {
		switch (color) {
		case Color.BLUE:
			textViewColor.setTextColor(color);
			textViewColor.setText(getString(R.string.info_color,
					getString(R.string.blue)));

			break;
		case Color.GREEN:
			textViewColor.setTextColor(color);
			textViewColor.setText(getString(R.string.info_color,
					getString(R.string.green)));

			break;
		case Color.RED:
			textViewColor.setTextColor(color);
			textViewColor.setText(getString(R.string.info_color,
					getString(R.string.red)));

			break;

		default:
			break;
		}
	}

	private void changeTextSize(final int size) {
		switch (size) {
		case 22:
			textViewSize.setTextSize(size);
			textViewSize.setText(getString(R.string.info_size,
					getString(R.string.size_22)));

			break;
		case 26:
			textViewSize.setTextSize(size);
			textViewSize.setText(getString(R.string.info_size,
					getString(R.string.size_26)));

			break;
		case 30:
			textViewSize.setTextSize(size);
			textViewSize.setText(getString(R.string.info_size,
					getString(R.string.size_30)));

			break;

		default:
			break;
		}
	}

}
