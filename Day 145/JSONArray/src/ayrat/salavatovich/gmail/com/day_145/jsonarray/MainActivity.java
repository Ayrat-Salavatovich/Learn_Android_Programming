package ayrat.salavatovich.gmail.com.day_145.jsonarray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MainActivity extends Activity {

	private final String NEW_LINE = "\n";
	private EditText editTextQuery;
	private Map<String, Bitmap> icons;
	private MyCustomAdapter adapter;
	private ArrayList<HashMap<String, String>> arrayList;
	private List<String> urlIcons;

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

					return new HttpGet(
							getString(R.string.query_url, getQuery()));
				}

				@Override
				public void onResponse(String result) {
					icons = new HashMap<String, Bitmap>();
					urlIcons = new ArrayList<String>();

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

			loadImages(urlIcons);
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
				if (arrayElement.has("Icon")
						&& !TextUtils.isEmpty(arrayElement
								.getJSONObject("Icon").getString("URL"))) {
					String url = arrayElement.getJSONObject("Icon").getString(
							"URL");
					object.put("Icon", url);
					urlIcons.add(url);
				} else {
					object.put("Icon", "");
				}

				arrayJson.add(object);
			}

		}
	}

	void showTopics(JSONArray data) throws JSONException {
		final ListView listView = (ListView) findViewById(R.id.listView);
		arrayList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map;
		for (int i = 0; i < data.length(); i++) {
			JSONObject c = data.getJSONObject(i);

			map = new HashMap<String, String>();
			map.put("Text", c.getString("Text"));
			map.put("URL", c.getString("URL"));
			map.put("Icon", c.getString("Icon"));
			arrayList.add(map);
		}

		adapter = new MyCustomAdapter(this, arrayList, R.layout.topic,
				new String[] { "Text", "URL", "Icon" },
				new int[] { R.id.textViewText, R.id.textViewUrl,
						R.id.imageViewIcon });
		listView.setAdapter(adapter);
	}

	public boolean isConnected() {
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

		if (networkInfo != null && networkInfo.isConnected())
			return true;
		else
			return false;
	}

	public class MyCustomAdapter extends SimpleAdapter {

		public MyCustomAdapter(Context context,
				List<? extends Map<String, ?>> data, int resource,
				String[] from, int[] to) {
			super(context, data, resource, from, to);
		}

		@Override
		public void setViewImage(ImageView v, String value) {
			if (!TextUtils.isEmpty(value))
				v.setImageBitmap(icons.get(value));
		}

		@Override
		public void setViewText(TextView v, String text) {
			super.setViewText(v, text);
		}

	}

	String getQuery() {
		String query = editTextQuery.getText().toString();
		if (TextUtils.isEmpty(query)) {
			return getString(R.string.query);
		}

		return query;
	}

	private void loadImages(List<String> urls) {
		if (isConnected()) {
			for (final String url : urls) {
				new HttpHandlerForImage() {

					@Override
					public void onResponse(Bitmap result) {
						icons.put(url, result);

						if (adapter != null)
							adapter.notifyDataSetChanged();
					}
				}.execute(url);
			}
		}
	}

}
