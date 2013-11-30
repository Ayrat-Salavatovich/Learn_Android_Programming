package ayrat.salavatovich.gmail.com.day_36.dynamiclayout3;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity {

	private SeekBar seekBarWeight;
	private Button buttonLeft, buttonRight;

	private LinearLayout.LayoutParams layoutParamsButtonLeft;
	private LinearLayout.LayoutParams layoutParamsButtonRight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		seekBarWeight = (SeekBar) findViewById(R.id.seekBarWeight);
		buttonLeft = (Button) findViewById(R.id.buttonLeft);
		buttonRight = (Button) findViewById(R.id.buttonRigth);
		layoutParamsButtonLeft = (LinearLayout.LayoutParams) buttonLeft
				.getLayoutParams();
		layoutParamsButtonRight = (LinearLayout.LayoutParams) buttonRight
				.getLayoutParams();

		seekBarWeight.setOnSeekBarChangeListener(listener);

		showProgressWeight();
	}

	private void showProgressWeight() {
		buttonLeft.setText(String.valueOf(seekBarWeight.getProgress()));
		buttonRight.setText(String.valueOf(seekBarWeight.getMax()
				- seekBarWeight.getProgress()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private OnSeekBarChangeListener listener = new OnSeekBarChangeListener() {

		/*
		 * Срабатывает, когда отпускаем ползунок
		 */
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
		}

		/*
		 * Срабатывает, когда начинаем тащить ползунок
		 */
		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
		}

		/*
		 * Срабатывает все время, пока значение меняется
		 */
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			int leftValue, rightValue;
			leftValue = progress;
			rightValue = seekBar.getMax() - leftValue;

			layoutParamsButtonLeft.weight = leftValue;
			layoutParamsButtonRight.weight = rightValue;

			showProgressWeight();
		}
	};

}
