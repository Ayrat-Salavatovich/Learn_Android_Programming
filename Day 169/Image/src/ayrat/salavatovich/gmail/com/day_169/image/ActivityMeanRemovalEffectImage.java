package ayrat.salavatovich.gmail.com.day_169.image;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class ActivityMeanRemovalEffectImage extends Activity implements
		OnClickListener {

	private ImageView imageView;
	private Button buttonMeanRemovalEffectImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mean_removal_effect_image);

		buttonMeanRemovalEffectImage = (Button) findViewById(R.id.buttonMeanRemovalEffectImage);
		buttonMeanRemovalEffectImage.setOnClickListener(this);
		((Button) findViewById(R.id.buttonReset)).setOnClickListener(this);
		imageView = (ImageView) findViewById(R.id.imageView);
	}

	@Override
	public void onClick(View v) {
		if (R.id.buttonMeanRemovalEffectImage == v.getId()) {
			buttonMeanRemovalEffectImage.setEnabled(false);
			new MeanRemovalEffectImage().execute(((BitmapDrawable) imageView
					.getDrawable()).getBitmap());
		} else if (R.id.buttonReset == v.getId())
			imageView.setImageResource(R.drawable.beautiful_portuguese_girl);
	}

	private class MeanRemovalEffectImage extends
			AsyncTask<Bitmap, Void, Bitmap> {

		@Override
		protected Bitmap doInBackground(Bitmap... images) {
			Bitmap source = images[0];
			double[][] MeanRemovalConfig = new double[][] { { -1, -1, -1 },
					{ -1, 9, -1 }, { -1, -1, -1 } };
			ConvolutionMatrix convolutionMatrix = new ConvolutionMatrix(3);
			convolutionMatrix.applyConfig(MeanRemovalConfig);
			convolutionMatrix.Factor = 1;
			convolutionMatrix.Offset = 0;
			return ConvolutionMatrix.computeConvolution3x3(source,
					convolutionMatrix);
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			buttonMeanRemovalEffectImage.setEnabled(true);
			imageView.setImageBitmap(result);
		}

	}

}
