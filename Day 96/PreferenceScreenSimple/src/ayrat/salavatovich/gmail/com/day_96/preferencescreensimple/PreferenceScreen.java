package ayrat.salavatovich.gmail.com.day_96.preferencescreensimple;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class PreferenceScreen extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.my_preferences);
	}

}
