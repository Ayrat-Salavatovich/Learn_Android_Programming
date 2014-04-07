package ayrat.salavatovich.gmail.com.day_143.circletext;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Paint.Style;
import android.graphics.Path.Direction;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class CircleTextActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new CircleTextView(CircleTextActivity.this));
	}

	private static class CircleTextView extends View {

		private Paint cPaint;
		private Paint tPaint;
		private Path circle;
		private Point center;
		private Context ctx;
		private float radius = 107f;

		public CircleTextView(Context context) {
			super(context);

			this.ctx = context;
			cPaint = new Paint();
			tPaint = new Paint();
			circle = new Path();
			center = new Point();
			setCenterPointXY();
		}

		@Override
		protected void onDraw(Canvas canvas) {
			setBackgroundGradient();
			setUpCircle();
			setUpText();

			circle.addCircle(center.x, center.y, radius, Direction.CW);

			canvas.drawPath(circle, cPaint);
			canvas.drawTextOnPath(
					getResources().getString(R.string.best_country), circle, 0,
					32, tPaint);

			if (Build.VERSION.SDK_INT >= 11) {
				setLayerType(View.LAYER_TYPE_SOFTWARE, null);
			}
		}

		void setCenterPointXY() {
			WindowManager wm = (WindowManager) ctx
					.getSystemService(Context.WINDOW_SERVICE);
			Display display = wm.getDefaultDisplay();
			display.getSize(center);
			center.x /= 2;
			center.y /= 2;
		}

		void setBackgroundGradient() {
			setBackgroundResource(R.drawable.background);
		}

		void setUpCircle() {
			cPaint.setColor(Color.BLUE);
			cPaint.setStyle(Style.STROKE);
			cPaint.setStrokeWidth(1.7f);
			// сглаживание
			cPaint.setAntiAlias(true);
		}

		void setUpText() {
			tPaint.setTextSize(36);
			tPaint.setColor(Color.WHITE);
			// сглаживание
			tPaint.setAntiAlias(true);
		}

	}

}