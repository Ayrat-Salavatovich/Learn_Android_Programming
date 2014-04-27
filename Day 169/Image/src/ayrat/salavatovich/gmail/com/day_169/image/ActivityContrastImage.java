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

public class ActivityContrastImage extends Activity implements
		OnSeekBarChangeListener {

	private final int SEEK_BAR_START_VALUE = 0;
	private final int SEEK_BAR_MAX_VALUE = 255;
	private final int SEEK_BAR_STEP = 5;
	private ImageView imageView;
	private SeekBar seekBarContrast;
	private TextView textViewInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contrast_image);

		imageView = (ImageView) findViewById(R.id.imageView);
		textViewInfo = (TextView) findViewById(R.id.textViewInfo);
		seekBarContrast = (SeekBar) findViewById(R.id.seekBarContrast);

		prepateSeekBar(seekBarContrast);
	}

	private void prepateSeekBar(final SeekBar seekBar) {
		seekBar.setMax(SEEK_BAR_MAX_VALUE);
		seekBar.incrementProgressBy(SEEK_BAR_STEP);
		seekBar.setProgress(SEEK_BAR_START_VALUE);
		seekBar.setOnSeekBarChangeListener(this);
	}

	private void setEnabledSeekBar(boolean enabled) {
		seekBarContrast.setEnabled(enabled);
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

		@Override
		protected Bitmap doInBackground(Bitmap... images) {
			Bitmap source = images[0];
			Bitmap destination = cloneBitmap(source);
			// Alpha (прозрачность)
			int A;
			// Red (красный), Green (зелёный), Blue (синий)
			int R, G, B;
			int pixelColor;
			double contrast = getContrastValue();

			for (int y = 0; y < source.getHeight(); y++) {
				for (int x = 0; x < source.getWidth(); x++) {
					pixelColor = source.getPixel(x, y);
					A = Color.alpha(pixelColor);
					R = applyFilterContrastForColorChannel(
							Color.red(pixelColor), contrast);
					G = applyFilterContrastForColorChannel(
							Color.green(pixelColor), contrast);
					B = applyFilterContrastForColorChannel(
							Color.blue(pixelColor), contrast);

					destination.setPixel(x, y, Color.argb(A, R, G, B));
				}
			}

			return destination;
		}

		/**
		 * Применяем значение контрастности к цветовому каналу по формуле:
		 * <ul>
		 * <li>Берём цвет пикселя, делённый на 255</li>
		 * <li>Отнимаем 0.5</li>
		 * <li>Умножаем на значение контрастности</li>
		 * <li>Прибавляем 0.5</li>
		 * <li>Умножаем на 255</li>
		 * </ul>
		 * 
		 * @param цвет
		 * @param контрасность
		 * @return
		 */
		private int applyFilterContrastForColorChannel(int color,
				double contrast) {
			int result = (int) (((((color / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
			if (result < 0)
				result = 0;
			else if (result > 255)
				result = 255;

			return result;
		}

		/**
		 * Устанавливем значение контрастности по формуле
		 * 
		 * <pre>
		 * ((value + 100) / 100) &circ; 2
		 * </pre>
		 * 
		 * @return значение контрастности
		 */
		private double getContrastValue() {
			return Math.pow((seekBarContrast.getProgress() + 100) / 100, 2);
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			setEnabledSeekBar(true);
			imageView.setImageBitmap(result);
			textViewInfo.setText("Contrast: " + seekBarContrast.getProgress());
		}

		private Bitmap cloneBitmap(Bitmap source) {
			return Bitmap.createBitmap(source.getWidth(), source.getHeight(),
					source.getConfig());
		}

	}

}
