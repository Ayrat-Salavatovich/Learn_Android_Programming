package ayrat.salavatovich.gmail.com.day_48.sharedpreferences;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private EditText editText;
	private TextView textViewLog;
	private SharedPreferences sharedPreferences;
	private final String KEY = "ayrat.salavatovich.gmail.com.day_48.sharedpreferences.TEXT";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		editText = (EditText) findViewById(R.id.editText);
		textViewLog = (TextView) findViewById(R.id.textViewLog);

		load();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void save(View v) {
		save();
	}

	public void load(View v) {
		load();
	}

	private void log(String info) {
		textViewLog.setText(info);
	}

	@Override
	protected void onDestroy() {
		save();
		super.onDestroy();
	}

	public void load() {
		sharedPreferences = getPreferences(MODE_PRIVATE);
		editText.setText(sharedPreferences.getString(KEY, ""));
		log(getString(R.string.text_loaded));
	}

	public void save() {
		sharedPreferences = getPreferences(MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.putString(KEY, editText.getText().toString());
		editor.commit();
		log(getString(R.string.text_saved));
	}

}
