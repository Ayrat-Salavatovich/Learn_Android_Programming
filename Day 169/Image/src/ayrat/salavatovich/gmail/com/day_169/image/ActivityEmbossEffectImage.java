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

public class ActivityEmbossEffectImage extends Activity implements
		OnClickListener {

	private ImageView imageView;
	private Button buttonEmbossEffectImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_emboss_effect_image);

		buttonEmbossEffectImage = (Button) findViewById(R.id.buttonEmbossEffectImage);
		buttonEmbossEffectImage.setOnClickListener(this);
		((Button) findViewById(R.id.buttonReset)).setOnClickListener(this);
		imageView = (ImageView) findViewById(R.id.imageView);
	}

	@Override
	public void onClick(View v) {
		if (R.id.buttonEmbossEffectImage == v.getId()) {
			buttonEmbossEffectImage.setEnabled(false);
			new EmbossEffectImage().execute(((BitmapDrawable) imageView
					.getDrawable()).getBitmap());
		} else if (R.id.buttonReset == v.getId())
			imageView.setImageResource(R.drawable.beautiful_portuguese_girl);
	}

	private class EmbossEffectImage extends AsyncTask<Bitmap, Void, Bitmap> {

		@Override
		protected Bitmap doInBackground(Bitmap... images) {
			Bitmap source = images[0];
			double[][] EmbossConfig = new double[][] { { -1, 0, -1 },
					{ 0, 4, 0 }, { -1, 0, -1 } };
			ConvolutionMatrix convolutionMatrix = new ConvolutionMatrix(3);
			convolutionMatrix.applyConfig(EmbossConfig);
			convolutionMatrix.Factor = 1;
			convolutionMatrix.Offset = 127;
			return ConvolutionMatrix.computeConvolution3x3(source,
					convolutionMatrix);
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			buttonEmbossEffectImage.setEnabled(true);
			imageView.setImageBitmap(result);
		}

	}

}
