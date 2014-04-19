package ayrat.salavatovich.gmail.com.day_157.file;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GetDirActivity extends Activity implements OnClickListener {

	private TextView textViewDir;
	private TextView textViewFileDir;
	private EditText editTextDir;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_dir);

		textViewDir = (TextView) findViewById(R.id.textViewDir);
		textViewFileDir = (TextView) findViewById(R.id.textViewFileDir);
		editTextDir = (EditText) findViewById(R.id.editTextDir);
		registerButtonsListener();
	}

	private void registerButtonsListener() {
		((Button) findViewById(R.id.buttonGetFileDir)).setOnClickListener(this);
		((Button) findViewById(R.id.buttonGetDir)).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonGetFileDir:
			textViewFileDir.setText(getFilesDir().toString());

			break;

		case R.id.buttonGetDir:
			final String appName = editTextDir.getText().toString();
			textViewDir.setText(getDir(appName, MODE_PRIVATE).toString());

			break;

		default:
			break;
		}
	}

}
