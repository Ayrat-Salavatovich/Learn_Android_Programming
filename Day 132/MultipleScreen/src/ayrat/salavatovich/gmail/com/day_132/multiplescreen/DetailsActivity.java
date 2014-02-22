package ayrat.salavatovich.gmail.com.day_132.multiplescreen;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class DetailsActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		int orientation = getResources().getConfiguration().orientation;
		if (orientation == Configuration.ORIENTATION_LANDSCAPE && isLarge()) {
			finish();

			return;
		}

		if (savedInstanceState == null) {
			DetailsFragment detailsFragment = DetailsFragment
					.newIstance(getIntent().getIntExtra(Constants.POSITION,
							Constants.ZERO));
			getSupportFragmentManager().beginTransaction()
					.add(android.R.id.content, detailsFragment).commit();
		}
	}

	private boolean isLarge() {
		return (getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
	}

	@Override
	public void onBackPressed() {
		Intent intent = getIntent();
		intent.putExtra(Constants.WITH_DETAILS, false);
		setResult(RESULT_OK, intent);

		finish();
	}

}
