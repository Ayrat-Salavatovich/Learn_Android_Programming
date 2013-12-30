package ayrat.salavatovich.gmail.com.day_74.alertdialogwithradiobuttons;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private int selectColor = -1;
	private final int DEFAULT_CHECKED_ITEM = -1;
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
					.setSingleChoiceItems(R.array.colors, DEFAULT_CHECKED_ITEM,
							new OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									selectColor = which;
								}
							})
					.setPositiveButton(android.R.string.yes, null)
					.setNegativeButton(android.R.string.cancel,
							new OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
								}
							}).setCancelable(false);
			final AlertDialog dialog = alertDialog.create();
			dialog.setOnShowListener(new DialogInterface.OnShowListener() {

				@Override
				public void onShow(DialogInterface dialogInterface) {
					Button button = dialog
							.getButton(AlertDialog.BUTTON_POSITIVE);
					button.setOnClickListener(new CustomListener(dialog));
				}
			});

			return dialog;
		default:
			return super.onCreateDialog(id);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	class CustomListener implements View.OnClickListener {
		private final Dialog dialog;

		public CustomListener(Dialog dialog) {
			this.dialog = dialog;
		}

		@Override
		public void onClick(View v) {
			if (selectColor == -1) {
				Toast.makeText(context, R.string.not_close, Toast.LENGTH_SHORT)
						.show();

			} else {
				Toast.makeText(
						context,
						getString(R.string.color_is, getResources()
								.getStringArray(R.array.colors)[selectColor]),
						Toast.LENGTH_SHORT).show();
				dialog.dismiss();
			}
		}
	}

}
