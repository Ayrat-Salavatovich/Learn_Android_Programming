package ayrat.salavatovich.gmail.com.day_152.clipboardmanager;

import android.os.Bundle;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.ClipboardManager.OnPrimaryClipChangedListener;
import android.content.Intent;
import android.text.Html;
import android.text.Spannable;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText editTextCopy;
	private EditText editTextPaste;
	private EditText editTextPasteCoerceText;
	private RadioButton radioButtonText;
	private RadioButton radioButtonHtml;
	private ClipboardManager clipboardManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		initComponents();
	}

	private void initComponents() {
		editTextCopy = (EditText) findViewById(R.id.editTextCopy);
		editTextPaste = (EditText) findViewById(R.id.editTextPaste);
		editTextPasteCoerceText = (EditText) findViewById(R.id.editTextPasteCoerceText);
		editTextCopy.setText(Html.fromHtml(getString(R.string.text_view_html)));
		radioButtonText = (RadioButton) findViewById(R.id.radioButtonText);
		radioButtonHtml = (RadioButton) findViewById(R.id.radioButtonHtml);
		clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
		clipboardManager
				.addPrimaryClipChangedListener(onPrimaryClipChangedListener);
	}

	private OnPrimaryClipChangedListener onPrimaryClipChangedListener = new OnPrimaryClipChangedListener() {

		@Override
		public void onPrimaryClipChanged() {
			Toast.makeText(getApplicationContext(), "Primary clipdata changed",
					Toast.LENGTH_SHORT).show();
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// Нажатие кнопки "Copy"
	public void copyHtml(View view) {
		String htmlText = getHtmltxt(editTextCopy);
		String plainText = getOnlyText(editTextCopy);
		clipboardManager.setPrimaryClip(ClipData.newHtmlText("HTML Text",
				plainText, htmlText));
	}

	// Нажатие кнопки "Paste"
	public void pasteHtml(View view) {
		if (clipboardManager.hasPrimaryClip()
				&& clipboardManager.getPrimaryClipDescription().hasMimeType(
						ClipDescription.MIMETYPE_TEXT_HTML)) {
			// Получем первый элемент из клипа
			ClipData.Item item = clipboardManager.getPrimaryClip().getItemAt(0);

			if (radioButtonHtml.isChecked()) {
				editTextPaste.setText(item.getHtmlText());
			} else {
				editTextPaste.setText(item.getText());
			}
			// Преобразовываем скопированные данные в текст и вставляем
			editTextPasteCoerceText.setText(item.coerceToText(this));
		}
	}

	public void sendHtmlIntent(View view) {
		Intent intent = new Intent(Intent.ACTION_SEND);

		String htmlText = getHtmltxt(editTextCopy);
		String text = getOnlyText(editTextPaste);
		intent.putExtra(Intent.EXTRA_HTML_TEXT, htmlText);
		intent.putExtra(Intent.EXTRA_TEXT, text);
		intent.setType("text/html");
		startActivity(Intent.createChooser(intent, null));
	}

	public void sendClipdataIntent(View view) {
		String htmlText = getHtmltxt(editTextCopy);
		String plainText = getOnlyText(editTextCopy);
		Intent intent = new Intent(this, ClipdataIntentActivity.class);
		// Создаем объект clipdata с HTML-текстом
		// и связываем его с намерением
		intent.setClipData(ClipData.newHtmlText(
				"HTML text in Intent's clipdata", plainText, htmlText));
		startActivity(intent);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// Remove the ClipChanged Listener to save the resources.
		clipboardManager
				.removePrimaryClipChangedListener(onPrimaryClipChangedListener);
	}

	/**
	 * This method gets the EditText object and returns the HTML text. It can be
	 * called only with EditTexts having spannable object with the HTML text.
	 * 
	 * @param editText
	 * @return
	 */
	private String getHtmltxt(EditText editText) {
		// get the spannable object from EditText
		Spannable spannable = (Spannable) editText.getText();
		// return the HTML text from spannable object.
		return Html.toHtml(spannable);
	}

	/**
	 * This method takes the EditText object which has spannable object with
	 * HTML text and returns the simple text without HTML tags.
	 * 
	 * @param editText
	 * @return
	 */
	private String getOnlyText(EditText editText) {
		return editText.getText().toString();
	}

}
