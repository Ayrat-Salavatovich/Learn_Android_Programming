package ayrat.salavatovich.gmail.com.day_74.alertdialogprepare;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	private final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	private final int CURRENT_TIME_DIALOG_ID = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button:
			showDialog(CURRENT_TIME_DIALOG_ID);

			break;

		default:
			break;
		}
	}

	// Выполняется только один раз для диалога.
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case CURRENT_TIME_DIALOG_ID:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.title_current_time);
			builder.setMessage("");

			return builder.create();

		default:
			return super.onCreateDialog(id);
		}
	}

	// Выполняется каждый раз перед показом диалога.
	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		((AlertDialog) dialog).setMessage(sdf.format(new Date()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
