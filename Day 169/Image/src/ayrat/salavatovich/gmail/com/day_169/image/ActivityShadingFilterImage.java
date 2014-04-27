package ayrat.salavatovich.gmail.com.day_169.image;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class ActivityShadingFilterImage extends Activity implements
		OnCheckedChangeListener {

	private ImageView imageView;
	private int shadingColor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shading_filter_image);

		imageView = (ImageView) findViewById(R.id.imageView);
		((RadioGroup) findViewById(R.id.radioGroupShadingFilter))
				.setOnCheckedChangeListener(this);
	}

	private class ShadingFilterImage extends AsyncTask<Bitmap, Void, Bitmap> {

		@Override
		protected Bitmap doInBackground(Bitmap... images) {
			Bitmap source = images[0];

			if (shadingColor == -1)
				return source;

			// получаем размеры изображения
			final int width = source.getWidth();
			final int height = source.getHeight();
			Bitmap destination = Bitmap.createBitmap(width, height,
					Bitmap.Config.ARGB_8888);
			// получаем массив пикселей из изображения
			int[] pixels = new int[width * height];
			source.getPixels(pixels, 0, width, 0, 0, width, height);
			// позиция пикселя в массиве pixels соотвествующая пикселю в 2D
			// позиции изображения
			int index;

			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					index = y * width + x;
					pixels[index] &= shadingColor;
				}
			}

			destination.setPixels(pixels, 0, width, 0, 0, width, height);

			return destination;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			setEnabledRadioGroup(true);
			imageView.setImageBitmap(result);
		}

	}

	private void setEnabledRadioGroup(boolean enabled) {
		((RadioButton) findViewById(R.id.radioButtonShadingRed))
				.setEnabled(enabled);
		((RadioButton) findViewById(R.id.radioButtonShadingGreen))
				.setEnabled(enabled);
		((RadioButton) findViewById(R.id.radioButtonShadingBlue))
				.setEnabled(enabled);
		((RadioButton) findViewById(R.id.radioButtonShadingMagenta))
				.setEnabled(enabled);
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.radioButtonShadingNone:

			shadingColor = -1;

			break;

		case R.id.radioButtonShadingRed:

			setEnabledRadioGroup(false);
			shadingColor = Color.RED;

			break;

		case R.id.radioButtonShadingGreen:

			setEnabledRadioGroup(false);
			shadingColor = Color.GREEN;

			break;

		case R.id.radioButtonShadingBlue:

			setEnabledRadioGroup(false);
			shadingColor = Color.BLUE;

			break;

		case R.id.radioButtonShadingMagenta:

			setEnabledRadioGroup(false);
			shadingColor = Color.MAGENTA;

			break;

		case R.id.radioButtonShadingViolet:

			setEnabledRadioGroup(false);
			shadingColor = Color.rgb(127, 0, 255);
		}

		new ShadingFilterImage().execute(BitmapFactory.decodeResource(
				getResources(), R.drawable.beautiful_portuguese_girl));
	}

}
