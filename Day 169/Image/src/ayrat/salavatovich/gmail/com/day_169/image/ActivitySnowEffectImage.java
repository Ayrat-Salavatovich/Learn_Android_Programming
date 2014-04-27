package ayrat.salavatovich.gmail.com.day_169.image;

import java.util.Random;

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

public class ActivitySnowEffectImage extends Activity implements
		OnClickListener {

	private ImageView imageView;
	private Button buttonSnowEffectImage;
	private Random random = new Random();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_snow_effect_image);

		buttonSnowEffectImage = (Button) findViewById(R.id.buttonSnowEffectImage);
		buttonSnowEffectImage.setOnClickListener(this);
		((Button) findViewById(R.id.buttonReset)).setOnClickListener(this);
		imageView = (ImageView) findViewById(R.id.imageView);
	}

	@Override
	public void onClick(View v) {
		if (R.id.buttonSnowEffectImage == v.getId()) {
			buttonSnowEffectImage.setEnabled(false);
			new SnowEffectImage().execute(((BitmapDrawable) imageView
					.getDrawable()).getBitmap());
		} else if (R.id.buttonReset == v.getId())
			imageView.setImageResource(R.drawable.beautiful_portuguese_girl);
	}

	private class SnowEffectImage extends AsyncTask<Bitmap, Void, Bitmap> {

		private final int MAX_VALUE = 255;

		@Override
		protected Bitmap doInBackground(Bitmap... images) {
			Bitmap source = images[0];
			final int width = source.getWidth();
			final int height = source.getHeight();
			Bitmap destination = Bitmap.createBitmap(width, height,
					Bitmap.Config.RGB_565);
			// Red (красный), Green (зелёный), Blue (синий)
			int R, G, B;
			int[] pixels = new int[width * height];
			source.getPixels(pixels, 0, width, 0, 0, width, height);
			// позиция пикселя в массиве pixels соотвествующая пикселю в 2D
			// позиции изображения
			int index;
			// порог для цвета
			int threshold;

			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					index = y * width + x;
					R = Color.red(pixels[index]);
					G = Color.green(pixels[index]);
					B = Color.blue(pixels[index]);

					threshold = random.nextInt(MAX_VALUE);

					if (R > threshold && G > threshold && B > threshold)
						pixels[index] = Color.WHITE;
				}
			}

			destination.setPixels(pixels, 0, width, 0, 0, width, height);

			return destination;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			buttonSnowEffectImage.setEnabled(true);
			imageView.setImageBitmap(result);
		}

	}

}
