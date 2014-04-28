package ayrat.salavatovich.gmail.com.day_171.drawingpath;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;

public class CurvesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new DrawView(this));
	}

	private class DrawView extends View {

		Paint p;
		Path path;
		Point point1;
		Point point21;
		Point point22;

		public DrawView(Context context) {
			super(context);
			// ANTI_ALIAS_FLAG сглаживает кривые при рисовании
			p = new Paint(Paint.ANTI_ALIAS_FLAG);
			p.setStrokeWidth(3);
			path = new Path();

			point1 = new Point(200, 300);
			point21 = new Point(500, 600);
			point22 = new Point(900, 200);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			canvas.drawARGB(80, 102, 204, 255);

			// первая линия
			p.setColor(Color.BLACK);
			canvas.drawLine(100, 100, 600, 100, p);

			// точка отклонения для первой линии
			p.setStyle(Paint.Style.FILL);
			p.setColor(Color.GREEN);
			canvas.drawCircle(point1.x, point1.y, 10, p);

			// очистка path
			path.reset();

			// квадратичная кривая
			path.moveTo(100, 100);
			path.quadTo(point1.x, point1.y, 600, 100);
			p.setStyle(Paint.Style.STROKE);
			canvas.drawPath(path, p);

			// вторая линия
			p.setColor(Color.BLACK);
			canvas.drawLine(400, 400, 1100, 400, p);

			// точки отклонения для второй линии
			p.setStyle(Paint.Style.FILL);
			p.setColor(Color.BLUE);
			canvas.drawCircle(point21.x, point21.y, 10, p);
			canvas.drawCircle(point22.x, point22.y, 10, p);

			// кубическая кривая
			path.reset();
			path.moveTo(400, 400);
			path.cubicTo(point21.x, point21.y, point22.x, point22.y, 1100, 400);
			p.setStyle(Paint.Style.STROKE);
			canvas.drawPath(path, p);
		}

	}

}
