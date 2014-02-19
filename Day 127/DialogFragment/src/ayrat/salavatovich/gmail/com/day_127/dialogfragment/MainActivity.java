package ayrat.salavatovich.gmail.com.day_127.dialogfragment;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	private DialogFragment dialog1;
	private DialogFragment dialog2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		dialog1 = new Dialog1();
		dialog2 = new Dialog2();
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonDialog1:

			dialog1.show(getFragmentManager(), getString(R.string.dialog_1));

			break;

		case R.id.buttonDialog2:

			dialog2.show(getFragmentManager(), getString(R.string.dialog_2));

			break;

		default:
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
