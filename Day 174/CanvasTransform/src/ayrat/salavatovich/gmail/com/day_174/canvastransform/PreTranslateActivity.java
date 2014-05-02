package ayrat.salavatovich.gmail.com.day_174.canvastransform;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;

public class PreTranslateActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new DrawView(this));
	}

	class DrawView extends View {

		private Paint p;
		private Matrix matrix;
		private RectF rectf;
		private Path path;

		public DrawView(Context context) {
			super(context);

			p = new Paint();
			p.setStrokeWidth(3);
			p.setStyle(Paint.Style.STROKE);

			matrix = new Matrix();

			rectf = new RectF(100, 100, 200, 200);

			path = new Path();
		}

		@Override
		protected void onDraw(Canvas canvas) {
			canvas.drawARGB(80, 102, 204, 255);

			// квадрат
			path.reset();
			path.addRect(rectf, Path.Direction.CW);
			p.setColor(Color.BLACK);
			canvas.drawPath(path, p);

			// преобразованный квадрат
			matrix.reset();
			matrix.preRotate(30);
			matrix.preTranslate(500, 0);
			path.transform(matrix);
			p.setColor(Color.BLUE);
			canvas.drawPath(path, p);
		}

	}
}
