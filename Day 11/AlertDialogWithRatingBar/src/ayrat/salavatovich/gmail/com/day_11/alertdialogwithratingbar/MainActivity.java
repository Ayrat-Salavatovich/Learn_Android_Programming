package ayrat.salavatovich.gmail.com.day_11.alertdialogwithratingbar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView textView;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		context = MainActivity.this;
		textView = (TextView) findViewById(R.id.textView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClick(View v) {
		showRatingDialog();
	}

	private void showRatingDialog() {
		final AlertDialog.Builder ratingDialog = new AlertDialog.Builder(
				context);

		ratingDialog.setIcon(android.R.drawable.btn_star_big_on);
		ratingDialog.setTitle(R.string.vote_for_portugal);

		View linearLayout = getLayoutInflater().inflate(R.layout.rating_dialog,
				null);
		ratingDialog.setView(linearLayout);

		final RatingBar rating = (RatingBar) linearLayout
				.findViewById(R.id.ratingBar);

		ratingDialog.setPositiveButton(R.string.ok, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				textView.setText(getResources().getString(R.string.your_rating)
						+ " " + rating.getRating());
				dialog.dismiss();
			}
		}).setNegativeButton(R.string.cancel, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		ratingDialog.create();
		ratingDialog.show();
	}

}
