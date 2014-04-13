package ayrat.salavatovich.gmail.com.day_152.clipboardmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class HtmlIntentActivity extends Activity {

	private EditText editTextHtml;
	private EditText editTextText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_html_intent);
		editTextHtml = (EditText) findViewById(R.id.editTextHtml);
		editTextText = (EditText) findViewById(R.id.editTextText);

		// Get the intent that started this activity
		Intent intent = getIntent();
		// Make sure intent and its type is not null.
		if (intent != null && intent.getType() != null
				&& intent.getType().equals("text/html")) {
			// This contition will full-fill when this application receive the
			// intent who's type is "test/html". In this application
			// sendHtmlIntent
			// method sends this type of Intent.
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				editTextHtml.setText(bundle
						.getCharSequence(Intent.EXTRA_HTML_TEXT));
				editTextText.setText(bundle.getCharSequence(Intent.EXTRA_TEXT));
			} else {
				Toast.makeText(this, "Intent clipdata doesn't have HTML",
						Toast.LENGTH_SHORT).show();
			}
		}
	}
}
