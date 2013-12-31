package ayrat.salavatovich.gmail.com.day_74.progressdialogsample;

import android.app.ProgressDialog;
import android.os.AsyncTask;

public class ProgressTask extends AsyncTask<String, Void, Boolean> {

	private ProgressDialog dialog;
	private final int SLEEP_TIME = 100;

	public ProgressTask(ProgressDialog dialog) {
		this.dialog = dialog;
	}

	@Override
	protected Boolean doInBackground(String... unused) {
		for (int value = dialog.getProgress(); value <= dialog.getMax(); value++) {
			try {
				Thread.sleep(SLEEP_TIME);
			} catch (InterruptedException ignore) {
			} finally {
				dialog.setProgress(value);
			}
		}

		return true;
	}

	@Override
	protected void onPostExecute(final Boolean success) {
		if (dialog.isShowing())
			dialog.dismiss();
	}

	@Override
	protected void onPreExecute() {
		dialog.show();
	}

}
