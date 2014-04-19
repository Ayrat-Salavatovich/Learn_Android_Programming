package ayrat.salavatovich.gmail.com.day_157.file;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		registerButtonsListener();
	}

	private void registerButtonsListener() {
		((Button) findViewById(R.id.buttonShowGetDirActivity))
				.setOnClickListener(this);
		((Button) findViewById(R.id.buttonShowOpenFileOutputActivity))
				.setOnClickListener(this);
		((Button) findViewById(R.id.buttonShowGetFilesListActivity))
				.setOnClickListener(this);
		((Button) findViewById(R.id.buttonShowOpenFileInputActivity))
				.setOnClickListener(this);
		((Button) findViewById(R.id.buttonShowDeleteFileActivity))
				.setOnClickListener(this);
		((Button) findViewById(R.id.buttonShowGetCacheDirActivity))
				.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonShowGetDirActivity:
			startActivity(new Intent(this, GetDirActivity.class));

			break;

		case R.id.buttonShowOpenFileOutputActivity:
			startActivity(new Intent(this, OpenFileOutputActivity.class));

			break;

		case R.id.buttonShowGetFilesListActivity:
			startActivity(new Intent(this, GetFilesListActivity.class));

			break;

		case R.id.buttonShowOpenFileInputActivity:
			startActivity(new Intent(this, OpenFileInputActivity.class));

			break;

		case R.id.buttonShowDeleteFileActivity:
			startActivity(new Intent(this, DeleteFileActivity.class));

			break;

		case R.id.buttonShowGetCacheDirActivity:
			startActivity(new Intent(this, GetCacheDirActivity.class));

			break;

		default:
			break;
		}
	}

}
