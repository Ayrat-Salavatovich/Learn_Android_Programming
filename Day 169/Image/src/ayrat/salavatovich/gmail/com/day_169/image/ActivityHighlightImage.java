package ayrat.salavatovich.gmail.com.day_169.image;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.BlurMaskFilter.Blur;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class ActivityHighlightImage extends Activity implements OnClickListener {

	private ImageView imageView;
	private Button buttonHighlightImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_highlight_image);

		buttonHighlightImage = (Button) findViewById(R.id.buttonHighlightImage);
		buttonHighlightImage.setOnClickListener(this);
		((Button) findViewById(R.id.buttonReset)).setOnClickListener(this);
		imageView = (ImageView) findViewById(R.id.imageView);
	}

	@Override
	public void onClick(View v) {
		if (R.id.buttonHighlightImage == v.getId())
			highlightImage(((BitmapDrawable) imageView.getDrawable())
					.getBitmap());
		else if (R.id.buttonReset == v.getId())
			imageView.setImageResource(R.drawable.beautiful_portuguese_girl);
	}

	public void highlightImage(Bitmap source) {
		// получаем размеры изображения
		final int width = source.getWidth();
		final int height = source.getHeight();
		// создадим новый битмап, который станет итоговым
		Bitmap destination = Bitmap.createBitmap(width + 96, height + 96,
				Bitmap.Config.ARGB_8888);
		// подключаем холст
		Canvas canvas = new Canvas(destination);
		// установим цвет по умолчанию
		canvas.drawColor(0, PorterDuff.Mode.CLEAR);

		// создадим размытие для прозрачности
		Paint paintBlur = new Paint();
		paintBlur.setMaskFilter(new BlurMaskFilter(15, Blur.NORMAL));
		int[] offsetXY = new int[2];
		// получим прозрачный слепок из изображения
		Bitmap bitmapAlpha = source.extractAlpha(paintBlur, offsetXY);
		// готовимся к рисованию
		Paint paintAlphaColor = new Paint();
		paintAlphaColor.setColor(0xFFFFFFFF);
		// закрашиваем цветом цветной слепок (bitmap)
		canvas.drawBitmap(bitmapAlpha, offsetXY[0], offsetXY[1],
				paintAlphaColor);
		// освобождаем ресурсы
		bitmapAlpha.recycle();

		// рисуем исходник
		canvas.drawBitmap(source, 0, 0, null);

		// обновляем рисунок
		imageView.setImageBitmap(destination);
	}

}
