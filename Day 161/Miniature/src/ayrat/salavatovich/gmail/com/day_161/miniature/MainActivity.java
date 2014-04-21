package ayrat.salavatovich.gmail.com.day_161.miniature;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private final int MINIATURE_WIDTH = 178;
	private final int MINIATURE_HEIGHT = 118;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final ImageView imageViewMiniature = (ImageView) findViewById(R.id.imageViewMiniature);
		Bitmap bitmapSource = BitmapFactory.decodeResource(getResources(),
				R.drawable.portugal);
		Bitmap bitmapMiniature = ThumbnailUtils.extractThumbnail(bitmapSource,
				MINIATURE_WIDTH, MINIATURE_HEIGHT);

		imageViewMiniature.setImageBitmap(bitmapMiniature);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
