package ayrat.salavatovich.gmail.com.day_145.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class RestClient {

	public static enum RequestMethod {
		DELETE, GET, POST, PUT
	}

	public static Object execute(RequestMethod method, String url)
			throws Exception {
		Object response = null;
		HttpUriRequest request = null;
		switch (method) {
		case GET:
			request = new HttpGet(url);
			response = executeRequest(request);

			break;
		case POST:
			request = new HttpPost(url);
			response = executeRequest(request);

			break;
		case PUT:
			request = new HttpPut(url);
			response = executeRequest(request);

			break;
		case DELETE:
			request = new HttpDelete(url);
			response = executeRequest(request);

			break;
		}

		return response;
	}

	private static Object executeRequest(HttpUriRequest request) {

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpParams params = httpclient.getParams();

		HttpConnectionParams.setConnectionTimeout(params, 30 * 1000);
		HttpConnectionParams.setSoTimeout(params, 30 * 1000);

		HttpResponse httpResponse;
		String result = "";

		try {
			httpResponse = httpclient.execute(request);

			HttpEntity entity = httpResponse.getEntity();

			if (entity != null) {
				InputStream instream = entity.getContent();
				Header contentEncoding = httpResponse
						.getFirstHeader("Content-Encoding");
				if (contentEncoding != null
						&& contentEncoding.getValue().equalsIgnoreCase("gzip")) {
					instream = new GZIPInputStream(instream);
				}
				result = convertStreamToString(instream);

				instream.close();
			}

		} catch (ClientProtocolException ignore) {
		} catch (IOException ignore) {
		}

		return result;
	}

	private static String convertStreamToString(InputStream in) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException ignore) {
			}
		}
		return sb.toString();
	}

}
