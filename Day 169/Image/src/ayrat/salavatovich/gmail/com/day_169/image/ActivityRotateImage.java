package ayrat.salavatovich.gmail.com.day_169.image;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class ActivityRotateImage extends Activity implements OnClickListener {

	private int DEGREE_ROTATE = 90;
	private ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rotate_image);

		((Button) findViewById(R.id.buttonRotateImage))
				.setOnClickListener(this);
		imageView = (ImageView) findViewById(R.id.imageView);
	}

	@Override
	public void onClick(View v) {
		if (R.id.buttonRotateImage == v.getId())
			rotateImage(((BitmapDrawable) imageView.getDrawable()).getBitmap(),
					DEGREE_ROTATE);
	}

	private void rotateImage(final Bitmap source, float degree) {
		Matrix matrix = new Matrix();
		
		matrix.postRotate(degree);

		// создаем bitmap, повернутый при помощи matrix
		Bitmap destination = Bitmap.createBitmap(source, 0, 0,
				source.getWidth(), source.getHeight(), matrix, true);
		imageView.setImageBitmap(destination);
		imageView.setScaleType(ScaleType.CENTER);
	}

}
