package ayrat.salavatovich.gmail.com.day_166.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

public class MyCircleView extends MyCustomView {

	final int MIN_WIDTH = 300;
	final int MIN_HEIGHT = 150;
	final int STROKE_WIDTH = 2;

	public MyCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		prepare();
	}

	public MyCircleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		prepare();
	}

	public MyCircleView(Context context) {
		super(context);
		prepare();
	}

	@Override
	public void prepare() {
		super.prepare();
		setMinimumHeight(MIN_HEIGHT);
		setMinimumWidth(MIN_WIDTH);
	}

	/**
	 * Вызывается при перерисовке компонента
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		int width = getWidth();
		int height = getHeight();
		int radius;

		if (width > height)
			radius = height / 2;
		else
			radius = width / 2;

		paint.setStyle(Paint.Style.FILL);
		paint.setAntiAlias(true);

		paint.setColor(colorView);
		canvas.drawCircle(width / 2, height / 2, radius, paint);
	}

}
