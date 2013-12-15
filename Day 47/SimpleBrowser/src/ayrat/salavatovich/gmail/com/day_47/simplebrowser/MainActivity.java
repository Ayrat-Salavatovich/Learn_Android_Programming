package ayrat.salavatovich.gmail.com.day_47.simplebrowser;

import android.app.Activity;
import android.content.Context;
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
		openBrowser(this, getString(R.string.google));
	}

	private void openBrowser(final Context context, String urlString) {
		if (!urlString.startsWith(HTTP) && !urlString.startsWith(HTTPS))
			urlString = HTTP + urlString;
		Intent target = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
		context.startActivity(Intent.createChooser(target,
				context.getString(R.string.choose_browser)));
	}

	private final String HTTPS = "https://";
	private final String HTTP = "http://";

}
