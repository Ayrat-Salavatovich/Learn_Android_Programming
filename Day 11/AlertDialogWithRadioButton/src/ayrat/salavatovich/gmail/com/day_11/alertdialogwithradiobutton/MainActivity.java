package ayrat.salavatovich.gmail.com.day_11.alertdialogwithradiobutton;

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

	private AlertDialog.Builder ad;
	private Context context;
	private final int ID_ALERT_DIALOG = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		context = MainActivity.this;
		ad = new AlertDialog.Builder(context);
		ad.setTitle(R.string.what_reading);
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
			final String[] mItems = {
					getResources().getString(R.string.correiomanha),
					getResources().getString(R.string.dn),
					getResources().getString(R.string.expresso),
					getResources().getString(R.string.jn),
					getResources().getString(R.string.publico),
					getResources().getString(R.string.jornaldecoimbra) };
			final String your_choice = getResources().getString(
					R.string.your_choice)
					+ " ";
			ad.setNegativeButton(R.string.back, new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}
			}).setSingleChoiceItems(mItems, -1, new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(context, your_choice + mItems[which],
							Toast.LENGTH_LONG).show();
				}
			}).setCancelable(false);
			return ad.create();
		default:
			return null;
		}
	}

}
