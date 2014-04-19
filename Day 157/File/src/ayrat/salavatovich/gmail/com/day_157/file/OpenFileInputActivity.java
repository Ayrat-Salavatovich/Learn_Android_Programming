package ayrat.salavatovich.gmail.com.day_157.file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class OpenFileInputActivity extends Activity implements OnClickListener {

	private TextView textViewInputFileName;
	private EditText editTextInputFileText;
	private ListView listViewFilesList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_input_stream);

		textViewInputFileName = (TextView) findViewById(R.id.textViewInputFileName);
		editTextInputFileText = (EditText) findViewById(R.id.editTextInputFileText);
		listViewFilesList = (ListView) findViewById(R.id.listViewFilesList);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, fileList());
		listViewFilesList.setAdapter(adapter);
		listViewFilesList.setOnItemClickListener(onItemClickListener);

		registerButtonsListener();
	}

	private void registerButtonsListener() {
		((Button) findViewById(R.id.buttonSaveInputFile))
				.setOnClickListener(this);
	}

	private void displayFile(final String fileName, final String context) {
		textViewInputFileName.setText(fileName);
		editTextInputFileText.setText(context);
	}

	private void openFile(final String fileName) {
		FileInputStream fis = null;
		StringBuilder sb = new StringBuilder();
		try {
			fis = openFileInput(fileName);
			InputStreamReader inputStreamReader = new InputStreamReader(fis);
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line).append(System.getProperty("line.separator"));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (fis != null) {
			try {
				fis.close();
			} catch (IOException ignore) {
			}
		}

		displayFile(fileName, sb.toString());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonSaveInputFile:
			final String fileName = textViewInputFileName.getText().toString();
			final String message = editTextInputFileText.getText().toString();
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

	private OnItemClickListener onItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			String fileName = (String) parent.getItemAtPosition(position);
			openFile(fileName);
		}
	};

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
