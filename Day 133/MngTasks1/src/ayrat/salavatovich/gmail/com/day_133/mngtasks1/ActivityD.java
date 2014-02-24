package ayrat.salavatovich.gmail.com.day_133.mngtasks1;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ActivityD extends MainActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_d);

		textViewInfo = (TextView) findViewById(R.id.textViewInfo);

		setTitle(getString(R.string.app_name) + ": " + getLocalClassName());
		activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
	}

	@Override
	public void onClickStart(View v) {
		startActivity(new Intent(this, ActivityE.class));
	}

	public void onClickStartD(View v) {
		startActivity(new Intent(this, ActivityD.class));
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);

		textViewInfo = (TextView) findViewById(R.id.textViewInfo);

		setTitle(getString(R.string.app_name) + ": " + getLocalClassName());
		activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
	}

}
