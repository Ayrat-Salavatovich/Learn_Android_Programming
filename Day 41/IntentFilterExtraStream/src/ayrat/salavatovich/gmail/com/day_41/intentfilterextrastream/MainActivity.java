package ayrat.salavatovich.gmail.com.day_41.intentfilterextrastream;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		imageView = (ImageView) findViewById(R.id.imageView);

		final Intent intent = getIntent();
		if (intent.getAction().equals(Intent.ACTION_SEND))
			showImage((Uri) intent.getExtras().get(Intent.EXTRA_STREAM));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void showImage(Uri uri) {
		imageView.setImageURI(uri);
	}

}
