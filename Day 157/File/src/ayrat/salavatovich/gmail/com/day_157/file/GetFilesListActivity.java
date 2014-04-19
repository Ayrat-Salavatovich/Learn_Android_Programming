package ayrat.salavatovich.gmail.com.day_157.file;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class GetFilesListActivity extends Activity implements OnClickListener {

	private ListView listViewFilesList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_files_list);

		listViewFilesList = (ListView) findViewById(R.id.listViewFilesList);
		registerButtonsListener();
	}

	private void registerButtonsListener() {
		((Button) findViewById(R.id.buttonGetFilesList))
				.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonGetFilesList:
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, fileList());
			listViewFilesList.setAdapter(adapter);

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
