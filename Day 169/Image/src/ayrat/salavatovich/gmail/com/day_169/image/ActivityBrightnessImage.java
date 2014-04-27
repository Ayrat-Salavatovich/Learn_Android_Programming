package ayrat.salavatovich.gmail.com.day_169.image;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class ActivityBrightnessImage extends Activity implements
		OnSeekBarChangeListener {

	private final int SEEK_BAR_ZERO_POINT = 255;
	private final int SEEK_BAR_MAX_VALUE = 255 << 1;
	private final int SEEK_BAR_STEP = 5;
	private ImageView imageView;
	private SeekBar seekBarBrightness;
	private TextView textViewInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_brightness_image);

		imageView = (ImageView) findViewById(R.id.imageView);
		textViewInfo = (TextView) findViewById(R.id.textViewInfo);
		seekBarBrightness = (SeekBar) findViewById(R.id.seekBarBrightness);

		prepateSeekBar(seekBarBrightness);
	}

	private void prepateSeekBar(final SeekBar seekBar) {
		seekBar.setMax(SEEK_BAR_MAX_VALUE);
		seekBar.incrementProgressBy(SEEK_BAR_STEP);
		seekBar.setProgress(SEEK_BAR_ZERO_POINT);
		seekBar.setOnSeekBarChangeListener(this);
	}

	private void setEnabledSeekBar(boolean enabled) {
		seekBarBrightness.setEnabled(enabled);
	}

	private int getBrightnessValue() {
		return -1 * 255 + seekBarBrightness.getProgress();
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
		textViewInfo.setText(R.string.text_view_empty);
		setEnabledSeekBar(false);
		new ContrastImage().execute(BitmapFactory.decodeResource(
				getResources(), R.drawable.beautiful_portuguese_girl));
	}

	private class ContrastImage extends AsyncTask<Bitmap, Void, Bitmap> {

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
					A = Color.alpha(pixelColor);
					R = Color.red(pixelColor);
					G = Color.green(pixelColor);
					B = Color.blue(pixelColor);

					// увеличиваем/уменьшаем каждый цветовой канал
					R += getBrightnessValue();
					if (R > MAX_VALUE)
						R = MAX_VALUE;
					else if (R < 0)
						R = 0;

					G += getBrightnessValue();
					if (G > MAX_VALUE)
						G = MAX_VALUE;
					else if (G < 0)
						G = 0;

					B += getBrightnessValue();
					if (B > MAX_VALUE)
						B = MAX_VALUE;
					else if (B < 0)
						B = 0;

					destination.setPixel(x, y, Color.argb(A, R, G, B));
				}
			}

			return destination;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			setEnabledSeekBar(true);
			imageView.setImageBitmap(result);
			textViewInfo.setText("Bright: " + getBrightnessValue());
		}

		private Bitmap cloneBitmap(Bitmap source) {
			return Bitmap.createBitmap(source.getWidth(), source.getHeight(),
					source.getConfig());
		}

	}

}
