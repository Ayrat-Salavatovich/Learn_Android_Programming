package ayrat.salavatovich.gmail.com.day_132.multiplescreen;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public class MainActivity extends FragmentActivity implements
		OnItemClickListener {

	// Последний выбранный элемент
	private int position = -1;
	private boolean withDetails;
	private int orientation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		orientation = getResources().getConfiguration().orientation;
		if (savedInstanceState != null) {
			position = savedInstanceState.getInt(Constants.POSITION);
			withDetails = savedInstanceState.getBoolean(Constants.WITH_DETAILS);
		}

		showDetails(orientation, position);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void itemClick(int position) {
		this.position = position;
		this.withDetails = true;
		showDetails(orientation, position);
	}

	private void showDetails(int orientation, int position) {
		if (position < 0)
			return;

		if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
			DetailsFragment detailsFragment = (DetailsFragment) getSupportFragmentManager()
					.findFragmentById(R.id.frameLayout);
			if (detailsFragment == null
					|| detailsFragment.getPosition() != position) {
				detailsFragment = DetailsFragment.newIstance(position);
				getSupportFragmentManager().beginTransaction()
						.replace(R.id.frameLayout, detailsFragment).commit();
			}
		} else if (withDetails)
			startActivityForResult(new Intent(MainActivity.this,
					DetailsActivity.class).putExtra(Constants.POSITION,
					position), Constants.REQUEST_CODE_DETAILS_ACTIVITY);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case Constants.REQUEST_CODE_DETAILS_ACTIVITY:
			if (data == null || resultCode != RESULT_OK)
				return;

			withDetails = data.getBooleanExtra(Constants.WITH_DETAILS, true);
			break;

		default:
			super.onActivityResult(requestCode, resultCode, data);
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		outState.putInt(Constants.POSITION, position);
		outState.putBoolean(Constants.WITH_DETAILS, withDetails);
	}

}
