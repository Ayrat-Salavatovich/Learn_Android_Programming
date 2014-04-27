package ayrat.salavatovich.gmail.com.day_169.image;

import java.util.Random;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityChannelMixerImage extends Activity implements
		OnClickListener {

	private ImageView imageView;
	private Button buttonChannelMixerImage;
	private TextView textViewInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_channel_mixer_image);

		buttonChannelMixerImage = ((Button) findViewById(R.id.buttonChannelMixerImage));
		buttonChannelMixerImage.setOnClickListener(this);
		((Button) findViewById(R.id.buttonReset)).setOnClickListener(this);
		imageView = (ImageView) findViewById(R.id.imageView);
		textViewInfo = (TextView) findViewById(R.id.textViewInfo);
	}

	@Override
	public void onClick(View v) {
		if (R.id.buttonChannelMixerImage == v.getId()) {
			buttonChannelMixerImage.setEnabled(false);
			textViewInfo.setText(R.string.text_view_empty);
			new ChannelMixerImage().execute(((BitmapDrawable) imageView
					.getDrawable()).getBitmap());
		} else if (R.id.buttonReset == v.getId()) {
			textViewInfo.setText(R.string.text_view_empty);
			imageView.setImageResource(R.drawable.beautiful_portuguese_girl);
		}
	}

	private class ChannelMixerImage extends AsyncTask<Bitmap, Void, Bitmap> {

		private final int MAX_VALUE = 255;
		private String color;
		private int percentBoost;

		@Override
		protected Bitmap doInBackground(Bitmap... images) {
			Bitmap source = images[0];
			Bitmap destination = cloneBitmap(source);
			// Alpha (прозрачность)
			int A;
			// Red (красный), Green (зелёный), Blue (синий)
			int R, G, B;
			int pixelColor;
			int typeChannelMixer = new Random().nextInt(3);
			percentBoost = new Random().nextInt(100);

			for (int y = 0; y < source.getHeight(); y++) {
				for (int x = 0; x < source.getWidth(); x++) {
					pixelColor = source.getPixel(x, y);
					A = Color.alpha(pixelColor);
					R = Color.red(pixelColor);
					G = Color.green(pixelColor);
					B = Color.blue(pixelColor);
					// преобразуем изображение
					if (typeChannelMixer == 0) {
						R = (int) (R * (1 + percentBoost));
						if (R > MAX_VALUE)
							R = MAX_VALUE;
						color = "Red";
					} else if (typeChannelMixer == 1) {
						G = (int) (G * (1 + percentBoost));
						if (G > MAX_VALUE)
							G = MAX_VALUE;
						color = "Green";
					} else if (typeChannelMixer == 2) {
						B = (int) (B * (1 + percentBoost));
						if (B > MAX_VALUE)
							B = MAX_VALUE;
						color = "Blue";
					}
					// полученный результа записываем в этот же пиксель
					destination.setPixel(x, y, Color.argb(A, R, G, B));
				}
			}

			return destination;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			buttonChannelMixerImage.setEnabled(true);
			imageView.setImageBitmap(result);
			textViewInfo.setText("Boost: " + color + " " + percentBoost + "%");
		}

		private Bitmap cloneBitmap(Bitmap source) {
			return Bitmap.createBitmap(source.getWidth(), source.getHeight(),
					source.getConfig());
		}

	}

}
