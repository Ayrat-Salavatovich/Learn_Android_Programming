package ayrat.salavatovich.gmail.com.day_171.drawingpath;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;

public class SimpleShapesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new DrawView(this));
	}

	private class DrawView extends View {

		Paint p;
		RectF rectf;
		Path path;
		Path path1;

		public DrawView(Context context) {
			super(context);
			p = new Paint();
			p.setStrokeWidth(3);
			p.setStyle(Paint.Style.STROKE);

			rectf = new RectF(350, 100, 400, 150);
			path = new Path();
			path1 = new Path();
		}

		@Override
		protected void onDraw(Canvas canvas) {
			canvas.drawARGB(80, 102, 204, 255);

			// очистка path
			path.reset();

			// угол
			path.moveTo(100, 100);
			path.lineTo(150, 200);
			path.lineTo(50, 200);

			// треугольник
			path.moveTo(250, 100);
			path.lineTo(300, 200);
			path.lineTo(200, 200);
			// фигура закрывается
			path.close();

			// квадрат и круг. Path.Direction.CW (по часовой) и
			// Path.Direction.CCW (против часовой)
			path.addRect(rectf, Path.Direction.CW);
			path.addCircle(450, 150, 25, Path.Direction.CW);

			// рисование path
			p.setColor(Color.BLACK);
			canvas.drawPath(path, p);

			// очистка path1
			path1.reset();

			// две пересекающиеся линии
			path1.moveTo(50, 50);
			path1.lineTo(500, 250);
			path1.moveTo(500, 50);
			path1.lineTo(50, 250);

			// рисование path1
			p.setColor(Color.GREEN);
			canvas.drawPath(path1, p);

			// добавление path1 к path
			path.addPath(path1);

			// смещение
			path.offset(500, 100);

			// рисование path
			p.setColor(Color.BLUE);
			canvas.drawPath(path, p);
		}

	}

}
