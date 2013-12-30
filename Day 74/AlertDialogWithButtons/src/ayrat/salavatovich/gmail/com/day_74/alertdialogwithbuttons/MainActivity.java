package ayrat.salavatovich.gmail.com.day_74.alertdialogwithbuttons;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	private final int ID_ALERT_DIALOG = 0;
	private final Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button:
			showDialog(ID_ALERT_DIALOG);

			break;

		default:
			break;
		}
	}

	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case ID_ALERT_DIALOG:
			// Android documents suggests to use getApplicationContext();
			// but it will not work instead of that use your current activity
			// while instantiating AlertDialog.Builder or AlertDialog or
			// Dialog...
			//
			// http://stackoverflow.com/questions/2634991/android-1-6-android-view-windowmanagerbadtokenexception-unable-to-add-window
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
					.setMessage(R.string.message)
					.setTitle(R.string.app_name)
					.setIcon(R.drawable.ic_launcher)
					.setPositiveButton(android.R.string.yes,
							new OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.cancel();
									MainActivity.this.finish();
								}
							})
					.setNegativeButton(android.R.string.no,
							new OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.cancel();
								}
							}).setCancelable(false);
			return alertDialog.create();

		default:
			return null;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
