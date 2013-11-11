package ayrat.salavatovich.gmail.com.day_18.simplepaint;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

public class Draw2D extends View {

	public Draw2D(Context context) {
		super(context);

		paint = new Paint();
		takeScreenSize(context);
	}

	private void takeScreenSize(Context context) {
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics metrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(metrics);
		screenHeight = metrics.heightPixels - getStatusBarHeight() - 100;
		screenWidth = metrics.widthPixels;
	}

	public int getStatusBarHeight() {
		int result = 0;
		int resourceId = getResources().getIdentifier("status_bar_height",
				"dimen", "android");
		if (resourceId > 0) {
			result = getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		fillCanvas(canvas, Color.WHITE);
		drawSun(canvas);
		drawGrass(canvas);
		drawTextGoodMorning(canvas);
		drawTextRayOfSunshine(canvas);
		drawIcon(canvas);
	}

	private void fillCanvas(Canvas canvas, int color) {
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(color);

		canvas.drawPaint(paint);
	}

	private void drawSun(Canvas canvas) {
		paint.setAntiAlias(true);
		paint.setColor(Color.YELLOW);

		canvas.drawCircle(screenWidth - MARGIN_RIGHT - RADIUS_SUN, 0
				+ MARGIN_TOP + RADIUS_SUN, RADIUS_SUN, paint);
	}

	private void drawTextRayOfSunshine(Canvas canvas) {
		int x = screenWidth - MARGIN_RIGHT - 4 * RADIUS_SUN;
		int y = 0 + MARGIN_TOP + 4 * RADIUS_SUN;

		paint.setColor(Color.GRAY);
		paint.setTextSize(25);
		paint.setStyle(Paint.Style.FILL);

		Rect rect = new Rect();
		canvas.rotate(-45, x + rect.exactCenterX(), y + rect.exactCenterY());

		canvas.drawText("Лучик солнца!", x, y, paint);
		canvas.restore();
	}

	private void drawTextGoodMorning(Canvas canvas) {
		paint.setColor(Color.BLUE);
		paint.setStyle(Paint.Style.FILL);
		paint.setAntiAlias(true);
		paint.setTextSize(40);
		paint.setTextAlign(Align.CENTER);

		canvas.drawText("Доброе утро!", screenWidth / 2, screenHeight / 2,
				paint);
	}

	private void drawGrass(Canvas canvas) {
		paint.setColor(Color.GREEN);

		canvas.drawRect(0 + MARGIN_LEFT, screenHeight - 3 * MARGIN_BOTTOM,
				screenWidth - MARGIN_RIGHT, screenHeight - MARGIN_BOTTOM, paint);
	}

	private void drawIcon(Canvas canvas) {
		Resources res = getResources();
		Bitmap bitmap = BitmapFactory.decodeResource(res,
				R.drawable.ic_launcher);

		canvas.drawBitmap(bitmap, 415, screenHeight - 3 * MARGIN_BOTTOM
				- bitmap.getHeight(), paint);
	}

	private Paint paint;
	private int screenWidth;
	private int screenHeight;
	private final int MARGIN = 20;
	private final int MARGIN_LEFT = MARGIN;
	private final int MARGIN_RIGHT = MARGIN;
	private final int MARGIN_TOP = MARGIN;
	private final int MARGIN_BOTTOM = MARGIN;
	private final int RADIUS_SUN = 50;

}
