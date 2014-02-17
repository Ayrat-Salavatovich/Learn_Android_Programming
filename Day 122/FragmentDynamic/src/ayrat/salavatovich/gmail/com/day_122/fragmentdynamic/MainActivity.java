package ayrat.salavatovich.gmail.com.day_122.fragmentdynamic;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;

public class MainActivity extends Activity {

	private Fragment1 fragment1;
	private Fragment2 fragment2;
	private FragmentTransaction fragmentTransaction;
	private CheckBox checkBoxBackStack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		fragment1 = new Fragment1();
		fragment2 = new Fragment2();

		checkBoxBackStack = (CheckBox) findViewById(R.id.checkBoxBackStack);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClick(View v) {
		// FragmentManager является основным объектом для работы с фрагментами
		fragmentTransaction = getFragmentManager().beginTransaction();

		switch (v.getId()) {
		case R.id.buttonAdd:

			fragmentTransaction.add(R.id.frameLayoutContainer, fragment1);

			break;

		case R.id.buttonRemove:

			fragmentTransaction.remove(fragment1);

			break;

		case R.id.buttonReplace:

			fragmentTransaction.replace(R.id.frameLayoutContainer, fragment2);

			break;

		default:
			break;
		}

		if (checkBoxBackStack.isChecked())
			fragmentTransaction.addToBackStack(null);

		fragmentTransaction.commit();
	}

}
