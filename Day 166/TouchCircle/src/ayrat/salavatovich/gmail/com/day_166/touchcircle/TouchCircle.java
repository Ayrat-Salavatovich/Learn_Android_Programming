package ayrat.salavatovich.gmail.com.day_166.touchcircle;

import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class TouchCircle extends View {

	private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

	private float initX, initY, radius;
	private final int STROKE_WIDTH = 3;
	private boolean drawing = false;
	private final Random rand = new Random();

	public TouchCircle(Context context) {
		super(context);
		setting();
	}

	public TouchCircle(Context context, AttributeSet attrs) {
		super(context, attrs);
		setting();
	}

	public TouchCircle(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		setting();
	}

	private void setting() {
		if (rand.nextBoolean())
			paint.setStyle(Paint.Style.STROKE);
		else
			paint.setStyle(Paint.Style.FILL);

		paint.setStrokeWidth(STROKE_WIDTH);
		paint.setColor(getCrazyColor());
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),
				MeasureSpec.getSize(heightMeasureSpec));
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (drawing)
			canvas.drawCircle(initX, initY, radius, paint);
	}

	public int getCrazyColor() {
		int red = rand.nextInt(255);
		int green = rand.nextInt(255);
		int blue = rand.nextInt(255);

		return Color.rgb(red, green, blue);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		if (action == MotionEvent.ACTION_MOVE) {
			float x = event.getX();
			float y = event.getY();

			radius = (float) Math.sqrt(Math.pow(x - initX, 2)
					+ Math.pow(y - initY, 2));
		} else if (action == MotionEvent.ACTION_DOWN) {
			initX = event.getX();
			initY = event.getY();
			radius = 1;
			drawing = true;
		} else if (action == MotionEvent.ACTION_UP)
			drawing = false;

		invalidate();

		return true;
	}

}
