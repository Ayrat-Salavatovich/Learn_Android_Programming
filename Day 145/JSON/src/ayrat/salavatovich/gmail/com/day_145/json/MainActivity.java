package ayrat.salavatovich.gmail.com.day_145.json;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView textViewWeatherLisbon;
	private final String NEW_LINE = "\n";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		disableStrictMode();

		textViewWeatherLisbon = (TextView) findViewById(R.id.textViewWeatherLisbon);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClick(View v) {
		StringBuilder sb = new StringBuilder();
		try {
			String response = (String) RestClient
					.execute(RestClient.RequestMethod.GET,
							"http://api.openweathermap.org/data/2.5/weather?q=Lisboa,pt");
			JSONObject jsonObject = new JSONObject(response);
			sb.append(jsonObject.toString()).append(NEW_LINE);
			String cityName = jsonObject.getString("name");
			sb.append("Город: ").append(cityName).append(NEW_LINE);
			JSONObject mainJsonObject = jsonObject.getJSONObject("main");
			double temp = mainJsonObject.getDouble("temp");
			sb.append("Температура: ")
					.append(Temperature.kelvinToCelsius(temp)).append(NEW_LINE);
			int humidity = mainJsonObject.getInt("humidity");
			sb.append("Влажность: ").append(humidity).append("%")
					.append(NEW_LINE);
			double minTemp = mainJsonObject.getDouble("temp_min");
			double maxTemp = mainJsonObject.getDouble("temp_max");
			sb.append("Температура от: ")
					.append(Temperature.kelvinToCelsius(minTemp))
					.append(" до ")
					.append(Temperature.kelvinToCelsius(maxTemp))
					.append(NEW_LINE);
			double pressure = mainJsonObject.getDouble("pressure");
			sb.append("Атмосферное давление: ")
					.append(Pressure.paToMMHg(pressure)).append("мм рт.ст")
					.append(NEW_LINE);
			JSONObject windJsonObject = jsonObject.getJSONObject("wind");
			sb.append("Скорость ветра: ")
					.append(windJsonObject.getDouble("speed")).append("м/c")
					.append(NEW_LINE);
			JSONObject cloudsJsonObject = jsonObject.getJSONObject("clouds");
			sb.append("Облачность: ").append(cloudsJsonObject.getInt("all"))
					.append("%").append(NEW_LINE);
			textViewWeatherLisbon.setText(sb.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Fix NetworkOnMainThreadException by disabling strict mode
	private void disableStrictMode() {
		try {
			Class<?> strictModeClass = Class.forName("android.os.StrictMode",
					true, Thread.currentThread().getContextClassLoader());
			Class<?> threadPolicyClass = Class.forName(
					"android.os.StrictMode$ThreadPolicy", true, Thread
							.currentThread().getContextClassLoader());
			Class<?> threadPolicyBuilderClass = Class.forName(
					"android.os.StrictMode$ThreadPolicy$Builder", true, Thread
							.currentThread().getContextClassLoader());

			Method setThreadPolicyMethod = strictModeClass.getMethod(
					"setThreadPolicy", threadPolicyClass);

			Method detectAllMethod = threadPolicyBuilderClass
					.getMethod("detectAll");
			Method penaltyMethod = threadPolicyBuilderClass
					.getMethod("penaltyLog");
			Method buildMethod = threadPolicyBuilderClass.getMethod("build");

			Constructor<?> threadPolicyBuilderConstructor = threadPolicyBuilderClass
					.getConstructor();
			Object threadPolicyBuilderObject = threadPolicyBuilderConstructor
					.newInstance();

			Object obj = detectAllMethod.invoke(threadPolicyBuilderObject);

			obj = penaltyMethod.invoke(obj);
			Object threadPolicyObject = buildMethod.invoke(obj);
			setThreadPolicyMethod.invoke(strictModeClass, threadPolicyObject);
		} catch (Exception ignore) {
		}
	}

}
