package ayrat.salavatovich.gmail.com.day_177.ringermode;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends Activity {

	private AudioManager audioManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		init();
		showRingerMode();
	}

	private void init() {
		audioManager = (AudioManager) this
				.getSystemService(Context.AUDIO_SERVICE);
	}

	private void showRingerMode() {
		switch (audioManager.getRingerMode()) {
		case AudioManager.RINGER_MODE_NORMAL:
			((RadioButton) findViewById(R.id.radioButtonNormal))
					.setChecked(true);

			break;

		case AudioManager.RINGER_MODE_SILENT:
			((RadioButton) findViewById(R.id.radioButtonSilent))
					.setChecked(true);

			break;

		case AudioManager.RINGER_MODE_VIBRATE:
			((RadioButton) findViewById(R.id.radioButtonVibrate))
					.setChecked(true);

			break;

		default:
			break;
		}
	}

	public void onClick(View v) {
		int ringerMode = AudioManager.RINGER_MODE_NORMAL;
		switch (v.getId()) {
		case R.id.radioButtonNormal:
			ringerMode = AudioManager.RINGER_MODE_NORMAL;

			break;

		case R.id.radioButtonSilent:
			ringerMode = AudioManager.RINGER_MODE_SILENT;

			break;

		case R.id.radioButtonVibrate:
			ringerMode = AudioManager.RINGER_MODE_VIBRATE;

			break;

		default:
			break;
		}

		audioManager.setRingerMode(ringerMode);
	}

}
