package ayrat.salavatovich.gmail.com.day_128.preferencefragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class Fragment1 extends PreferenceFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Берет файл preference_1.xml и по нему создает экран настроек
		addPreferencesFromResource(R.xml.preference_1);
	}

}
