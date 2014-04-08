package ayrat.salavatovich.gmail.com.day_145.jsonarray;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MainActivity extends Activity {

	private final String NEW_LINE = "\n";
	private EditText editTextQuery;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		editTextQuery = (EditText) findViewById(R.id.editTextQuery);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClick(View v) {
		if (isConnected()) {
			new HttpHandler() {
				@Override
				public HttpUriRequest getHttpRequestMethod() {

					return new HttpGet("http://api.duckduckgo.com/?q="
							+ getQuery() + "&format=json&pretty=1");
				}

				@Override
				public void onResponse(String result) {
					outputResponse(result);
				}

			}.execute();
		}
	}

	void outputResponse(String response) {
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(response);
			JSONArray results = jsonObject.getJSONArray("RelatedTopics");
			StringBuilder sb = new StringBuilder();

			sb.append(jsonObject.getString("Heading")).append(": ")
					.append(jsonObject.getString("Definition"))
					.append(NEW_LINE);
			sb.append("URL").append(": ")
					.append(jsonObject.getString("AbstractURL"))
					.append(NEW_LINE);

			((TextView) findViewById(R.id.textViewDescription)).setText(sb
					.toString());

			StringBuilder topics = new StringBuilder();
			ArrayList<JSONObject> arrayJson = new ArrayList<JSONObject>();
			parseTopics(results, topics, arrayJson);

			showTopics(new JSONArray(arrayJson));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	void parseTopics(final JSONArray results, final StringBuilder sb,
			ArrayList<JSONObject> arrayJson) throws JSONException {
		for (int i = 0; i < results.length(); i++) {
			JSONObject arrayElement = results.getJSONObject(i);
			if (arrayElement.has("Topics")) {
				JSONArray array = arrayElement.getJSONArray("Topics");
				parseTopics(array, sb, arrayJson);
			} else {
				JSONObject object = new JSONObject();
				object.put("Text", arrayElement.getString("Text"));
				object.put("URL", arrayElement.getString("FirstURL"));

				arrayJson.add(object);
			}
		}
	}

	void showTopics(JSONArray data) throws JSONException {
		final ListView listView = (ListView) findViewById(R.id.listView);
		ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map;
		for (int i = 0; i < data.length(); i++) {
			JSONObject c = data.getJSONObject(i);

			map = new HashMap<String, String>();
			map.put("Text", c.getString("Text"));
			map.put("URL", c.getString("URL"));
			arrayList.add(map);
		}

		SimpleAdapter simpleAdapter;
		simpleAdapter = new SimpleAdapter(this, arrayList, R.layout.topic,
				new String[] { "Text", "URL" }, new int[] { R.id.textViewText,
						R.id.textViewUrl });
		listView.setAdapter(simpleAdapter);
	}

	public boolean isConnected() {
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

		if (networkInfo != null && networkInfo.isConnected())
			return true;
		else
			return false;
	}

	String getQuery() {
		String query = editTextQuery.getText().toString();
		if (TextUtils.isEmpty(query)) {
			return getString(R.string.query);
		}

		return query;
	}

}
