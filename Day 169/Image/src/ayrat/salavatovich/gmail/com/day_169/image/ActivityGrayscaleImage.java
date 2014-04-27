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

public class ActivityGrayscaleImage extends Activity implements OnClickListener {

	private ImageView imageView;
	private Button buttonGrayscaleImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grayscale_image);

		buttonGrayscaleImage = ((Button) findViewById(R.id.buttonGrayscaleImage));
		buttonGrayscaleImage.setOnClickListener(this);
		imageView = (ImageView) findViewById(R.id.imageView);
	}

	@Override
	public void onClick(View v) {
		if (R.id.buttonGrayscaleImage == v.getId()) {
			buttonGrayscaleImage.setEnabled(false);
			new GrayscaleImage().execute(((BitmapDrawable) imageView
					.getDrawable()).getBitmap());
		}
	}

	private class GrayscaleImage extends AsyncTask<Bitmap, Void, Bitmap> {

		private final double GS_RED = 0.299;
		private final double GS_GREEN = 0.587;
		private final double GS_BLUE = 0.114;

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
					A = Color.alpha(pixelColor);
					R = Color.red(pixelColor);
					G = Color.green(pixelColor);
					B = Color.blue(pixelColor);
					// преобразуем изображение
					// по формуле: 30% Red + 59% Green + 11 % Blue
					R = G = B = (int) (GS_RED * R + GS_GREEN * G + GS_BLUE * B);
					// полученный результа записываем в этот же пиксель
					destination.setPixel(x, y, Color.argb(A, R, G, B));
				}
			}

			return destination;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			buttonGrayscaleImage.setEnabled(true);
			imageView.setImageBitmap(result);
		}

		private Bitmap cloneBitmap(Bitmap source) {
			return Bitmap.createBitmap(source.getWidth(), source.getHeight(),
					source.getConfig());
		}

	}

}
