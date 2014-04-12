package ayrat.salavatovich.gmail.com.day_150.volumecontrol;

import android.media.AudioManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity {

	private RadioGroup radioGroupVolumeControl;
	private SeekBar seekBarVolume;
	private AudioManager audioManager;
	private TextView textViewVolumeValue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textViewVolumeValue = (TextView) findViewById(R.id.textViewVolumeValue);
		radioGroupVolumeControl = (RadioGroup) findViewById(R.id.radioGroupVolumeControl);
		radioGroupVolumeControl
				.setOnCheckedChangeListener(onCheckedChangeListener);
		seekBarVolume = (SeekBar) findViewById(R.id.seekBarVolume);
		seekBarVolume.setOnSeekBarChangeListener(onSeekBarChangeListener);

		initControl(AudioManager.STREAM_MUSIC);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			switch (checkedId) {
			case R.id.radioButtonVolumeControlAlarm:
				initControl(AudioManager.STREAM_ALARM);

				break;

			case R.id.radioButtonVolumeControlMedia:
				initControl(AudioManager.STREAM_MUSIC);

				break;

			case R.id.radioButtonVolumeControlRinger:
				initControl(AudioManager.STREAM_RING);

				break;

			case R.id.radioButtonVolumeControlVoice:
				initControl(AudioManager.STREAM_VOICE_CALL);

				break;

			case R.id.radioButtonVolumeControlNotification:
				initControl(AudioManager.STREAM_NOTIFICATION);

				break;

			case R.id.radioButtonVolumeControlSystem:
				initControl(AudioManager.STREAM_SYSTEM);

				break;

			case R.id.radioButtonVolumeControlDTMF:
				initControl(AudioManager.STREAM_DTMF);

				break;

			default:
				break;
			}
		}
	};

	private void initControl(final int streamType) {
		if (isNull(audioManager))
			audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

		setVolumeControlStream(streamType);

		seekBarVolume.setProgress(audioManager.getStreamVolume(streamType));
		seekBarVolume.setMax(audioManager.getStreamMaxVolume(streamType));
	}

	private boolean isNull(final Object obj) {
		if (obj == null)
			return true;

		return false;
	}

	private OnSeekBarChangeListener onSeekBarChangeListener = new OnSeekBarChangeListener() {

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
		}

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			audioManager.setStreamVolume(getVolumeControlStream(), progress,
					AudioManager.FLAG_PLAY_SOUND);
			textViewVolumeValue.setText(getString(R.string.volume_value,
					progress));
		}
	};

}
