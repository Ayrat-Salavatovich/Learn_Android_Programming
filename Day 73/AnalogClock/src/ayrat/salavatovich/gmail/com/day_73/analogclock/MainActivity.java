package ayrat.salavatovich.gmail.com.day_73.analogclock;

import android.app.Activity;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import android.widget.AnalogClock;

public class MainActivity extends Activity {

	private AnalogClock analogClock;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		analogClock = (AnalogClock) findViewById(R.id.analogClock);
	}

	public void onClick(View btn) {
		switch (btn.getId()) {
		case R.id.buttonStart:
			analogClock.startAnimation(new ViewAnimation());
			break;
		case R.id.buttonStop:
			analogClock.clearAnimation();
			break;
		default:
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	protected class ViewAnimation extends Animation {

		private boolean trend;
		private float pivoteX, pivoteY;

		@Override
		protected void applyTransformation(float interpolatedTime,
				Transformation t) {
			super.applyTransformation(interpolatedTime, t);
			final Matrix matrix = t.getMatrix();
			if (trend)
				matrix.setScale(.9f + interpolatedTime / 10,
						.9f + interpolatedTime / 10, pivoteX, pivoteY);
			else
				matrix.setScale(1 - interpolatedTime / 10,
						1 - interpolatedTime / 10, pivoteX, pivoteY);

			if (interpolatedTime >= 1.f)
				trend = !trend;
		}

		@Override
		public void initialize(int width, int height, int parentWidth,
				int parentHeight) {
			super.initialize(width, height, parentWidth, parentHeight);

			setDuration(1000);
			setFillAfter(true);
			setInterpolator(new LinearInterpolator());
			setRepeatCount(INFINITE);

			pivoteX = (float) width / 2;
			pivoteY = (float) height / 2;

		}

	}

}
