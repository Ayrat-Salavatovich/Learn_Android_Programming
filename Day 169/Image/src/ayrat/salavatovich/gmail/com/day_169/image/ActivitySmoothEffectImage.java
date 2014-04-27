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

public class ActivitySmoothEffectImage extends Activity implements
		OnSeekBarChangeListener {

	private final int SEEK_BAR_START_VALUE = 1;
	private final int SEEK_BAR_MAX_VALUE = 100;
	private ImageView imageView;
	private SeekBar seekBarSmoothEffect;
	private TextView textViewInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_smooth_effect_image);

		imageView = (ImageView) findViewById(R.id.imageView);
		textViewInfo = (TextView) findViewById(R.id.textViewInfo);
		seekBarSmoothEffect = (SeekBar) findViewById(R.id.seekBarSmoothEffect);

		prepateSeekBar(seekBarSmoothEffect);
	}

	private void prepateSeekBar(final SeekBar seekBar) {
		seekBar.setMax(SEEK_BAR_MAX_VALUE);
		seekBar.setProgress(SEEK_BAR_START_VALUE);
		seekBar.setOnSeekBarChangeListener(this);
	}

	private void setEnabledSeekBar(boolean enabled) {
		seekBarSmoothEffect.setEnabled(enabled);
	}

	private class SmoothEffectImage extends AsyncTask<Bitmap, Void, Bitmap> {

		@Override
		protected Bitmap doInBackground(Bitmap... images) {
			Bitmap source = images[0];
			ConvolutionMatrix convolutionMatrix = new ConvolutionMatrix(3);
			convolutionMatrix.setAll(1);
			convolutionMatrix.matrix[1][1] = getSmoothValue();
			convolutionMatrix.Factor = getSmoothValue() + 8;
			convolutionMatrix.Offset = 1;
			return ConvolutionMatrix.computeConvolution3x3(source,
					convolutionMatrix);
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			setEnabledSeekBar(true);
			imageView.setImageBitmap(result);
			textViewInfo.setText("Smooth: " + getSmoothValue());
		}

	}

	private int getSmoothValue() {
		return seekBarSmoothEffect.getProgress();
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
		new SmoothEffectImage().execute(BitmapFactory.decodeResource(
				getResources(), R.drawable.beautiful_portuguese_girl));
	}

}
