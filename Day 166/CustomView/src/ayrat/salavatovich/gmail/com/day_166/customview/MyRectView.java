package ayrat.salavatovich.gmail.com.day_166.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;

public class MyRectView extends MyCustomView {

	final int MIN_WIDTH = 300;
	final int MIN_HEIGHT = 150;
	final int STROKE_WIDTH = 2;
	private int colorText = Color.GRAY;
	private RectF rect;
	private Path path;

	public MyRectView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		prepare();
	}

	public MyRectView(Context context, AttributeSet attrs) {
		super(context, attrs);
		prepare();
	}

	public MyRectView(Context context) {
		super(context);
		prepare();
	}

	@Override
	protected void prepare() {
		super.prepare();
		setMinimumHeight(MIN_HEIGHT);
		setMinimumWidth(MIN_WIDTH);

		rect = new RectF();
		path = new Path();
	}
	
	public void setColorText(int color) {
		colorText = color;
	}
	
	public void setCrazyColorText() {
		int red = rand.nextInt(255);
		int green = rand.nextInt(255);
		int blue = rand.nextInt(255);

		colorText = Color.rgb(red, green, blue);
	}

	/**
	 * Вызывается при перерисовке компонента
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		paint.setColor(colorView);
		paint.setStrokeWidth(STROKE_WIDTH);
		canvas.drawRect(5, 5, getWidth() - 5, getHeight() - 5, paint);
		drowTextOnView(canvas);
	}
	
	private void drowTextOnView(final Canvas canvas) {
		setCrazyColorText();
		paint.setColor(colorText);
		paint.setTextSize(20);

		rect.set(22, 22, getWidth() - 22, getHeight() - 22);
		path.addArc(rect, 180, 90);

		canvas.drawTextOnPath("Portugal", path, 0, 0, paint); 
		path.reset();

		path.addArc(rect, 0, 120);
		canvas.drawTextOnPath("Lisboa", path, 0, 0, paint);
	}

}
