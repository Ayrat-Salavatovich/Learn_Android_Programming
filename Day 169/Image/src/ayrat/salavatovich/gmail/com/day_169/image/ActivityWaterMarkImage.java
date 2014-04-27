package ayrat.salavatovich.gmail.com.day_169.image;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class ActivityWaterMarkImage extends Activity implements OnClickListener {

	private ImageView imageView;
	private EditText editTextWaterMark;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_water_mark_image);

		((Button) findViewById(R.id.buttonWaterMarkImage))
				.setOnClickListener(this);
		((Button) findViewById(R.id.buttonReset)).setOnClickListener(this);
		imageView = (ImageView) findViewById(R.id.imageView);
		editTextWaterMark = (EditText) findViewById(R.id.editTextWaterMark);
		editTextWaterMark.setText(R.string.edit_text_default_text);
	}

	@Override
	public void onClick(View v) {
		if (R.id.buttonWaterMarkImage == v.getId())
			waterMarkImage(
					((BitmapDrawable) imageView.getDrawable()).getBitmap(),
					editTextWaterMark.getText().toString());
		else if (R.id.buttonReset == v.getId())
			imageView.setImageResource(R.drawable.beautiful_portuguese_girl);
	}

	private void waterMarkImage(final Bitmap source, String text) {
		// создаем bitmap, повернутый при помощи matrix
		Bitmap destination = cloneBitmap(source);
		imageView.setImageBitmap(destination);
		imageView.setScaleType(ScaleType.CENTER);

		Canvas canvas = new Canvas(destination);
		canvas.drawBitmap(source, 0, 0, null);

		Paint paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setAlpha(90);
		paint.setTextSize(31);
		paint.setAntiAlias(true);
		paint.setUnderlineText(true);

		Rect bounds = new Rect();
		paint.getTextBounds(text, 0, text.length(), bounds);
		Point location = new Point((source.getWidth() - bounds.width()) / 2,
				550);

		canvas.drawText(text, location.x, location.y, paint);

		imageView.setImageBitmap(destination);
	}

	private Bitmap cloneBitmap(Bitmap source) {
		return Bitmap.createBitmap(source.getWidth(), source.getHeight(),
				source.getConfig());
	}

}
