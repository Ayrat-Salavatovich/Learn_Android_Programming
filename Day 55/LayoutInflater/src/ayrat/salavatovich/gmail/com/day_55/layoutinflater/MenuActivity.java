package ayrat.salavatovich.gmail.com.day_55.layoutinflater;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class MenuActivity extends Activity {

	public static final String ROOT = "root";
	public static final String ATTACH_TO_ROOT = "attach";

	private SharedPreferences sharedPreferences;
	private CheckBox checkBox_root, checkBox_attachToRoot;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inflater);

		checkBox_root = (CheckBox) findViewById(R.id.checkBox_root);
		checkBox_root.setChecked(load(ROOT));
		checkBox_attachToRoot = (CheckBox) findViewById(R.id.checkBox_attachToRoot);
		checkBox_attachToRoot.setChecked(load(ATTACH_TO_ROOT));
	}

	public void onCheckboxClicked(View view) {
		boolean checked = ((CheckBox) view).isChecked();

		switch (view.getId()) {
		case R.id.checkBox_root:
			if (checked)
				save(ROOT, true);
			else
				save(ROOT, false);
			break;
		case R.id.checkBox_attachToRoot:
			if (checked)
				save(ATTACH_TO_ROOT, true);
			else
				save(ATTACH_TO_ROOT, false);
			break;
		}
	}

	public boolean load(String key) {
		sharedPreferences = getPreferences(MODE_PRIVATE);
		return sharedPreferences.getBoolean(key, false);
	}

	public void save(String key, boolean value) {
		sharedPreferences = getPreferences(MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

}
