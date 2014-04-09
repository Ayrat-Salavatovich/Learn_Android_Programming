package ayrat.salavatovich.gmail.com.day_146.httpurlconnection;

public interface HttpResponseHandler {

	final static int SUCCESSFUL = 200;
	final static int ERROR = -1;

	void onResponse(Object result);

}
