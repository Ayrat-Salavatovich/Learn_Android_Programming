package ayrat.salavatovich.gmail.com.day_146.httpurlconnection;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private TextView textView;
	private ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textView = (TextView) findViewById(R.id.textView);
		imageView = (ImageView) findViewById(R.id.imageView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClick(View view) {
		if (isNetworkAvailable()) {
			new DownloadPageTask(new HttpResponseHandler() {

				@Override
				public void onResponse(Object result) {
					textView.setText((String) result);
				}
			}).execute(getString(R.string.site));

			new DownloadImageTask(new HttpResponseHandler() {

				@Override
				public void onResponse(Object result) {
					imageView.setImageBitmap((Bitmap) result);
				}
			}).execute(getString(R.string.url_image));
		}
	}

	public boolean isNetworkAvailable() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = cm.getActiveNetworkInfo();

		if (networkInfo != null && networkInfo.isConnected()) {
			return true;
		}

		return false;
	}

	private class DownloadPageTask extends AsyncTask<String, Void, String> {

		private HttpResponseHandler handler;
		private int result;

		public DownloadPageTask(HttpResponseHandler httpResponseHandler) {
			this.handler = httpResponseHandler;
			result = HttpResponseHandler.ERROR;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			Toast.makeText(MainActivity.this, "loading...", Toast.LENGTH_SHORT)
					.show();
		}

		@Override
		protected String doInBackground(String... urls) {
			String response = "";

			try {
				response = downloadOneUrl(urls[0]);
				result = HttpResponseHandler.SUCCESSFUL;
			} catch (IOException e) {
				e.printStackTrace();
			}

			return response;
		}

		@Override
		protected void onPostExecute(String response) {
			if (result == HttpResponseHandler.ERROR) {
				Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT)
						.show();

				return;
			}

			handler.onResponse(response);
		}
	}

	public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

		private HttpResponseHandler handler;
		private int result;

		public DownloadImageTask(HttpResponseHandler httpResponseHandler) {
			this.handler = httpResponseHandler;
			result = HttpResponseHandler.ERROR;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			Toast.makeText(MainActivity.this, "loading image...",
					Toast.LENGTH_SHORT).show();
		}

		@Override
		protected Bitmap doInBackground(String... urls) {
			Bitmap image = BitmapFactory.decodeResource(getResources(),
					R.drawable.error);

			try {
				image = prepareLoadSrcBitmap(urls[0]);
				result = HttpResponseHandler.SUCCESSFUL;
			} catch (IOException e) {
				e.printStackTrace();
			}

			return image;
		}

		@Override
		protected void onPostExecute(Bitmap image) {
			if (result == HttpResponseHandler.ERROR) {
				Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT)
						.show();

				return;
			}

			handler.onResponse(image);
		}

	}

	private String downloadOneUrl(String myurl) throws IOException {
		InputStream inputStream = null;
		String data = "";
		try {
			URL url = new URL(myurl);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setReadTimeout(100000);
			connection.setConnectTimeout(100000);
			connection.setRequestMethod("GET");
			connection.setInstanceFollowRedirects(true);
			connection.setUseCaches(false);
			connection.setDoInput(true);

			int responseCode = connection.getResponseCode();

			if (responseCode == HttpURLConnection.HTTP_OK) {
				inputStream = connection.getInputStream();

				data = convertInputStreamToString(inputStream);
			} else {
				data = connection.getResponseMessage() + " . Error Code : "
						+ responseCode;
			}
			connection.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeClosable(inputStream);
		}

		return data;
	}

	private String convertInputStreamToString(InputStream inputStream)
			throws IOException {
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));
		String line = "";
		String result = "";
		while ((line = bufferedReader.readLine()) != null)
			result += line;

		return result;
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
			HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();

			inputStream = urlConnection.getInputStream();

			bitmap = BitmapFactory.decodeStream(inputStream, null, options);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeClosable(inputStream);
		}

		return bitmap;
	}

	private synchronized void closeClosable(final Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (final IOException e) {
			}
		}
	}

}
