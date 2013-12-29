package ayrat.salavatovich.gmail.com.day_72.ratingbarsample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity {

	private RatingBar ratingBar;
	private final int MAX_STARS = 5;
	private final float STEP_SIZE = .5f;
	private final float DEFAULT_RATING = 1.5f;
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ratingBar = (RatingBar) findViewById(R.id.ratingBar);
		ratingBar.setNumStars(MAX_STARS);
		ratingBar.setStepSize(STEP_SIZE);
		ratingBar.setRating(DEFAULT_RATING);
		ratingBar.setOnRatingBarChangeListener(ratingBar_onRatingBarChange);

		textView = (TextView) findViewById(R.id.textView);
		showRating(ratingBar.getRating());
	}

	public void onClick(View btn) {
		switch (btn.getId()) {
		case R.id.buttonUp:
			incrementRating(STEP_SIZE);
			break;
		case R.id.buttonDown:
			decrementRating(STEP_SIZE);
			break;

		default:
			break;
		}
	}

	private void incrementRating(float diff) {
		if (ratingBar.getRating() + diff <= MAX_STARS)
			ratingBar.setRating(ratingBar.getRating() + diff);

		showRating(ratingBar.getRating());
	}

	private void decrementRating(float diff) {
		if (ratingBar.getRating() - diff >= 0)
			ratingBar.setRating(ratingBar.getRating() - diff);

		showRating(ratingBar.getRating());
	}

	private void showRating(float rating) {
		textView.setText(getString(R.string.value, rating));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private OnRatingBarChangeListener ratingBar_onRatingBarChange = new OnRatingBarChangeListener() {

		@Override
		public void onRatingChanged(RatingBar rateBar, float rating,
				boolean fromUser) {
			showRating(rating);
		}
	};

}
