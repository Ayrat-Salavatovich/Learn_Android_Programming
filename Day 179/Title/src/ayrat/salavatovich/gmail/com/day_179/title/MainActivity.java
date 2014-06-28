package ayrat.salavatovich.gmail.com.day_179.title;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	private EditText editTextTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		init();
	}

	private void init() {
		setContentView(R.layout.main);
		editTextTitle = (EditText) findViewById(R.id.editTextTitle);
	}

	public void onClick(View v) {
		setTitle(editTextTitle.getText().toString());
	}
}
