package ayrat.salavatovich.gmail.com.day_128.preferencefragment;

import java.util.List;

import android.preference.PreferenceActivity;

public class MyPreferenceActivity extends PreferenceActivity {

	@Override
	public void onBuildHeaders(List<Header> target) {
		loadHeadersFromResource(R.xml.preference_header, target);
	}

}
