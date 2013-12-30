package ayrat.salavatovich.gmail.com.day_74.alertdialogwithlist;

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

	private final int ID_ALERT_DIALOG = 0;
	private final Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
					.setItems(R.array.colors, new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							Toast.makeText(
									context,
									getString(
											R.string.color_is,
											getResources().getStringArray(
													R.array.colors)[which]),
									Toast.LENGTH_SHORT).show();
						}
					}).setTitle(R.string.app_name)
					.setIcon(R.drawable.ic_launcher).setCancelable(false);
			return alertDialog.create();

		default:
			return super.onCreateDialog(id);
		}
	}

}
