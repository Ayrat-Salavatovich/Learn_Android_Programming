package ayrat.salavatovich.gmail.com.day_38.simplegraph;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.view.Menu;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		imageView = (ImageView) findViewById(R.id.imageView);
		imageView.setBackgroundResource(R.drawable.graph);
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);

		AnimationDrawable animationDrawable = (AnimationDrawable) imageView
				.getBackground();

		if (hasFocus)
			animationDrawable.start();
		else
			animationDrawable.stop();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
