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

public class ActivityGaussianBlurEffectImage extends Activity implements
		OnClickListener {

	private ImageView imageView;
	private Button buttonGaussianBlurEffectImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gaussian_blur_effect_image);

		buttonGaussianBlurEffectImage = (Button) findViewById(R.id.buttonGaussianBlurEffectImage);
		buttonGaussianBlurEffectImage.setOnClickListener(this);
		((Button) findViewById(R.id.buttonReset)).setOnClickListener(this);
		imageView = (ImageView) findViewById(R.id.imageView);
	}

	@Override
	public void onClick(View v) {
		if (R.id.buttonGaussianBlurEffectImage == v.getId()) {
			buttonGaussianBlurEffectImage.setEnabled(false);
			new GaussianBlurEffectImage().execute(((BitmapDrawable) imageView
					.getDrawable()).getBitmap());
		} else if (R.id.buttonReset == v.getId())
			imageView.setImageResource(R.drawable.beautiful_portuguese_girl);
	}

	private class GaussianBlurEffectImage extends
			AsyncTask<Bitmap, Void, Bitmap> {

		@Override
		protected Bitmap doInBackground(Bitmap... images) {
			Bitmap source = images[0];
			double[][] GaussianBlurConfig = new double[][] { { 1, 2, 1 },
					{ 2, 4, 2 }, { 1, 2, 1 } };
			ConvolutionMatrix convolutionMatrix = new ConvolutionMatrix(3);
			convolutionMatrix.applyConfig(GaussianBlurConfig);
			convolutionMatrix.Factor = 16;
			convolutionMatrix.Offset = 0;
			return ConvolutionMatrix.computeConvolution3x3(source,
					convolutionMatrix);
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			buttonGaussianBlurEffectImage.setEnabled(true);
			imageView.setImageBitmap(result);
		}

	}

}
