package ayrat.salavatovich.gmail.com.day_97.preferencesenable;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;

public class PreferenceScreenActivity extends PreferenceActivity {

	private CheckBoxPreference checkBoxPreference3;
	private PreferenceCategory preferenceCategory2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.my_preferences);

		checkBoxPreference3 = (CheckBoxPreference) findPreference(getString(R.string.preference_key_check_box_3));
		checkBoxPreference3
				.setOnPreferenceClickListener(mPreferenceClickListener);

		preferenceCategory2 = (PreferenceCategory) findPreference(getString(R.string.preference_key_category_2));

		setEnabledPreferenceCategory2(checkBoxPreference3.isChecked());
	}

	private OnPreferenceClickListener mPreferenceClickListener = new OnPreferenceClickListener() {

		@Override
		public boolean onPreferenceClick(Preference preference) {
			setEnabledPreferenceCategory2(checkBoxPreference3.isChecked());
			return false;
		}
	};

	private void setEnabledPreferenceCategory2(boolean enabled) {
		preferenceCategory2.setEnabled(enabled);
	}

}
