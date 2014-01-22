package ayrat.salavatovich.gmail.com.day_74.alertdialogsimple;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	private final int ID_ALERT_DIALOG_EXIT = 0;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		context = this;
	}

	public void onClick(View v) {
		if (v.getId() == R.id.buttonExit)
			showAlertDialog(ID_ALERT_DIALOG_EXIT);
	}

	private void showAlertDialog(final int id) {
		showDialog(id);
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case ID_ALERT_DIALOG_EXIT:
			AlertDialog.Builder aBuilder = new AlertDialog.Builder(context)
					.setTitle(R.string.exit).setMessage(R.string.save_data)
					.setIcon(android.R.drawable.ic_dialog_info)
					.setPositiveButton(R.string.ok, myClickListener)
					.setNegativeButton(R.string.no, myClickListener)
					.setNeutralButton(R.string.cancel, myClickListener);

			return aBuilder.create();

		default:
			return super.onCreateDialog(id);
		}
	}

	OnClickListener myClickListener = new OnClickListener() {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			switch (which) {
			case Dialog.BUTTON_POSITIVE:
				savedData();
				finish();

				break;
			case Dialog.BUTTON_NEGATIVE:
				finish();

				break;
			case Dialog.BUTTON_NEUTRAL:

				break;

			default:
				break;
			}
		}

	};

	private void savedData() {
		Toast.makeText(getApplicationContext(), R.string.saved,
				Toast.LENGTH_LONG).show();
	}

	@Override
	public void onBackPressed() {
		showAlertDialog(ID_ALERT_DIALOG_EXIT);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
