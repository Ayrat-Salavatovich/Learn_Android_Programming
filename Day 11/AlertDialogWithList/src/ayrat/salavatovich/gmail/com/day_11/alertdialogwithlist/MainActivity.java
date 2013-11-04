package ayrat.salavatovich.gmail.com.day_11.alertdialogwithlist;

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
		ad.setTitle(R.string.select);
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
					getResources().getString(R.string.lisbon),
					getResources().getString(R.string.algarve),
					getResources().getString(R.string.madeira) };
			final String your_choice = getResources().getString(
					R.string.your_choice)
					+ " ";
			ad.setItems(mItems, new OnClickListener() {

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
