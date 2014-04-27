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

public class ActivityNoiseEffectImage extends Activity implements
		OnClickListener {

	private ImageView imageView;
	private Button buttonNoiseEffectImage;
	private Random random = new Random();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_noise_effect_image);

		buttonNoiseEffectImage = (Button) findViewById(R.id.buttonNoiseEffectImage);
		buttonNoiseEffectImage.setOnClickListener(this);
		((Button) findViewById(R.id.buttonReset)).setOnClickListener(this);
		imageView = (ImageView) findViewById(R.id.imageView);
	}

	@Override
	public void onClick(View v) {
		if (R.id.buttonNoiseEffectImage == v.getId()) {
			buttonNoiseEffectImage.setEnabled(false);
			new NoiseEffectImage().execute(((BitmapDrawable) imageView
					.getDrawable()).getBitmap());
		} else if (R.id.buttonReset == v.getId())
			imageView.setImageResource(R.drawable.beautiful_portuguese_girl);
	}

	private class NoiseEffectImage extends AsyncTask<Bitmap, Void, Bitmap> {

		private final int MAX_VALUE = 255;

		@Override
		protected Bitmap doInBackground(Bitmap... images) {
			Bitmap source = images[0];
			// получаем размеры изображения
			final int width = source.getWidth();
			final int height = source.getHeight();
			Bitmap destination = cloneBitmap(source);
			// получаем массив пикселей из изображения
			int[] pixels = new int[width * height];
			source.getPixels(pixels, 0, width, 0, 0, width, height);
			// позиция пикселя в массиве pixels соотвествующая пикселю в 2D
			// позиции изображения
			int index;
			// случайный цвет
			int randomColor;

			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					index = y * width + x;

					randomColor = Color.rgb(random.nextInt(MAX_VALUE),
							random.nextInt(MAX_VALUE),
							random.nextInt(MAX_VALUE));

					pixels[index] |= randomColor;
				}
			}

			destination.setPixels(pixels, 0, width, 0, 0, width, height);

			return destination;
		}

		private Bitmap cloneBitmap(Bitmap source) {
			return Bitmap.createBitmap(source.getWidth(), source.getHeight(),
					source.getConfig());
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			buttonNoiseEffectImage.setEnabled(true);
			imageView.setImageBitmap(result);
		}

	}

}
