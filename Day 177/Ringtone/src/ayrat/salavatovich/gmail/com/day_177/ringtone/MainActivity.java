package ayrat.salavatovich.gmail.com.day_177.ringtone;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	public static final int RINGTONE_PICKER = 1;
	private MediaPlayer mediaPlayer;
	private Uri ringtoneUri;

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
		Intent intent = new Intent("android.intent.action.RINGTONE_PICKER");
		startActivityForResult(intent, RINGTONE_PICKER);
	}

	private void playRingtone(Uri ringtone) {
		try {
			mediaPlayer.reset();
			mediaPlayer.setDataSource(this, ringtone);
			final AudioManager audioManager = (AudioManager) getSystemService(this.AUDIO_SERVICE);
			if (audioManager.getStreamVolume(AudioManager.STREAM_ALARM) != 0) {
				mediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
				mediaPlayer.prepare();
				mediaPlayer.start();
			}
		} catch (Exception e) {
		}
	}

	@Override
	protected void onDestroy() {
		stopRingtone();
		if (mediaPlayer != null) {
			mediaPlayer.release();
		}
		mediaPlayer = null;
		super.onDestroy();
	}

	public void onClickPlayStop(View v) {
		if (mediaPlayer.isPlaying())
			stopRingtone();
		else
			playRingtone(ringtoneUri);
	}

	private void stopRingtone() {
		if (mediaPlayer != null) {
			mediaPlayer.stop();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == RINGTONE_PICKER) {
			ringtoneUri = data
					.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
			RingtoneManager.setActualDefaultRingtoneUri(this,
					RingtoneManager.TYPE_NOTIFICATION, ringtoneUri);
			String title = "Нет";
			if (ringtoneUri != null) {
				Ringtone ringtone = RingtoneManager.getRingtone(this,
						ringtoneUri);
				title = ringtone.getTitle(this);
			}
			TextView textView = (TextView) findViewById(R.id.textView);
			textView.setText(title);
		}
	}
}
