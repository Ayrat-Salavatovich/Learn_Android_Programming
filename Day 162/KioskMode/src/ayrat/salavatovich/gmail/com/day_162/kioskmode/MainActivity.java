package ayrat.salavatovich.gmail.com.day_162.kioskmode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity extends Activity {

	public static final String URL = "http://www.portugal.com";
	private final List<Integer> blockedKeys = new ArrayList<Integer>(
			Arrays.asList(KeyEvent.KEYCODE_VOLUME_DOWN,
					KeyEvent.KEYCODE_VOLUME_UP));

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (isKeyBlocked(event.getKeyCode()))
			return true;

		return super.dispatchKeyEvent(event);
	}

	boolean isKeyBlocked(int keyCode) {
		if (blockedKeys.contains(keyCode))
			return true;

		return false;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return keyCode == KeyEvent.KEYCODE_HOME
				|| keyCode == KeyEvent.KEYCODE_BACK;
	}

	@Override
	public void onAttachedToWindow() {
		if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.GINGERBREAD_MR1)
			getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD);
		super.onAttachedToWindow();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setting();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	void setting() {
		fullScreen();
		keepScreenOn();
		showWhenLocked();
	}

	@Override
	public void onBackPressed() {
		((WebView) getFragmentManager().findFragmentById(R.id.container)
				.getView().findViewById(R.id.webView)).loadUrl(URL);
	}

	void fullScreen() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}

	void keepScreenOn() {
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}

	void showWhenLocked() {
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		private WebView webView;

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			webView = (WebView) rootView.findViewById(R.id.webView);
			settingWebView(webView);
			return rootView;
		}

		void settingWebView(final WebView webView) {
			WebSettings webSettings = webView.getSettings();
			loadURL(URL);
			enableJavaScript(webSettings);
		}

		void enableJavaScript(final WebSettings webSettings) {
			webView.setWebViewClient(new KioskWebViewClient());
			webSettings.setJavaScriptEnabled(true);
		}

		void loadURL(String url) {
			webView.loadUrl(URL);
		}

	}

}
