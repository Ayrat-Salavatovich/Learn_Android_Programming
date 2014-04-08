package ayrat.salavatovich.gmail.com.day_145.jsonarray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

public class HttpAsyncTask extends AsyncTask<String, Void, String> {

	private HttpHandler httpHandler;

	public HttpAsyncTask(HttpHandler httpHandler) {
		this.httpHandler = httpHandler;
	}

	@Override
	protected String doInBackground(String... params) {
		InputStream inputStream = null;
		String result = "";
		try {
			HttpClient httpclient = new DefaultHttpClient();

			HttpResponse httpResponse = httpclient.execute(httpHandler
					.getHttpRequestMethod());

			inputStream = httpResponse.getEntity().getContent();
			Header contentEncoding = httpResponse
					.getFirstHeader("Content-Encoding");
			if (contentEncoding != null
					&& contentEncoding.getValue().equalsIgnoreCase("gzip")) {
				inputStream = new GZIPInputStream(inputStream);
			}
			result = convertInputStreamToString(inputStream);

		} catch (Exception ignore) {
		}

		return result;
	}

	@Override
	protected void onPostExecute(String result) {
		httpHandler.onResponse(result);
	}

	private static String convertInputStreamToString(InputStream inputStream)
			throws IOException {
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));
		String line = "";
		String result = "";
		while ((line = bufferedReader.readLine()) != null)
			result += line;

		inputStream.close();
		return result;
	}
}
