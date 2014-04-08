package ayrat.salavatovich.gmail.com.day_145.jsonarray;

import org.apache.http.client.methods.HttpUriRequest;

public abstract class HttpHandler {

	public abstract HttpUriRequest getHttpRequestMethod();

	public abstract void onResponse(String result);

	public void execute() {
		new HttpAsyncTask(this).execute();
	}
}