package ayrat.salavatovich.gmail.com.day_11.simplealertdialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	private AlertDialog.Builder ad;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		context = MainActivity.this;

		ad = new AlertDialog.Builder(context);
		ad.setTitle(R.string.title);
		ad.setMessage(R.string.message);
		ad.setNegativeButton(R.string.blue_pill, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(context, R.string.if_blue_pill,
						Toast.LENGTH_LONG).show();
			}
		});
		ad.setPositiveButton(R.string.red_pill, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(context, R.string.if_red_pill, Toast.LENGTH_LONG)
						.show();
			}
		});
		ad.setCancelable(true);
		ad.setOnCancelListener(new OnCancelListener() {

			@Override
			public void onCancel(DialogInterface dialog) {
				Toast.makeText(context, R.string.not_choose, Toast.LENGTH_LONG)
						.show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClickButton(View v) {
		ad.show();
	}

}
