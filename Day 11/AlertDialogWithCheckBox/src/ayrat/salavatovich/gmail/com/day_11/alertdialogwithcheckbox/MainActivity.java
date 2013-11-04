package ayrat.salavatovich.gmail.com.day_11.alertdialogwithcheckbox;

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

	private AlertDialog.Builder ad;
	private Context context;
	private final int ID_ALERT_DIALOG = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		context = MainActivity.this;
		ad = new AlertDialog.Builder(context);
		ad.setTitle(R.string.question);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClick(View v) {
		showDialog(ID_ALERT_DIALOG);
	}

	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case ID_ALERT_DIALOG:
			final boolean[] mCheckedItems = { true, true, true, true, true,
					true, true, true };
			final String[] mItems = {
					getResources()
							.getString(
									R.string.commonwealth_of_portuguese_speaking_countries),
					getResources().getString(R.string.eu),
					getResources().getString(R.string.euro_area),
					getResources().getString(R.string.ibero_american_summit),
					getResources().getString(R.string.nato),
					getResources().getString(R.string.oecd),
					getResources().getString(R.string.schengen_area),
					getResources().getString(R.string.united_nations) };

			ad.setNegativeButton(R.string.back, new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}
			})
					.setMultiChoiceItems(mItems, mCheckedItems,
							new OnMultiChoiceClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which, boolean isChecked) {
									mCheckedItems[which] = isChecked;
								}
							})
					.setPositiveButton(R.string.ok, new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							StringBuilder sb = new StringBuilder();
							for (int i = 0; i < mItems.length; i++) {
								sb.append(mItems[i]).append(" ");
								if (mCheckedItems[i])
									sb.append(
											getResources().getString(
													R.string.select))
											.append(". ")
											.append(getResources().getString(
													R.string.right));
								else
									sb.append(
											getResources().getString(
													R.string.not_select))
											.append(". ")
											.append(getResources().getString(
													R.string.mistake));
								sb.append("\n");
							}
							Toast.makeText(context, sb.toString(),
									Toast.LENGTH_LONG).show();
						}
					}).setCancelable(false);
			return ad.create();
		default:
			return null;
		}
	}

}
