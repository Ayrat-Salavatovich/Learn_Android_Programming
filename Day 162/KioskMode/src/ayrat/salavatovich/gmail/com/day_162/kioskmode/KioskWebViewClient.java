package ayrat.salavatovich.gmail.com.day_162.kioskmode;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class KioskWebViewClient extends WebViewClient {

	public final String HOST = "portugal.com";

	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		if (url.contains(HOST))
			view.loadUrl(url);

		return true;
	}

}
