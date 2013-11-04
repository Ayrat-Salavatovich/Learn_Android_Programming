package ayrat.salavatovich.gmail.com.day_11.alertdialogwithtimer;

import java.util.Timer;
import java.util.TimerTask;

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

	private AlertDialog.Builder builder;
	private Context context;
	private final int ID_ALERT_DIALOG = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		context = MainActivity.this;
		builder = new AlertDialog.Builder(context);
		builder.setTitle(R.string.title);
		builder.setMessage(R.string.message);
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
			builder.setCancelable(false);
			final AlertDialog dialog = builder.create();
			dialog.show();
			final Timer timer = new Timer();
			timer.schedule(new TimerTask() {

				@Override
				public void run() {
					dialog.dismiss();
					timer.cancel();

				}
			}, 5000);
			return super.onCreateDialog(id);
		default:
			return null;
		}
	}

}
