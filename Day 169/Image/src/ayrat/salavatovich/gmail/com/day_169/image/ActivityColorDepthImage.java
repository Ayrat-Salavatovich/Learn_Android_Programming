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

public class ActivityColorDepthImage extends Activity implements
		OnCheckedChangeListener {

	private ImageView imageView;
	private int bitOffset = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_color_depth_image);

		imageView = (ImageView) findViewById(R.id.imageView);
		((RadioGroup) findViewById(R.id.radioGroupColorDepth))
				.setOnCheckedChangeListener(this);
	}

	private class ColorDepth extends AsyncTask<Bitmap, Void, Bitmap> {

		@Override
		protected Bitmap doInBackground(Bitmap... images) {
			Bitmap source = images[0];

			if (bitOffset == 0)
				return source;

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
					R = Color.red(pixelColor);
					G = Color.green(pixelColor);
					B = Color.blue(pixelColor);

					// устанавливаем смещение цвета
					R = ((R + (bitOffset / 2))
							- ((R + (bitOffset / 2)) % bitOffset) - 1);
					if (R < 0)
						R = 0;

					G = ((G + (bitOffset / 2))
							- ((G + (bitOffset / 2)) % bitOffset) - 1);
					if (G < 0)
						G = 0;

					B = ((B + (bitOffset / 2))
							- ((B + (bitOffset / 2)) % bitOffset) - 1);
					if (B < 0)
						B = 0;

					destination.setPixel(x, y, Color.argb(A, R, G, B));
				}
			}

			return destination;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			setEnabledRadioGroup(true);
			imageView.setImageBitmap(result);
		}

		private Bitmap cloneBitmap(Bitmap source) {
			return Bitmap.createBitmap(source.getWidth(), source.getHeight(),
					source.getConfig());
		}

	}

	private void setEnabledRadioGroup(boolean enabled) {
		((RadioButton) findViewById(R.id.radioButton0BitOffset))
				.setEnabled(enabled);
		((RadioButton) findViewById(R.id.radioButton32BitOffset))
				.setEnabled(enabled);
		((RadioButton) findViewById(R.id.radioButton64BitOffset))
				.setEnabled(enabled);
		((RadioButton) findViewById(R.id.radioButton128BitOffset))
				.setEnabled(enabled);
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.radioButton0BitOffset:

			bitOffset = 0;

			break;

		case R.id.radioButton32BitOffset:

			setEnabledRadioGroup(false);
			bitOffset = 32;

			break;

		case R.id.radioButton64BitOffset:

			setEnabledRadioGroup(false);
			bitOffset = 64;

			break;

		case R.id.radioButton128BitOffset:

			setEnabledRadioGroup(false);
			bitOffset = 128;
		}

		new ColorDepth().execute(BitmapFactory.decodeResource(getResources(),
				R.drawable.beautiful_portuguese_girl));
	}

}
