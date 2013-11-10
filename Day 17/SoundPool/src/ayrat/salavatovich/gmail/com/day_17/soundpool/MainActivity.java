package ayrat.salavatovich.gmail.com.day_17.soundpool;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		anthemSoundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC,
				0);
		assets = getAssets();

		loadAnthems();

		setOnClickImageButtonListener();
	}

	private void setOnClickImageButtonListener() {
		((ImageButton) findViewById(R.id.imageButtonPlayAnthemPortugal))
				.setOnClickListener(this);
		((ImageButton) findViewById(R.id.imageButtonPlayAnthemFrance))
				.setOnClickListener(this);
		((ImageButton) findViewById(R.id.imageButtonPlayAnthemIreland))
				.setOnClickListener(this);
		((ImageButton) findViewById(R.id.imageButtonPlayAnthemMorocco))
				.setOnClickListener(this);
		((ImageButton) findViewById(R.id.imageButtonPlayAnthemSpain))
				.setOnClickListener(this);
		((ImageButton) findViewById(R.id.imageButtonPlayAnthemUnitedKingdom))
				.setOnClickListener(this);
	}

	private void loadAnthems() {
		anthems.put("portugal", loadAnthem("anthem_portugal.ogg"));
		anthems.put("france", loadAnthem("anthem_france.ogg"));
		anthems.put("ireland", loadAnthem("anthem_ireland.ogg"));
		anthems.put("morocco", loadAnthem("anthem_morocco.ogg"));
		anthems.put("spain", loadAnthem("anthem_spain.ogg"));
		anthems.put("united_kingdom", loadAnthem("anthem_united_kingdom.ogg"));
	}

	private int loadAnthem(String anthemName) {
		AssetFileDescriptor afd = null;
		try {
			afd = assets.openFd(anthemName);
		} catch (IOException e) {
			e.printStackTrace();
			Toast.makeText(getApplicationContext(),
					getString(R.string.not_found_file, anthemName),
					Toast.LENGTH_SHORT).show();
			return -1;
		}
		return anthemSoundPool.load(afd, 1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imageButtonPlayAnthemPortugal:
			playSound(anthems.get("portugal"));
			break;
		case R.id.imageButtonPlayAnthemFrance:
			playSound(anthems.get("france"));
			break;
		case R.id.imageButtonPlayAnthemIreland:
			playSound(anthems.get("ireland"));
			break;
		case R.id.imageButtonPlayAnthemMorocco:
			playSound(anthems.get("morocco"));
			break;
		case R.id.imageButtonPlayAnthemSpain:
			playSound(anthems.get("spain"));
			break;
		case R.id.imageButtonPlayAnthemUnitedKingdom:
			playSound(anthems.get("united_kingdom"));
			break;
		default:
			break;
		}
	}

	protected void playSound(int sound) {
		if (sound > 0)
			anthemSoundPool.play(sound, 1, 1, 1, 0, 1);
		else
			new IllegalArgumentException();
	}

	private SoundPool anthemSoundPool;
	private Map<String, Integer> anthems = new HashMap<String, Integer>();
	private int MAX_STREAMS = 3;
	private AssetManager assets;

}
