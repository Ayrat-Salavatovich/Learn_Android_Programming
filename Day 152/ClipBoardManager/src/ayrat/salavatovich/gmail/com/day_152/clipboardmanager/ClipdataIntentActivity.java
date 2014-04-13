package ayrat.salavatovich.gmail.com.day_152.clipboardmanager;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class ClipdataIntentActivity extends Activity {
	private EditText editTextClipBoardHtml;
	private EditText editTextClipBoardText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clipdata_intent);
		editTextClipBoardHtml = (EditText) findViewById(R.id.editTextClipBoardHtml);
		editTextClipBoardText = (EditText) findViewById(R.id.editTextClipBoardText);

		// Get the intent that started this activity
		Intent intent = getIntent();
		if (intent != null) {
			ClipData clipdata = intent.getClipData();
			// Make sure clipdata object is not null and it has HTML MIME type.
			if (clipdata != null
					&& clipdata.getDescription().hasMimeType(
							ClipDescription.MIMETYPE_TEXT_HTML)) {

				ClipData.Item item = clipdata.getItemAt(0);
				editTextClipBoardHtml.setText(item.getHtmlText());
				editTextClipBoardText.setText(item.getText());
			} else {
				Toast.makeText(this, "Intent clipdata doesn't have HTML",
						Toast.LENGTH_SHORT).show();
			}
		}
	}
}
