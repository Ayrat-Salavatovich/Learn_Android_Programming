package ayrat.salavatovich.gmail.com.day_145.jsonarray;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

public class HttpAcyncTaskLoadImage extends AsyncTask<String, Void, Bitmap> {

	private HttpHandlerForImage httpHandler;

	public HttpAcyncTaskLoadImage(HttpHandlerForImage httpHandler) {
		this.httpHandler = httpHandler;
	}

	@Override
	protected Bitmap doInBackground(String... urls) {
		Bitmap image = null;
		try {
			image = prepareLoadSrcBitmap(urls[0]);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return image;
	}

	@Override
	protected void onPostExecute(Bitmap result) {
		httpHandler.onResponse(result);
	}

	private Bitmap prepareLoadSrcBitmap(String url) throws IOException {
		BitmapFactory.Options options;
		options = new BitmapFactory.Options();
		options.inSampleSize = 1;
		return downloadImageUrl(url, options);
	}

	private Bitmap downloadImageUrl(String strUrl, BitmapFactory.Options options)
			throws IOException {
		Bitmap bitmap = null;
		InputStream inputStream = null;
		try {
			URL url = new URL(strUrl);
			URLConnection urlConnection;
			if (url.getProtocol().toLowerCase().equals("https")) {
				trustAllHosts();

				urlConnection = (HttpsURLConnection) url.openConnection();
			} else {
				urlConnection = (HttpURLConnection) url.openConnection();
			}

			inputStream = urlConnection.getInputStream();

			bitmap = BitmapFactory.decodeStream(inputStream, null, options);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IoUtil.closeCloseable(inputStream);
		}

		return bitmap;
	}

	private static void trustAllHosts() {

		X509TrustManager easyTrustManager = new X509TrustManager() {

			@Override
			public X509Certificate[] getAcceptedIssuers() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void checkServerTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
				// TODO Auto-generated method stub

			}

			@Override
			public void checkClientTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
				// TODO Auto-generated method stub

			}
		};

		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[] { easyTrustManager };

		// Install the all-trusting trust manager
		try {
			SSLContext sc = SSLContext.getInstance("TLS");

			sc.init(null, trustAllCerts, new java.security.SecureRandom());

			HttpsURLConnection
					.setDefaultSSLSocketFactory(sc.getSocketFactory());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
