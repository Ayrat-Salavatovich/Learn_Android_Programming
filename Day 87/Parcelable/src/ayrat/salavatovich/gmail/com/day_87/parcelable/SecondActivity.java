package ayrat.salavatovich.gmail.com.day_87.parcelable;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SecondActivity extends Activity {

	public static final String TAG = SecondActivity.class.getCanonicalName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);

		LogUtils.d(TAG, "getParcelableExtra");

		MyObject myObject = (MyObject) getIntent().getParcelableExtra(
				MyObject.TAG);

		LogUtils.d(TAG, "myObject: %s, %d", myObject.str, myObject.i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
