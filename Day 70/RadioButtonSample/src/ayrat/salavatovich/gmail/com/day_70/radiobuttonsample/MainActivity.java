package ayrat.salavatovich.gmail.com.day_70.radiobuttonsample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textView = (TextView) findViewById(R.id.textView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.radioButtonMode1:
		case R.id.radioButtonMode2:
		case R.id.radioButtonMode3:
			show((Button) v);
			break;
		default:
			break;
		}
	}

	private void show(Button btn) {
		textView.setText(getString(R.string.select_mode, btn.getText()
				.toString()));
	}

}
