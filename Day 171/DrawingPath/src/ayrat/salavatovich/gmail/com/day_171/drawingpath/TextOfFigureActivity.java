package ayrat.salavatovich.gmail.com.day_171.drawingpath;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;

public class TextOfFigureActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new DrawView(this));
	}

	private class DrawView extends View {

		Paint p;
		Path path;
		String text;

		public DrawView(Context context) {
			super(context);
			p = new Paint(Paint.ANTI_ALIAS_FLAG);
			p.setStrokeWidth(1);
			p.setTextSize(20);
			path = new Path();
			text = "Draw the text, with origin at (x,y), using the specified paint";
		}

		@Override
		protected void onDraw(Canvas canvas) {
			canvas.drawARGB(80, 102, 204, 255);

			// очистка path
			path.reset();

			// черный текст по контуру path-фигуры
			path.addCircle(200, 200, 100, Path.Direction.CW);
			p.setColor(Color.BLACK);
			canvas.drawTextOnPath(text, path, 0, 0, p);

			// очистка path
			path.reset();

			// круг, направление против часовой Path.Direction.CCW
			path.addCircle(500, 200, 100, Path.Direction.CCW);

			// синим цветом рисуем текст и круг
			p.setStyle(Paint.Style.FILL);
			p.setColor(Color.BLUE);
			canvas.drawTextOnPath(text, path, 0, 0, p);
			p.setStyle(Paint.Style.STROKE);
			canvas.drawPath(path, p);

			// зеленым цветом рисуем текст и круг
			path.offset(-300, 250);
			p.setStyle(Paint.Style.FILL);
			p.setColor(Color.GREEN);
			canvas.drawTextOnPath(text, path, 100, 0, p);
			p.setStyle(Paint.Style.STROKE);
			canvas.drawPath(path, p);

			// красным цветом рисуем текст и круг
			path.offset(300, 0);
			p.setStyle(Paint.Style.FILL);
			p.setColor(Color.RED);
			canvas.drawTextOnPath(text, path, 0, 30, p);
			p.setStyle(Paint.Style.STROKE);
			canvas.drawPath(path, p);
		}

	}
}
