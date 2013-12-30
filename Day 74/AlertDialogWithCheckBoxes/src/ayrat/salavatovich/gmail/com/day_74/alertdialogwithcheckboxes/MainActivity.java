package ayrat.salavatovich.gmail.com.day_74.alertdialogwithcheckboxes;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	private String[] colors = { "Red", "Green", "Blue" };
	private boolean[] selectedColors = { false, true, false };
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
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
					.setTitle(R.string.app_name)
					.setIcon(R.drawable.ic_launcher)
					.setMultiChoiceItems(colors, selectedColors,
							new OnMultiChoiceClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which, boolean isChecked) {
									selectedColors[which] = isChecked;
								}
							})
					.setPositiveButton(android.R.string.yes,
							new OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									StringBuilder states = new StringBuilder();
									for (int i = 0; i < colors.length; i++) {
										String state = (selectedColors[i]) ? getString(R.string.checked)
												: getString(R.string.unchecked);
										states.append(getString(
												R.string.color_state_is,
												colors[i], state));
									}
									Toast.makeText(context, states,
											Toast.LENGTH_LONG).show();
								}
							}).setNegativeButton(android.R.string.cancel, null)
					.setCancelable(false);

			return alertDialog.create();
		default:
			return super.onCreateDialog(id);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
