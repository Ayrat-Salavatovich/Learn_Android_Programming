package ayrat.salavatovich.gmail.com.day_74.customdialogsample;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private final int ID_ALERT_DIALOG = 0;
	private final Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void onClick(View btn) {
		if (btn.getId() == R.id.button)
			showDialog(ID_ALERT_DIALOG);
	}

	@Override
	protected Dialog onCreateDialog(final int id) {
		if (id == ID_ALERT_DIALOG) {
			LayoutInflater layoutInflater = getLayoutInflater();
			View viewRoot = layoutInflater
					.inflate(
							R.layout.custom_dialog,
							(ViewGroup) findViewById(R.id.customDialogRootLinearLayout));
			TextView customDialogTextView = (TextView) viewRoot
					.findViewById(R.id.customDialogTextView);
			customDialogTextView.setText(R.string.message);
			ImageView customDialogImageView = (ImageView) viewRoot
					.findViewById(R.id.customDialogImageView);
			customDialogImageView.setImageResource(R.drawable.ic_launcher);

			AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
					.setTitle(R.string.title)
					.setView(viewRoot)
					.setPositiveButton(android.R.string.ok,
							new OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.cancel();
									MainActivity.this.finish();
								}
							}).setNegativeButton(android.R.string.no, null)
					.setCancelable(false);

			return alertDialog.create();
		}

		return super.onCreateDialog(id);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
