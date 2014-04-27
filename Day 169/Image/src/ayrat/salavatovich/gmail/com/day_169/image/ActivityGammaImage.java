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

public class ActivityGammaImage extends Activity implements
		OnSeekBarChangeListener {

	private final int SEEK_BAR_START_VALUE = 100;
	private final int SEEK_BAR_MAX_VALUE = 200;
	private final int SEEK_BAR_STEP = 5;
	private ImageView imageView;
	private SeekBar seekBarGammaRed;
	private SeekBar seekBarGammaGreen;
	private SeekBar seekBarGammaBlue;
	private TextView textViewInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gamma_image);

		imageView = (ImageView) findViewById(R.id.imageView);
		textViewInfo = (TextView) findViewById(R.id.textViewInfo);
		seekBarGammaRed = (SeekBar) findViewById(R.id.seekBarGammaRed);
		seekBarGammaGreen = (SeekBar) findViewById(R.id.seekBarGammaGreen);
		seekBarGammaBlue = (SeekBar) findViewById(R.id.seekBarGammaBlue);

		prepateSeekBar(seekBarGammaRed);
		prepateSeekBar(seekBarGammaGreen);
		prepateSeekBar(seekBarGammaBlue);
	}

	private void prepateSeekBar(final SeekBar seekBar) {
		seekBar.setMax(SEEK_BAR_MAX_VALUE);
		seekBar.incrementProgressBy(SEEK_BAR_STEP);
		seekBar.setProgress(SEEK_BAR_START_VALUE);
		seekBar.setOnSeekBarChangeListener(this);
	}

	private void setEnabledSeekBars(boolean enabled) {
		seekBarGammaRed.setEnabled(enabled);
		seekBarGammaGreen.setEnabled(enabled);
		seekBarGammaBlue.setEnabled(enabled);
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
		new GammaImage().execute(BitmapFactory.decodeResource(getResources(),
				R.drawable.beautiful_portuguese_girl));
	}

	private double getRedGammaValue() {
		return seekBarGammaRed.getProgress() / 100.;
	}

	private double getGreenGammaValue() {
		return seekBarGammaGreen.getProgress() / 100.;
	}

	private double getBlueGammaValue() {
		return seekBarGammaBlue.getProgress() / 100.;
	}

	private class GammaImage extends AsyncTask<Bitmap, Void, Bitmap> {

		private final int MAX_SIZE = 256;
		private final int MAX_VALUE = 255;
		private final double REVERSE = 1.;

		@Override
		protected Bitmap doInBackground(Bitmap... images) {
			Bitmap source = images[0];
			Bitmap destination = cloneBitmap(source);
			// Alpha (прозрачность)
			int A;
			// Red (красный), Green (зелёный), Blue (синий)
			int R, G, B;
			int pixelColor;

			// gamma arrays
			int[] gammaR = new int[MAX_SIZE];
			int[] gammaG = new int[MAX_SIZE];
			int[] gammaB = new int[MAX_SIZE];

			settingValuesGammaChannels(gammaR, gammaG, gammaB);

			for (int y = 0; y < source.getHeight(); y++) {
				for (int x = 0; x < source.getWidth(); x++) {
					pixelColor = source.getPixel(x, y);
					A = Color.alpha(pixelColor);
					R = gammaR[Color.red(pixelColor)];
					G = gammaG[Color.green(pixelColor)];
					B = gammaB[Color.blue(pixelColor)];

					destination.setPixel(x, y, Color.argb(A, R, G, B));
				}
			}

			return destination;
		}

		private void settingValuesGammaChannels(final int[] gammaR,
				final int[] gammaG, final int[] gammaB) {
			for (int i = 0; i < MAX_SIZE; ++i) {
				gammaR[i] = (int) Math.min(
						MAX_VALUE,
						(int) (((double) MAX_VALUE * Math.pow(i
								/ (double) MAX_VALUE, REVERSE
								/ getRedGammaValue())) + .5));
				gammaG[i] = (int) Math.min(
						MAX_VALUE,
						(int) (((double) MAX_VALUE * Math.pow(i
								/ (double) MAX_VALUE, REVERSE
								/ getGreenGammaValue())) + .5));
				gammaB[i] = (int) Math.min(
						MAX_VALUE,
						(int) (((double) MAX_VALUE * Math.pow(i
								/ (double) MAX_VALUE, REVERSE
								/ getBlueGammaValue())) + .5));
			}
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			setEnabledSeekBars(true);
			imageView.setImageBitmap(result);
			textViewInfo.setText("Gamma Image: (Red, Green, Blue) = ("
					+ getRedGammaValue() + ", " + getGreenGammaValue() + ", "
					+ getBlueGammaValue() + ")");
		}

		private Bitmap cloneBitmap(Bitmap source) {
			return Bitmap.createBitmap(source.getWidth(), source.getHeight(),
					source.getConfig());
		}

	}

}
