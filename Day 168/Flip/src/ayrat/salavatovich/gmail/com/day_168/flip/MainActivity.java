package ayrat.salavatovich.gmail.com.day_168.flip;

import android.os.Bundle;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends FragmentActivity {

	public static final int FLIP_VERTICAL = 1;
	public static final int FLIP_HORIZONTAL = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null)
			getSupportFragmentManager()
					.beginTransaction()
					.add(R.id.frameLayoutContainer,
							(Fragment) new PlaceholderFragment()).commit();
	}

	public static Bitmap flip(final ImageView imageView, int type) {
		BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView
				.getDrawable();
		Bitmap bitmap = bitmapDrawable.getBitmap();
		int bitmapWidth = bitmap.getWidth();
		int bitmapHeight = bitmap.getHeight();
		Matrix matrix = new Matrix();
		Bitmap mirrorBitmap = null;

		if (type == FLIP_HORIZONTAL) {
			matrix.preScale(-1.f, 1.f);
			mirrorBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmapWidth,
					bitmapHeight, matrix, true);
		} else if (type == FLIP_VERTICAL) {
			matrix.preScale(1.f, -1.f);
			mirrorBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmapWidth,
					bitmapHeight, matrix, true);
		}

		return mirrorBitmap;
	}

	public static Bitmap horizontalFlip(final ImageView imageView) {
		return flip(imageView, FLIP_HORIZONTAL);
	}

	public static Bitmap verticalFlip(final ImageView imageView) {
		return flip(imageView, FLIP_VERTICAL);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public static class PlaceholderFragment extends Fragment {

		private Button buttonHorizontalFlip;
		private Button buttonVerticalFlip;
		private ImageView imageView;

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);

			buttonHorizontalFlip = (Button) rootView
					.findViewById(R.id.buttonHorizontalFlip);
			buttonVerticalFlip = (Button) rootView
					.findViewById(R.id.buttonVerticalFlip);
			imageView = (ImageView) rootView.findViewById(R.id.imageView);

			buttonHorizontalFlip.setOnClickListener(flipOnClickListener);
			buttonVerticalFlip.setOnClickListener(flipOnClickListener);

			return rootView;
		}

		private OnClickListener flipOnClickListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (v == buttonHorizontalFlip)
					imageView.setImageBitmap(horizontalFlip(imageView));
				else if (v == buttonVerticalFlip)
					imageView.setImageBitmap(verticalFlip(imageView));
			}
		};

	}

}
