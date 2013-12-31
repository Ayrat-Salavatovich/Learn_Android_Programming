package ayrat.salavatovich.gmail.com.day_74.progressdialogsample;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	private Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void onClick(View v) {
		if (v.getId() == R.id.button) {
			ProgressDialog mProgressDialog = new ProgressDialog(context);
			mProgressDialog.setMessage(getString(R.string.message));
			mProgressDialog.setIndeterminate(false);
			mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			mProgressDialog.setCancelable(false);

			new ProgressTask(mProgressDialog).execute();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
