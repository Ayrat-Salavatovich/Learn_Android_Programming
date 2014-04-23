package ayrat.salavatovich.gmail.com.day_165.zipfile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends Activity {

	static final String FILE_NAME = "out";
	static final String ZIP_NAME = "out.zip";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		if (isNull(savedInstanceState))
			getFragmentManager().beginTransaction()
					.add(R.id.frameLayoutMain, new PlaceholderFragment())
					.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public static class PlaceholderFragment extends Fragment {
		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.main_fragment, container,
					false);

			return rootView;
		}
	}

	public static boolean isNull(Object obj) {
		if (obj == null)
			return true;

		return false;
	}

	public void onClickDecompressFileFromZip(View v) {
		final String FILE_ZIP = getDirPath() + "/" + ZIP_NAME;

		Utils.decompress(FILE_ZIP, getDirPath());
	}

	public void onClickCompressFileToZip(View v) {
		File file = openFile(FILE_NAME);
		if (!isNull(file)) {
			final String FILE_ZIP = getDirPath() + "/" + ZIP_NAME;
			Utils.compress(file.getAbsolutePath(), FILE_ZIP);
			delete(file);
		}
	}

	String getDirPath() {
		if (isExternalStorageWritable()) {
			String extStorageDirectory = Environment
					.getExternalStorageDirectory().toString();
			return extStorageDirectory + "/TEMP";
		} else
			return getCacheDir().getAbsolutePath();
	}

	public void onClickClean(View v) {
		if (isExternalStorageWritable())
			delete(new File(getDirPath()));
		else {
			delete(new File(getDirPath() + "/" + FILE_NAME));
			delete(new File(getDirPath() + "/" + ZIP_NAME));
		}
	}

	public void onClickCreateFile(View v) {
		writeFile(FILE_NAME, "");
	}

	File openFile(String fileName) {
		File file = new File(getDirPath(), fileName);

		if (file != null && file.exists() && !file.isDirectory())
			return file;

		return null;
	}

	private void delete(File file) {
		if (file.isDirectory()) {
			String fileList[] = file.list();
			if (fileList.length == 0) {
				file.delete();
			} else {
				int size = fileList.length;
				for (int i = 0; i < size; i++) {
					String fileName = fileList[i];
					String fullPath = file.getPath() + "/" + fileName;
					File fileOrFolder = new File(fullPath);
					delete(fileOrFolder);
				}
			}
		} else {
			file.delete();
		}
	}

	File createFile(String fileName) throws IOException {
		File file = openFile(fileName);
		if (!isNull(file))
			return file;

		if (isExternalStorageWritable()) {
			File root = new File(getDirPath());
			if (!root.exists())
				root.mkdirs();
			file = new File(root, fileName);
		} else
			file = File.createTempFile(fileName, null, new File(getDirPath()));

		return file;
	}

	boolean isExternalStorageWritable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state))
			return true;

		return false;
	}

	public boolean writeFile(String fileName, String context) {
		boolean success = false;
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		try {
			File file = createFile(fileName);

			fos = new FileOutputStream(file);
			osw = new OutputStreamWriter(fos);
			osw.write(context);
			osw.flush();
			osw.close();
			success = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return success;
	}

}
