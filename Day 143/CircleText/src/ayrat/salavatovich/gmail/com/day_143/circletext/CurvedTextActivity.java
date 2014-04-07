package ayrat.salavatovich.gmail.com.day_143.circletext;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class CurvedTextActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new CurvedTextView(CurvedTextActivity.this));
	}

	private static class CurvedTextView extends View {

		private Path path;
		private Paint paint;
		private Point center;
		private Context ctx;

		public CurvedTextView(Context context) {
			super(context);

			this.ctx = context;
			path = new Path();
			paint = new Paint();
			center = new Point();
			setCenterPointXY();
		}

		void setCenterPointXY() {
			WindowManager wm = (WindowManager) ctx
					.getSystemService(Context.WINDOW_SERVICE);
			Display display = wm.getDefaultDisplay();
			display.getSize(center);
			center.x /= 2;
			center.y /= 2;
		}

		@Override
		protected void onDraw(Canvas canvas) {
			setBackgroundGradient();
			setUpText();

			path.moveTo(20, 40);
			path.cubicTo(500, 100, 200, 600, 940, 280);

			canvas.drawPath(path, paint);

			paint.setStyle(Paint.Style.FILL_AND_STROKE);
			paint.setTextSize(50);

			paint.setColor(Color.GRAY);
			canvas.drawTextOnPath(
					getResources().getString(R.string.best_country), path, 102,
					-4, paint);
			paint.setColor(Color.GREEN);
			canvas.drawTextOnPath(
					getResources().getString(R.string.best_country), path, 100,
					-2, paint);

		}

		void setBackgroundGradient() {
			setBackgroundResource(R.drawable.background);
		}

		void setUpText() {
			paint.setStyle(Paint.Style.STROKE);
			paint.setColor(Color.WHITE);
			paint.setStrokeWidth(3);
			// сглаживание
			paint.setAntiAlias(true);
		}
	}

}
