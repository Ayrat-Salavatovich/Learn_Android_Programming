package ayrat.salavatovich.gmail.com.day_138.notepad;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends Activity {

	private final static String FILE_NAME = "test.out";
	private EditText editText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		editText = (EditText) findViewById(R.id.editText);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_open:
			openFile(FILE_NAME);

			break;

		case R.id.action_save:
			saveFile(FILE_NAME);

			break;

		case R.id.action_settings:
			Intent intent = new Intent(this, SettingsActivity.class);
			startActivity(intent);

		default:
			break;
		}

		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();
		loadPreferences();
	}

	private void loadPreferences() {
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this);
		if (preferences.getBoolean(getString(R.string.preference_open_mode),
				false)) {
			openFile(FILE_NAME);
		}
		float textSize = Float.parseFloat(preferences.getString(
				getString(R.string.preference_size), "20"));
		editText.setTextSize(textSize);

		String regular = preferences.getString(
				getString(R.string.preference_style),
				getString(R.string.style_regular));
		int typeface = -1;

		if (regular.contains(getString(R.string.style_regular)))
			typeface = Typeface.NORMAL;

		if (regular.contains(getString(R.string.style_bold)))
			typeface += Typeface.BOLD;

		if (regular.contains(getString(R.string.style_bold_italic)))
			typeface += Typeface.ITALIC;

		editText.setTypeface(null, typeface);
	}

	private void saveFile(String fileName) {
		OutputStreamWriter outputWriter = null;
		try {
			OutputStream out = openFileOutput(fileName, 0);
			outputWriter = new OutputStreamWriter(out);
			outputWriter.write(editText.getText().toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (notNull(outputWriter))
				try {
					outputWriter.close();
				} catch (IOException ignore) {
				}
		}
	}

	private void openFile(String fileName) {
		InputStream in = null;
		try {
			in = openFileInput(fileName);
			if (notNull(in)) {
				InputStreamReader inputReader = new InputStreamReader(in);
				BufferedReader reader = new BufferedReader(inputReader);
				StringBuffer buffer = new StringBuffer();
				String line;

				while (notNull(line = reader.readLine())) {
					buffer.append(line);
				}

				editText.setText(buffer.toString());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			if (notNull(in))
				try {
					in.close();
				} catch (IOException ignore) {
				}
		}
	}

	protected boolean notNull(Object obj) {
		return (obj == null ? false : true);
	}

}
