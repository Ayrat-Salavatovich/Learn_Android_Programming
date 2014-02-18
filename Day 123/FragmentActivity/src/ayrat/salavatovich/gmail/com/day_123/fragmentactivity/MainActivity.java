package ayrat.salavatovich.gmail.com.day_123.fragmentactivity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity implements OnSomeEventListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Fragment2 fragment2 = new Fragment2();
		FragmentTransaction fragmentTransaction = getFragmentManager()
				.beginTransaction();
		fragmentTransaction.add(R.id.fragment2, fragment2);
		fragmentTransaction.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button:

			Fragment fragment1 = getFragmentManager().findFragmentById(
					R.id.fragment1);
			((TextView) fragment1.getView().findViewById(R.id.textView))
					.setText(R.string.access_to_fragment1);

			Fragment fragment2 = getFragmentManager().findFragmentById(
					R.id.fragment2);
			((TextView) fragment2.getView().findViewById(R.id.textView))
					.setText(R.string.access_to_fragment2);

			break;

		default:
			break;
		}
	}

	@Override
	public void someEvent(int rand) {
		Fragment fragment1 = getFragmentManager().findFragmentById(
				R.id.fragment1);
		((TextView) fragment1.getView().findViewById(R.id.textView))
				.setText(getString(R.string.random, rand));
	}

}
