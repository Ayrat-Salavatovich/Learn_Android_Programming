package ayrat.salavatovich.gmail.com.day_159.fileexplorer;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private List<String> item = null;
	private List<String> path = null;
	private final String ROOT_PATH = File.separator;
	private TextView textViewPath;
	private ListView listViewListFiles;
	private Comparator<? super File> comparator;
	private String currentPath;
	private boolean onlyImages = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textViewPath = (TextView) findViewById(R.id.textViewPath);
		listViewListFiles = (ListView) findViewById(R.id.listViewListFiles);
		listViewListFiles.setOnItemClickListener(onListItemClick);

		comparator = filecomparatorByAlphabetically;
		currentPath = Environment.getExternalStorageDirectory().getPath();

		getDir(currentPath);
	}

	void showCurrentPath(String path) {
		textViewPath.setText(getString(R.string.text_view_current_path, path));
	}

	private void cleanItemAndPath() {
		item = new ArrayList<String>();
		path = new ArrayList<String>();
	}

	private boolean isRoot(String dirPath) {
		return (dirPath.equals(ROOT_PATH)) ? true : false;
	}

	private void addItem(String path) {
		item.add(path);
	}

	private void addPath(String path) {
		this.path.add(path);
	}

	private void addItem(File file) {
		if (file.isDirectory())
			item.add(file.getName() + "/");
		else
			item.add(file.getName());
	}

	private void addPath(File file) {
		this.path.add(file.getPath());
	}

	private void addItemAndPath(String itemPath, String pathPath) {
		addItem(itemPath);
		addPath(pathPath);
	}

	private void addItemAndPath(File itemFile, File pathFile) {
		addItem(itemFile);
		addPath(pathFile);
	}

	private File[] getListFiles(File file) {
		File files[];
		if (onlyImages)
			files = file.listFiles(new FileFilter() {

				@Override
				public boolean accept(File file) {
					return isImage(file);
				}
			});
		else
			files = file.listFiles();

		return files;
	}

	private void formListFiles(String dirPath) {
		File file = new File(dirPath);
		if (!isRoot(dirPath)) {
			addItemAndPath(ROOT_PATH, ROOT_PATH);
			addItemAndPath("../", file.getParent());
		}

		File[] files = getListFiles(file);
		Arrays.sort(files, comparator);

		for (File f : files) {
			if (!f.isHidden() && f.canRead())
				addItemAndPath(f, f);
		}
	}

	private void displayListFiles() {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.row, item);
		listViewListFiles.setAdapter(adapter);
	}

	private void getDir(String dirPath) {
		cleanItemAndPath();
		showCurrentPath(dirPath);
		formListFiles(dirPath);
		displayListFiles();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private OnItemClickListener onListItemClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			File file = new File(path.get(position));

			if (file.isDirectory()) {
				if (file.canRead()) {
					currentPath = path.get(position);
					getDir(currentPath);
				} else {
					new AlertDialog()
							.setTitle(
									getString(R.string.notification_title_sorry))
							.setMessage(
									getString(
											R.string.notification_message_not_available_read,
											file.getName()))
							.show(getFragmentManager(), "NOT_AVAILABLE_READ");
				}
			} else {
				String exifAttribute = "";
				if (isImage(file)) {
					try {
						ExifInterface exif = new ExifInterface(file.toString());
						exifAttribute = getExif(exif);
					} catch (IOException ignore) {
					}
				}
				new AlertDialog()
						.setTitle(
								getString(R.string.notification_title_info,
										file.getName()))
						.setMessage(
								getString(
										R.string.notification_message_file_info,
										file.getAbsolutePath(), file.getPath(),
										file.getParent(), file.getName(),
										new Date(file.lastModified()),
										exifAttribute))
						.show(getFragmentManager(), "INFO_FILE");
			}
		}
	};

	public boolean isImage(File file) {
		if (file == null || !file.exists() || file.isDirectory()) {
			return false;
		}
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(file.getPath(), options);
		return options.outWidth != -1 && options.outHeight != -1;
	}

	private String getExif(ExifInterface exif) {
		StringBuilder exifAttribute = new StringBuilder();
		String tags[] = { ExifInterface.TAG_DATETIME, ExifInterface.TAG_FLASH,
				ExifInterface.TAG_GPS_LATITUDE,
				ExifInterface.TAG_GPS_LATITUDE_REF,
				ExifInterface.TAG_GPS_LONGITUDE,
				ExifInterface.TAG_GPS_LONGITUDE_REF,
				ExifInterface.TAG_IMAGE_LENGTH, ExifInterface.TAG_IMAGE_WIDTH,
				ExifInterface.TAG_MAKE, ExifInterface.TAG_MODEL,
				ExifInterface.TAG_ORIENTATION, ExifInterface.TAG_WHITE_BALANCE };

		for (String tag : tags)
			exifAttribute.append(getTagString(tag, exif));

		return exifAttribute.toString();
	}

	private String getTagString(String tag, ExifInterface exif) {
		return (tag + " : " + exif.getAttribute(tag) + "\n");
	}

	Comparator<? super File> filecomparatorByAlphabetically = new Comparator<File>() {
		public int compare(File file1, File file2) {
			if (file1.isDirectory()) {
				if (file2.isDirectory()) {
					return String.valueOf(file1.getName().toLowerCase())
							.compareTo(file2.getName().toLowerCase());
				} else {
					return -1;
				}
			} else {
				if (file2.isDirectory()) {
					return 1;
				} else {
					return String.valueOf(file1.getName().toLowerCase())
							.compareTo(file2.getName().toLowerCase());
				}
			}
		}
	};

	Comparator<? super File> filecomparatorByLastDateModified = new Comparator<File>() {
		public int compare(File file1, File file2) {
			if (file1.isDirectory()) {
				if (file2.isDirectory()) {
					return Long.valueOf(file1.lastModified()).compareTo(
							file2.lastModified());
				} else {
					return -1;
				}
			} else {
				if (file2.isDirectory()) {
					return 1;
				} else {
					return Long.valueOf(file1.lastModified()).compareTo(
							file2.lastModified());
				}
			}
		}
	};

	public void onAlphabeticallyClick(View v) {
		comparator = filecomparatorByAlphabetically;
		getDir(currentPath);
	}

	public void onLastDateModifiedClick(View v) {
		comparator = filecomparatorByLastDateModified;
		getDir(currentPath);
	}

	public void onNoFilterClick(View v) {
		onlyImages = false;
		getDir(currentPath);
	}

	public void onImagesFilterClick(View v) {
		onlyImages = true;
		getDir(currentPath);
	}
}
