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

public class ActivityHueImage extends Activity implements
		OnSeekBarChangeListener {

	private final int SEEK_BAR_START_VALUE = 1;
	private final int SEEK_BAR_MAX_VALUE = 10;
	private ImageView imageView;
	private SeekBar seekBarHue;
	private TextView textViewInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hue_image);

		imageView = (ImageView) findViewById(R.id.imageView);
		textViewInfo = (TextView) findViewById(R.id.textViewInfo);
		seekBarHue = (SeekBar) findViewById(R.id.seekBarHue);

		prepateSeekBar(seekBarHue);
	}

	private void prepateSeekBar(final SeekBar seekBar) {
		seekBar.setMax(SEEK_BAR_MAX_VALUE);
		seekBar.setProgress(SEEK_BAR_START_VALUE);
		seekBar.setOnSeekBarChangeListener(this);
	}

	private void setEnabledSeekBar(boolean enabled) {
		seekBarHue.setEnabled(enabled);
	}

	private int getHueValue() {
		return seekBarHue.getProgress();
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
		new HueImage().execute(BitmapFactory.decodeResource(getResources(),
				R.drawable.beautiful_portuguese_girl));
	}

	private class HueImage extends AsyncTask<Bitmap, Void, Bitmap> {

		@Override
		protected Bitmap doInBackground(Bitmap... images) {
			Bitmap source = images[0];
			// получаем размеры изображения
			final int width = source.getWidth();
			final int height = source.getHeight();
			Bitmap destination = Bitmap.createBitmap(width, height,
					Bitmap.Config.ARGB_8888);
			float[] HSV = new float[3];
			// получаем массив пикселей из изображения
			int[] pixels = new int[width * height];
			source.getPixels(pixels, 0, width, 0, 0, width, height);
			// позиция пикселя в массиве pixels соотвествующая пикселю в 2D
			// позиции изображения
			int index;

			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					index = y * width + x;
					// конвертируем в HSV
					Color.colorToHSV(pixels[index], HSV);
					// увеличиваем уровень
					HSV[0] *= getHueValue();
					HSV[0] = (float) Math.max(0.0, Math.min(HSV[0], 360.0));
					// возвращаем обратно
					pixels[index] |= Color.HSVToColor(HSV);
				}
			}

			destination.setPixels(pixels, 0, width, 0, 0, width, height);

			return destination;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			setEnabledSeekBar(true);
			imageView.setImageBitmap(result);
			textViewInfo.setText("Level: " + getHueValue());
		}

	}

}
