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

public class ActivityFilterImage extends Activity implements
		OnSeekBarChangeListener {

	private final int SEEK_BAR_START_VALUE = 100;
	private final int SEEK_BAR_MAX_VALUE = 200;
	private final int SEEK_BAR_STEP = 5;
	private ImageView imageView;
	private SeekBar seekBarFilterRed;
	private SeekBar seekBarFilterGreen;
	private SeekBar seekBarFilterBlue;
	private TextView textViewInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_filter_image);

		imageView = (ImageView) findViewById(R.id.imageView);
		textViewInfo = (TextView) findViewById(R.id.textViewInfo);
		seekBarFilterRed = (SeekBar) findViewById(R.id.seekBarFilterRed);
		seekBarFilterGreen = (SeekBar) findViewById(R.id.seekBarFilterGreen);
		seekBarFilterBlue = (SeekBar) findViewById(R.id.seekBarFilterBlue);

		prepateSeekBar(seekBarFilterRed);
		prepateSeekBar(seekBarFilterGreen);
		prepateSeekBar(seekBarFilterBlue);
	}

	private void prepateSeekBar(final SeekBar seekBar) {
		seekBar.setMax(SEEK_BAR_MAX_VALUE);
		seekBar.incrementProgressBy(SEEK_BAR_STEP);
		seekBar.setProgress(SEEK_BAR_START_VALUE);
		seekBar.setOnSeekBarChangeListener(this);
	}

	private void setEnabledSeekBars(boolean enabled) {
		seekBarFilterRed.setEnabled(enabled);
		seekBarFilterGreen.setEnabled(enabled);
		seekBarFilterBlue.setEnabled(enabled);
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
		setEnabledSeekBars(false);
		new FilterImage().execute(BitmapFactory.decodeResource(getResources(),
				R.drawable.beautiful_portuguese_girl));
	}

	private double getRedFilterValue() {
		return seekBarFilterRed.getProgress() / 100.;
	}

	private double getGreenFilterValue() {
		return seekBarFilterGreen.getProgress() / 100.;
	}

	private double getBlueFilterValue() {
		return seekBarFilterBlue.getProgress() / 100.;
	}

	private class FilterImage extends AsyncTask<Bitmap, Void, Bitmap> {

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
					R = (int) (Color.red(pixelColor) * getRedFilterValue());
					G = (int) (Color.green(pixelColor) * getGreenFilterValue());
					B = (int) (Color.blue(pixelColor) * getBlueFilterValue());
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
			textViewInfo
					.setText("Red: " + getRedFilterValue() + ", Green: "
							+ getGreenFilterValue() + ", Blue: "
							+ getBlueFilterValue());
		}

		private Bitmap cloneBitmap(Bitmap source) {
			return Bitmap.createBitmap(source.getWidth(), source.getHeight(),
					source.getConfig());
		}

	}

}
