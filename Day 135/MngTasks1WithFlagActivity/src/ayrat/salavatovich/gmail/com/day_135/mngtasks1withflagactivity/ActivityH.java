package ayrat.salavatovich.gmail.com.day_135.mngtasks1withflagactivity;

import android.app.ActivityManager;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

public class ActivityH extends MainActivity {

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);

		textViewInfo = (TextView) findViewById(R.id.textViewInfo);

		setTitle(getString(R.string.app_name) + ": " + getLocalClassName());
		activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
	}

	@Override
	public void onClickStart(View v) {
		startActivity(new Intent(this, ActivityH.class));
	}

}
