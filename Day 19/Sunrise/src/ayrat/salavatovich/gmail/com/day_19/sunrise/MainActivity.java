package ayrat.salavatovich.gmail.com.day_19.sunrise;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ImageView sun = (ImageView) findViewById(R.id.imageViewSun);
		ImageView clock = (ImageView) findViewById(R.id.imageViewClock);
		ImageView hand = (ImageView) findViewById(R.id.imageViewHand);

		Animation rise = AnimationUtils.loadAnimation(getApplicationContext(),
				R.animator.sun_rise);
		Animation clock_turn = AnimationUtils.loadAnimation(
				getApplicationContext(), R.animator.clock_turn);
		Animation hand_turn = AnimationUtils.loadAnimation(
				getApplicationContext(), R.animator.hour_turn);

		sun.startAnimation(rise);
		clock.startAnimation(clock_turn);
		hand.startAnimation(hand_turn);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
