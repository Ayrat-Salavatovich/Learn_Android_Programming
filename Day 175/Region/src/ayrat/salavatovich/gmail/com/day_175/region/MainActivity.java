package ayrat.salavatovich.gmail.com.day_175.region;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new DrawView(this));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private static class DrawView extends View {
		private final Paint p = new Paint();
		private final Rect rect1 = new Rect();
		private final Rect rect2 = new Rect();

		public DrawView(Context context) {
			super(context);
			setFocusable(true);

			p.setAntiAlias(true);
			p.setTextSize(16);
			p.setTextAlign(Paint.Align.CENTER);

			// прямоугольники
			rect1.set(10, 10, 100, 80);
			rect2.set(50, 50, 130, 110);
		}

		private void drawOriginalRects(Canvas canvas, int alpha) {
			// контуры прямоугольников
			p.setStyle(Paint.Style.STROKE);
			p.setColor(Color.RED);
			p.setAlpha(alpha);
			drawCentered(canvas, rect1, p);
			p.setColor(Color.BLUE);
			p.setAlpha(alpha);
			drawCentered(canvas, rect2, p);
		}

		private void drawRgn(Canvas canvas, int color, String str, Region.Op op) {
			if (str != null) {
				p.setColor(Color.BLACK);
				canvas.drawText(str, 80, 24, p);
			}

			// создание региона
			Region rgn = new Region();
			rgn.set(rect1);
			rgn.op(rect2, op);

			p.setColor(color);
			// Разбиваем итоговую область региона на набор непересекающихся
			// прямоугольников
			RegionIterator iter = new RegionIterator(rgn);
			Rect r = new Rect();

			canvas.translate(0, 30);
			p.setColor(color);
			// перебираете прямоугольники
			while (iter.next(r)) {
				canvas.drawRect(r, p);
			}
			drawOriginalRects(canvas, 0x80);
		}

		private static void drawCentered(Canvas canvas, Rect r, Paint p) {
			float inset = p.getStrokeWidth() * 0.5f;
			if (inset == 0) { // catch hairlines
				inset = 0.5f;
			}
			canvas.drawRect(r.left + inset, r.top + inset, r.right - inset,
					r.bottom - inset, p);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			canvas.drawARGB(80, 102, 204, 255);

			canvas.save();
			canvas.translate(getWidth() / 2 - 55, 5);
			drawOriginalRects(canvas, 0xFF);
			canvas.restore();

			p.setStyle(Paint.Style.FILL);

			canvas.save();
			canvas.translate(getWidth() / 2 - 135, 140);
			drawRgn(canvas, Color.RED, "Union", Region.Op.UNION);
			canvas.restore();

			p.setStyle(Paint.Style.FILL);

			canvas.save();
			canvas.translate(getWidth() / 2 - 135, 280);
			drawRgn(canvas, Color.BLUE, "Xor", Region.Op.XOR);
			canvas.restore();

			p.setStyle(Paint.Style.FILL);

			canvas.save();
			canvas.translate(getWidth() / 2 - 135, 420);
			drawRgn(canvas, Color.BLUE, "Replace", Region.Op.REPLACE);
			canvas.restore();

			p.setStyle(Paint.Style.FILL);

			canvas.save();
			canvas.translate(getWidth() / 2 + 25, 140);
			drawRgn(canvas, Color.GREEN, "Difference", Region.Op.DIFFERENCE);
			canvas.restore();

			p.setStyle(Paint.Style.FILL);

			canvas.save();
			canvas.translate(getWidth() / 2 + 25, 280);
			drawRgn(canvas, Color.WHITE, "Intersect", Region.Op.INTERSECT);
			canvas.restore();

			p.setStyle(Paint.Style.FILL);

			canvas.save();
			canvas.translate(getWidth() / 2 + 25, 420);
			drawRgn(canvas, Color.BLUE, "Reverse difference",
					Region.Op.REVERSE_DIFFERENCE);
			canvas.restore();
		}
	}
}
