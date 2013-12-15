package ayrat.salavatovich.gmail.com.day_46.simpleintents;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void launchBrowser(View v) {
		Intent intent = new Intent(Intent.ACTION_VIEW,
				Uri.parse("http://www.visitportugal.com"));
		startActivity(intent);
	}

	public void launchMaps(View v) {
		Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
				Uri.parse("geo:38.7666667,-9.1508496"));
		startActivity(intent);
	}

	public void launchPhoneDialer(View v) {
		Intent intent = new Intent(Intent.ACTION_DIAL);
		intent.setData(Uri.parse("tel:+351"));
		startActivity(intent);
	}

}
