package ayrat.salavatovich.gmail.com.day_174.canvastransform;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;

public class CanvasTranslateActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new DrawView(this));
	}

	class DrawView extends View {

		private Paint p;
		private RectF rectf;

		public DrawView(Context context) {
			super(context);

			p = new Paint();
			p.setStrokeWidth(3);
			p.setStyle(Paint.Style.STROKE);
			rectf = new RectF(100, 100, 200, 200);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			canvas.drawARGB(80, 102, 204, 255);

			// квадрат
			p.setColor(Color.BLACK);
			canvas.drawRect(rectf, p);

			// преобразованный квадрат
			canvas.rotate(30);
			canvas.translate(500, 0);
			p.setColor(Color.GREEN);
			canvas.drawRect(rectf, p);
		}

	}
}
