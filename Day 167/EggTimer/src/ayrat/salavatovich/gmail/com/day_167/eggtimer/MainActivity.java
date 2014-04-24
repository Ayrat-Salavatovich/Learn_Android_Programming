package ayrat.salavatovich.gmail.com.day_167.eggtimer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null)
			getSupportFragmentManager()
					.beginTransaction()
					.add(R.id.frameLayoutContainer,
							(Fragment) new PlaceholderFragment()).commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public static class PlaceholderFragment extends Fragment {

		private Button buttonStart;
		private TextView textViewTimeLeft;
		private NumberPickerView numberPickerView;
		private boolean startedTimer = false;
		private CountDownTimer timer;

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);

			buttonStart = (Button) rootView.findViewById(R.id.buttonStart);
			buttonStart.setOnClickListener(startOnClickListener);
			textViewTimeLeft = (TextView) rootView
					.findViewById(R.id.textViewTimeLeft);
			numberPickerView = (NumberPickerView) rootView
					.findViewById(R.id.numberPickerView);

			return rootView;
		}

		OnClickListener startOnClickListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (v.getId() == R.id.buttonStart)
					if (startedTimer)
						stopTimer();
					else
						startTimer();
			}
		};

		public void startTimer() {
			if (numberPickerView.getNumber() == 0)
				return;

			timer = new CountDownTimer(
					numberPickerView.getNumber() * 60 * 1000, 1000) {

				@Override
				public void onTick(long millisUntilFinished) {
					textViewTimeLeft.setText(getString(
							R.string.text_view_time_left,
							millisUntilFinished / 1000));
				}

				@Override
				public void onFinish() {
					startedTimer = false;
					textViewTimeLeft.setText(R.string.text_view_ready);
					buttonStart.setText(R.string.button_start);
				}
			};

			timer.start();
			buttonStart.setText(R.string.button_stop);
		}

		public void stopTimer() {
			if (timer != null)
				timer.cancel();

			startedTimer = false;
			startedTimer = true;
			buttonStart.setText(R.string.button_start);
		}

		@Override
		public void onPause() {
			stopTimer();
			super.onPause();
		}

	}

}
