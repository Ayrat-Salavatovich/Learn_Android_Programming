package ayrat.salavatovich.gmail.com.day_177.ringtonetexttransferlanguage;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	private MediaPlayer mediaPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		init();
	}

	private void init() {
		mediaPlayer = new MediaPlayer();
	}

	public void onClick(View v) {
		if (mediaPlayer != null) {
			mediaPlayer.release();
		}

		mediaPlayer = MediaPlayer.create(this, R.raw.sample);
		mediaPlayer.start();
	}
}
