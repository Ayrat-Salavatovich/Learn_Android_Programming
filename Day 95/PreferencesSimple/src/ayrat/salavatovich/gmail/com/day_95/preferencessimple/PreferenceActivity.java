package ayrat.salavatovich.gmail.com.day_95.preferencessimple;

import android.os.Bundle;

public class PreferenceActivity extends android.preference.PreferenceActivity {

	private static final int PREFS = R.xml.my_preferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(PREFS);
	}

}
