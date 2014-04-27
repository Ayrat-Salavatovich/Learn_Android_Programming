package ayrat.salavatovich.gmail.com.day_169.image;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class ActivitySharpenEffectImage extends Activity implements
		OnSeekBarChangeListener {

	private final int SEEK_BAR_START_VALUE = 8;
	private final int SEEK_BAR_MAX_VALUE = 50;
	private ImageView imageView;
	private SeekBar seekBarSharpenEffect;
	private TextView textViewInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sharpen_effect_image);

		imageView = (ImageView) findViewById(R.id.imageView);
		textViewInfo = (TextView) findViewById(R.id.textViewInfo);
		seekBarSharpenEffect = (SeekBar) findViewById(R.id.seekBarSharpenEffect);

		prepateSeekBar(seekBarSharpenEffect);
	}

	private void prepateSeekBar(final SeekBar seekBar) {
		seekBar.setMax(SEEK_BAR_MAX_VALUE);
		seekBar.setProgress(SEEK_BAR_START_VALUE);
		seekBar.setOnSeekBarChangeListener(this);
	}

	private void setEnabledSeekBar(boolean enabled) {
		seekBarSharpenEffect.setEnabled(enabled);
	}

	private class SharpenEffectImage extends AsyncTask<Bitmap, Void, Bitmap> {

		@Override
		protected Bitmap doInBackground(Bitmap... images) {
			Bitmap source = images[0];
			double[][] SharpConfig = new double[][] { { 0, -2, 0 },
					{ -2, getSharpenWight(), -2 }, { 0, -2, 0 } };
			ConvolutionMatrix convMatrix = new ConvolutionMatrix(3);
			convMatrix.applyConfig(SharpConfig);
			convMatrix.Factor = getSharpenWight() - 8;
			return ConvolutionMatrix.computeConvolution3x3(source, convMatrix);
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			setEnabledSeekBar(true);
			imageView.setImageBitmap(result);
			textViewInfo.setText("Wight: " + getSharpenWight());
		}

	}

	private int getSharpenWight() {
		return seekBarSharpenEffect.getProgress();
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
		new SharpenEffectImage().execute(BitmapFactory.decodeResource(
				getResources(), R.drawable.beautiful_portuguese_girl));
	}

}
