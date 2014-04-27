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

public class ActivityEngravingEffectImage extends Activity implements
		OnClickListener {

	private ImageView imageView;
	private Button buttonEngravingEffectImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_engraving_effect_image);

		buttonEngravingEffectImage = (Button) findViewById(R.id.buttonEngravingEffectImage);
		buttonEngravingEffectImage.setOnClickListener(this);
		((Button) findViewById(R.id.buttonReset)).setOnClickListener(this);
		imageView = (ImageView) findViewById(R.id.imageView);
	}

	@Override
	public void onClick(View v) {
		if (R.id.buttonEngravingEffectImage == v.getId()) {
			buttonEngravingEffectImage.setEnabled(false);
			new EngravingEffectImage().execute(((BitmapDrawable) imageView
					.getDrawable()).getBitmap());
		} else if (R.id.buttonReset == v.getId())
			imageView.setImageResource(R.drawable.beautiful_portuguese_girl);
	}

	private class EngravingEffectImage extends AsyncTask<Bitmap, Void, Bitmap> {

		@Override
		protected Bitmap doInBackground(Bitmap... images) {
			Bitmap source = images[0];
			ConvolutionMatrix convolutionMatrix = new ConvolutionMatrix(3);
			convolutionMatrix.setAll(0);
			convolutionMatrix.matrix[0][0] = -2;
			convolutionMatrix.matrix[1][1] = 2;
			convolutionMatrix.Factor = 1;
			convolutionMatrix.Offset = 95;
			return ConvolutionMatrix.computeConvolution3x3(source,
					convolutionMatrix);
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			buttonEngravingEffectImage.setEnabled(true);
			imageView.setImageBitmap(result);
		}

	}

}
