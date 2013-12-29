package ayrat.salavatovich.gmail.com.day_72.seekbarsample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
		textView = (TextView) findViewById(R.id.textView);
		seekBar.setOnSeekBarChangeListener(seekBar_OnSeekBarChange);

		show(seekBar.getProgress());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void show(int value) {
		textView.setText(getString(R.string.value, value));
	}

	private OnSeekBarChangeListener seekBar_OnSeekBarChange = new OnSeekBarChangeListener() {

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			show(seekBar.getProgress());
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {

		}

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			show(seekBar.getProgress());
		}
	};

}
