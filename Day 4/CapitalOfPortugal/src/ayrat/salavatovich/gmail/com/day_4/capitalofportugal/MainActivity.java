package ayrat.salavatovich.gmail.com.day_4.capitalofportugal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	static final private int CHOOSE_CAPITAL = 0;

	TextView textViewInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textViewInfo = (TextView) findViewById(R.id.textViewInfo);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClick(View v) {
		Intent intent = new Intent(MainActivity.this, ChooseActivity.class);
		startActivityForResult(intent, CHOOSE_CAPITAL);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == CHOOSE_CAPITAL && resultCode == RESULT_OK)
			if (data.getIntExtra(ChooseActivity.CAPITAL, -1) == R.string.lisbon)
				textViewInfo.setText(R.string.win);
			else
				textViewInfo.setText(R.string.loss);
		else
			textViewInfo.setText("");

	}

}
