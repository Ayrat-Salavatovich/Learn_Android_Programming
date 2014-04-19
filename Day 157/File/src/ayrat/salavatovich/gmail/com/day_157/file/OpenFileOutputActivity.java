package ayrat.salavatovich.gmail.com.day_157.file;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OpenFileOutputActivity extends Activity implements OnClickListener {

	private EditText editTextOutputFileName;
	private EditText editTextOutputText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_output_stream);

		editTextOutputFileName = (EditText) findViewById(R.id.editTextOutputFileName);
		editTextOutputText = (EditText) findViewById(R.id.editTextOutputText);
		registerButtonsListener();
	}

	private void registerButtonsListener() {
		((Button) findViewById(R.id.buttonSaveOutputFile))
				.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonSaveOutputFile:
			final String fileName = editTextOutputFileName.getText().toString();
			final String message = editTextOutputText.getText().toString();
			if (write(fileName, message))
				Toast.makeText(
						this,
						getString(R.string.notifications_save_output_file,
								fileName), Toast.LENGTH_SHORT).show();

			break;

		default:
			break;
		}
	}

	public boolean write(final String fileName, final String text) {
		boolean success = false;
		FileOutputStream fos = null;
		try {
			fos = openFileOutput(fileName, Context.MODE_PRIVATE);
			fos.write(text.getBytes());
			success = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException ignore) {
					success = false;
				}
			}
		}

		return success;
	}
}
