package ayrat.salavatovich.gmail.com.day_44.simpleactivityresult;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	TextView textViewName;
	Button buttonName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textViewName = (TextView) findViewById(R.id.textViewName);

		buttonName = (Button) findViewById(R.id.buttonName);
		buttonName.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(MainActivity.this, NameActivity.class);
		startActivityForResult(intent, REQUEST_CODE_NAME_ACTIVITY);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case REQUEST_CODE_NAME_ACTIVITY:
			if (data == null || resultCode != RESULT_OK)
				return;
			outName(data.getStringExtra(Name.NAME));
			break;

		default:
			super.onActivityResult(requestCode, resultCode, data);
		}
	}

	private void outName(String name) {
		textViewName.setText(getString(R.string.out_name, name));
	}

	public static final int REQUEST_CODE_NAME_ACTIVITY = 1;

}
