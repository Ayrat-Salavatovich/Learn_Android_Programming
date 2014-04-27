package ayrat.salavatovich.gmail.com.day_169.image;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class ActivityInvertImage extends Activity implements OnClickListener {

	private ImageView imageView;
	private Button buttonInvertImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_invert_image);

		buttonInvertImage = ((Button) findViewById(R.id.buttonInvertImage));
		buttonInvertImage.setOnClickListener(this);
		imageView = (ImageView) findViewById(R.id.imageView);
	}

	@Override
	public void onClick(View v) {
		if (R.id.buttonInvertImage == v.getId()) {
			buttonInvertImage.setEnabled(false);
			new InvertImage()
					.execute(((BitmapDrawable) imageView.getDrawable())
							.getBitmap());
		}
	}

	private class InvertImage extends AsyncTask<Bitmap, Void, Bitmap> {

		private final int MAX_VALUE = 255;

		@Override
		protected Bitmap doInBackground(Bitmap... images) {
			Bitmap source = images[0];
			Bitmap destination = cloneBitmap(source);
			// Alpha (прозрачность)
			int A;
			// Red (красный), Green (зелёный), Blue (синий)
			int R, G, B;
			int pixelColor;

			for (int y = 0; y < source.getHeight(); y++) {
				for (int x = 0; x < source.getWidth(); x++) {
					pixelColor = source.getPixel(x, y);
					// сохраняем alpha-канал
					A = Color.alpha(pixelColor);
					// инвертируем байт каждого R/G/B канала
					// по формуле: 0xFF – CurrentValue.
					R = MAX_VALUE - Color.red(pixelColor);
					G = MAX_VALUE - Color.green(pixelColor);
					B = MAX_VALUE - Color.blue(pixelColor);
					// полученный результа записываем в этот же пиксель
					destination.setPixel(x, y, Color.argb(A, R, G, B));
				}
			}

			return destination;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			buttonInvertImage.setEnabled(true);
			imageView.setImageBitmap(result);
		}

		private Bitmap cloneBitmap(Bitmap source) {
			return Bitmap.createBitmap(source.getWidth(), source.getHeight(),
					source.getConfig());
		}

	}

}
