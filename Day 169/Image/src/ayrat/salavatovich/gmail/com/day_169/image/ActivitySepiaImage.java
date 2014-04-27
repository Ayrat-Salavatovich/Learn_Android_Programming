package ayrat.salavatovich.gmail.com.day_169.image;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class ActivitySepiaImage extends Activity implements
		OnSeekBarChangeListener {

	private final int SEEK_BAR_START_VALUE = 100;
	private final int SEEK_BAR_MAX_VALUE = 200;
	private final int SEEK_BAR_STEP = 5;
	private ImageView imageView;
	private SeekBar seekBarSepiaDepth;
	private SeekBar seekBarSepiaRed;
	private SeekBar seekBarSepiaGreen;
	private SeekBar seekBarSepiaBlue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sepia_image);

		imageView = (ImageView) findViewById(R.id.imageView);
		seekBarSepiaDepth = (SeekBar) findViewById(R.id.seekBarSepiaDepth);
		seekBarSepiaRed = (SeekBar) findViewById(R.id.seekBarSepiaRed);
		seekBarSepiaGreen = (SeekBar) findViewById(R.id.seekBarSepiaGreen);
		seekBarSepiaBlue = (SeekBar) findViewById(R.id.seekBarSepiaBlue);

		prepateSeekBar(seekBarSepiaDepth);
		prepateSeekBar(seekBarSepiaRed);
		prepateSeekBar(seekBarSepiaGreen);
		prepateSeekBar(seekBarSepiaBlue);
	}

	private void prepateSeekBar(final SeekBar seekBar) {
		seekBar.setMax(SEEK_BAR_MAX_VALUE);
		seekBar.incrementProgressBy(SEEK_BAR_STEP);
		seekBar.setProgress(SEEK_BAR_START_VALUE);
		seekBar.setOnSeekBarChangeListener(this);
	}

	private void setEnabledSeekBars(boolean enabled) {
		seekBarSepiaRed.setEnabled(enabled);
		seekBarSepiaGreen.setEnabled(enabled);
		seekBarSepiaBlue.setEnabled(enabled);
	}

	// Изменении положения ползунка
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
	}

	// Ползунок начал перемещаться
	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {

	}

	// Ползунок закончил перемещаться
	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		setEnabledSeekBars(false);
		new FilterImage().execute(BitmapFactory.decodeResource(getResources(),
				R.drawable.beautiful_portuguese_girl));
	}

	private int getDepthSepiaValue() {
		return seekBarSepiaDepth.getProgress();
	}

	private double getRedSepiaValue() {
		return seekBarSepiaRed.getProgress() / 100.;
	}

	private double getGreenSepiaValue() {
		return seekBarSepiaGreen.getProgress() / 100.;
	}

	private double getBlueSepiaValue() {
		return seekBarSepiaBlue.getProgress() / 100.;
	}

	private class FilterImage extends AsyncTask<Bitmap, Void, Bitmap> {

		private final int MAX_VALUE = 255;
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

					// преобразуем изображение в оттенки серого
					// по формуле: 30% Red + 59% Green + 11 % Blue
					R = G = B = (int) (GS_RED * R + GS_GREEN * G + GS_BLUE * B);

					// устанавливаем уровень интенсивности для каждого цветового
					// канала
					R += (getDepthSepiaValue() * getRedSepiaValue());
					if (R > MAX_VALUE)
						R = MAX_VALUE;

					G += (getDepthSepiaValue() * getGreenSepiaValue());
					if (G > MAX_VALUE)
						G = MAX_VALUE;

					B += (getDepthSepiaValue() * getBlueSepiaValue());
					if (B > MAX_VALUE)
						B = MAX_VALUE;

					destination.setPixel(x, y, Color.argb(A, R, G, B));
				}
			}

			return destination;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			setEnabledSeekBars(true);
			imageView.setImageBitmap(result);
		}

		private Bitmap cloneBitmap(Bitmap source) {
			return Bitmap.createBitmap(source.getWidth(), source.getHeight(),
					source.getConfig());
		}

	}

}
