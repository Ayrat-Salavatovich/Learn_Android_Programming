package ayrat.salavatovich.gmail.com.day_157.file;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GetCacheDirActivity extends Activity implements OnClickListener {

	private TextView textViewCacheDir;
	private TextView textViewExternalCacheDir;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_cache_dir);

		textViewCacheDir = (TextView) findViewById(R.id.textViewCacheDir);
		textViewExternalCacheDir = (TextView) findViewById(R.id.textViewExternalCacheDir);
		registerButtonsListener();
	}

	private void registerButtonsListener() {
		((Button) findViewById(R.id.buttonGetCacheDir))
				.setOnClickListener(this);
		((Button) findViewById(R.id.buttonGetExternalCacheDir))
				.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonGetCacheDir:
			textViewCacheDir.setText(getCacheDir().toString());

			break;

		case R.id.buttonGetExternalCacheDir:
			textViewExternalCacheDir.setText(getExternalCacheDir().toString());

			break;

		default:
			break;
		}
	}

}
