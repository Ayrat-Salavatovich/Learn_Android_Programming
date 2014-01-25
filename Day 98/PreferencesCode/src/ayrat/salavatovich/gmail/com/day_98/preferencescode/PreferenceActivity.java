package ayrat.salavatovich.gmail.com.day_98.preferencescode;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;

public class PreferenceActivity extends android.preference.PreferenceActivity {

	private CheckBoxPreference checkBoxPreference3;
	private PreferenceCategory preferenceCategory2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		PreferenceScreen preferenceScreen = getPreferenceManager()
				.createPreferenceScreen(this);
		setPreferenceScreen(preferenceScreen);

		CheckBoxPreference checkBoxPreference1 = new CheckBoxPreference(this);
		checkBoxPreference1
				.setKey(getString(R.string.preference_key_check_box_1));
		checkBoxPreference1
				.setSummaryOn(R.string.preference_summary_on_check_box_1);
		checkBoxPreference1
				.setSummaryOff(R.string.preference_summary_off_check_box_1);
		checkBoxPreference1.setTitle(R.string.preference_title_check_box_1);

		preferenceScreen.addPreference(checkBoxPreference1);

		ListPreference listPreference = new ListPreference(this);
		listPreference.setKey(getString(R.string.preference_key_list));
		listPreference.setSummary(R.string.preference_summary_list);
		listPreference.setTitle(R.string.preference_title_list);
		listPreference.setEntries(R.array.entries);
		listPreference.setEntryValues(R.array.entry_values);

		preferenceScreen.addPreference(listPreference);

		CheckBoxPreference checkBoxPreference2 = new CheckBoxPreference(this);
		checkBoxPreference2
				.setKey(getString(R.string.preference_key_check_box_2));
		checkBoxPreference2.setSummary(R.string.preference_summary_check_box_2);
		checkBoxPreference2.setTitle(R.string.preference_title_check_box_2);

		preferenceScreen.addPreference(checkBoxPreference2);

		PreferenceScreen screen = getPreferenceManager()
				.createPreferenceScreen(this);
		screen.setKey(getString(R.string.preference_key_screen));
		screen.setSummary(R.string.preference_summary_screen);
		screen.setTitle(R.string.preference_title_screen);

		PreferenceCategory preferenceCategory1 = new PreferenceCategory(this);
		preferenceCategory1
				.setKey(getString(R.string.preference_key_category_1));
		preferenceCategory1.setSummary(R.string.preference_summary_category_1);
		preferenceCategory1.setTitle(R.string.preference_title_category_1);

		screen.addPreference(preferenceCategory1);

		checkBoxPreference3 = new CheckBoxPreference(this);
		checkBoxPreference3
				.setKey(getString(R.string.preference_key_check_box_3));
		checkBoxPreference3.setSummary(R.string.preference_summary_check_box_3);
		checkBoxPreference3.setTitle(R.string.preference_title_check_box_3);

		preferenceCategory1.addPreference(checkBoxPreference3);

		CheckBoxPreference checkBoxPreference4 = new CheckBoxPreference(this);
		checkBoxPreference4
				.setKey(getString(R.string.preference_key_check_box_4));
		checkBoxPreference4.setSummary(R.string.preference_summary_check_box_4);
		checkBoxPreference4.setTitle(R.string.preference_title_check_box_4);

		preferenceCategory1.addPreference(checkBoxPreference4);

		preferenceCategory2 = new PreferenceCategory(this);
		preferenceCategory2
				.setKey(getString(R.string.preference_key_category_2));
		preferenceCategory2.setSummary(R.string.preference_summary_category_2);
		preferenceCategory2.setTitle(R.string.preference_title_category_2);

		screen.addPreference(preferenceCategory2);

		CheckBoxPreference checkBoxPreference5 = new CheckBoxPreference(this);
		checkBoxPreference5
				.setKey(getString(R.string.preference_key_check_box_5));
		checkBoxPreference5.setSummary(R.string.preference_summary_check_box_5);
		checkBoxPreference5.setTitle(R.string.preference_title_check_box_5);

		preferenceCategory2.addPreference(checkBoxPreference5);

		CheckBoxPreference checkBoxPreference6 = new CheckBoxPreference(this);
		checkBoxPreference6
				.setKey(getString(R.string.preference_key_check_box_6));
		checkBoxPreference6.setSummary(R.string.preference_summary_check_box_6);
		checkBoxPreference6.setTitle(R.string.preference_title_check_box_6);

		preferenceCategory2.addPreference(checkBoxPreference6);

		preferenceScreen.addPreference(screen);

		listPreference
				.setDependency(getString(R.string.preference_key_check_box_1));
		screen.setDependency(getString(R.string.preference_key_check_box_2));

		checkBoxPreference3
				.setOnPreferenceClickListener(mPreferenceClickListener);

		updateEnabledPreferenceCategory2();
	}

	private void updateEnabledPreferenceCategory2() {
		setEnabledPreferenceCategory2(isEnabledCheckBoxPreference3());
	}

	private boolean isEnabledCheckBoxPreference3() {
		return checkBoxPreference3.isEnabled();
	}

	private void setEnabledPreferenceCategory2(boolean enabled) {
		preferenceCategory2.setEnabled(enabled);
	}

	private OnPreferenceClickListener mPreferenceClickListener = new OnPreferenceClickListener() {

		@Override
		public boolean onPreferenceClick(Preference preference) {
			updateEnabledPreferenceCategory2();
			return false;
		}
	};

}
