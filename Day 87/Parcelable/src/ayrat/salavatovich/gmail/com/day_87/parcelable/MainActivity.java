package ayrat.salavatovich.gmail.com.day_87.parcelable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	public static final String TAG = MainActivity.class.getCanonicalName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void onClick(View v) {
		MyObject myObject = new MyObject("Portugal", 868);
		Intent intent = new Intent(this, SecondActivity.class);
		intent.putExtra(MyObject.TAG, myObject);
		LogUtils.d(TAG, "startActivity");
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
