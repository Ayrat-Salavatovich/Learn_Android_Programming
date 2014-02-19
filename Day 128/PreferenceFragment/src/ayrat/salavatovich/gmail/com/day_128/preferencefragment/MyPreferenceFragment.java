package ayrat.salavatovich.gmail.com.day_128.preferencefragment;

import android.app.Activity;
import android.os.Bundle;

public class MyPreferenceFragment extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getFragmentManager().beginTransaction()
				.replace(android.R.id.content, new Fragment1()).commit();
	}

}
