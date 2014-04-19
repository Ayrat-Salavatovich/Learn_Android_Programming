package ayrat.salavatovich.gmail.com.day_157.file;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class DeleteFileActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		showFilesList();
		this.getListView().setOnItemLongClickListener(onItemLongClickListener);
	}

	void showFilesList() {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, fileList());
		setListAdapter(adapter);
	}

	private OnItemLongClickListener onItemLongClickListener = new OnItemLongClickListener() {

		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View v,
				int position, long id) {
			String fileName = (String) parent.getItemAtPosition(position);
			if (deleteFile(fileName)) {
				Toast.makeText(
						DeleteFileActivity.this,
						getString(R.string.notifications_delete_output_file,
								fileName), Toast.LENGTH_LONG).show();
				showFilesList();
			}
			return true;
		}

	};
}
