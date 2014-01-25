package ayrat.salavatovich.gmail.com.day_99.files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private TextView textViewInfo;
	private final String FILENAME = "file";

	private final String DIR_SDCARD = "my_files";
	private final String FILENAME_SDCARD = "file_on_sd_card";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textViewInfo = (TextView) findViewById(R.id.textViewInfo);
	}

	public void readFile(View v) {
		outputText("");

		BufferedReader bReader = null;
		StringBuilder stringBuilder = new StringBuilder();
		try {
			bReader = new BufferedReader(new InputStreamReader(
					openFileInput(FILENAME)));
			String line = "";
			while ((line = bReader.readLine()) != null) {
				stringBuilder.append(line + System.lineSeparator());
			}
		} catch (FileNotFoundException e) {
			showInfo(R.string.error_file_not_found);
			e.printStackTrace();
		} catch (IOException e) {
			showInfo(R.string.error_read_file);
			e.printStackTrace();
		} finally {
			try {
				if (bReader != null)
					bReader.close();
				outputText(stringBuilder.toString());
			} catch (IOException e) {
				showInfo(R.string.error_read_file);
				e.printStackTrace();
			}
		}
	}

	public void readFileFromSDCard(View v) {
		outputText("");

		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			showInfo(getString(R.string.error_accessible_sd_card,
					Environment.getExternalStorageState()));
			return;
		}

		File dirPath = combine(Environment.getExternalStorageDirectory(),
				DIR_SDCARD);

		File filePath = combine(dirPath, FILENAME_SDCARD);

		BufferedReader bReader = null;
		StringBuilder stringBuilder = new StringBuilder();
		try {
			bReader = new BufferedReader(new FileReader(filePath));
			String line = "";
			while ((line = bReader.readLine()) != null) {
				stringBuilder.append(line + System.lineSeparator());
			}
		} catch (FileNotFoundException e) {
			showInfo(R.string.error_file_not_found);
			e.printStackTrace();
		} catch (IOException e) {
			showInfo(R.string.error_read_file_from_sd_card);
			e.printStackTrace();
		} finally {
			try {
				if (bReader != null)
					bReader.close();
				outputText(stringBuilder.toString());
			} catch (IOException e) {
				showInfo(R.string.error_read_file_from_sd_card);
				e.printStackTrace();
			}
		}
	}

	public void writeFile(View v) {
		outputText("");

		BufferedWriter bWriter = null;
		try {
			bWriter = new BufferedWriter(new OutputStreamWriter(openFileOutput(
					FILENAME, MODE_PRIVATE)));
			bWriter.write(getString(R.string.best_beaches));
		} catch (FileNotFoundException e) {
			showInfo(R.string.error_file_not_found);
			e.printStackTrace();
		} catch (IOException e) {
			showInfo(R.string.error_write_file);
			e.printStackTrace();
		} finally {
			try {
				if (bWriter != null)
					bWriter.close();
				showInfo(R.string.success_write_file);
			} catch (IOException e) {
				showInfo(R.string.error_write_file);
				e.printStackTrace();
			}
		}
	}

	public void writeFileToSDCard(View v) {
		outputText("");

		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			showInfo(getString(R.string.error_accessible_sd_card,
					Environment.getExternalStorageState()));
			return;
		}

		File dirPath = combine(Environment.getExternalStorageDirectory(),
				DIR_SDCARD);

		dirPath.mkdirs();

		File filePath = combine(dirPath, FILENAME_SDCARD);

		BufferedWriter bWriter = null;
		try {
			bWriter = new BufferedWriter(new FileWriter(filePath));
			bWriter.write(getString(R.string.most_beautiful_girls));
		} catch (FileNotFoundException e) {
			showInfo(R.string.error_file_not_found);
			e.printStackTrace();
		} catch (IOException e) {
			showInfo(R.string.error_write_file_to_sd_card);
			e.printStackTrace();
		} finally {
			try {
				if (bWriter != null)
					bWriter.close();
				showInfo(R.string.success_write_file_to_sd_card);
			} catch (IOException e) {
				showInfo(R.string.error_write_file_to_sd_card);
				e.printStackTrace();
			}
		}

	}

	private File combine(File file, String path) {
		return new File(file, path);
	}

	private File combine(String path1, String path2) {
		File file = new File(path1);
		return combine(file, path2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void showInfo(int resId) {
		Toast.makeText(getApplicationContext(), resId, Toast.LENGTH_SHORT)
				.show();
	}

	private void showInfo(String message) {
		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT)
				.show();
	}

	private void outputText(String text) {
		textViewInfo.setText(text);
	}

}
