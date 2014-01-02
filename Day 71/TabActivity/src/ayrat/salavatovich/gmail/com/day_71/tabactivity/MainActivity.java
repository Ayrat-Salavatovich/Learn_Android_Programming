package ayrat.salavatovich.gmail.com.day_71.tabactivity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		TabHost tabHost = getTabHost();

		TabSpec tabMusic = tabHost.newTabSpec(getString(R.string.id_music));
		tabMusic.setIndicator(getString(R.string.indicator_music));
		tabMusic.setContent(new Intent(getApplicationContext(), TabMusic.class));

		TabSpec tabFilm = tabHost.newTabSpec(getString(R.string.id_film));
		tabFilm.setIndicator(getString(R.string.indicator_film));
		tabFilm.setContent(new Intent(getApplicationContext(), TabFilm.class));

		TabSpec tabPhoto = tabHost.newTabSpec(getString(R.string.id_photo));
		tabPhoto.setIndicator(getString(R.string.indicator_photo));
		tabPhoto.setContent(new Intent(getApplicationContext(), TabPhoto.class));

		tabHost.addTab(tabMusic);
		tabHost.addTab(tabFilm);
		tabHost.addTab(tabPhoto);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
