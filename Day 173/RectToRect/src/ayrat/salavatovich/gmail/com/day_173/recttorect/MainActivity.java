package ayrat.salavatovich.gmail.com.day_173.recttorect;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new DrawView(this));
	}

	class DrawView extends View {

		private Paint p;
		private Path path;
		private Path pathDst;
		private RectF rectfBounds;
		private RectF rectfDst;
		private Matrix matrix;

		public DrawView(Context context) {
			super(context);

			p = new Paint();
			p.setStrokeWidth(3);
			p.setStyle(Paint.Style.STROKE);

			rectfDst = new RectF();
			rectfBounds = new RectF();

			path = new Path();
			path.addCircle(200, 100, 50, Path.Direction.CW);
			path.addCircle(200, 225, 75, Path.Direction.CW);
			path.addCircle(200, 400, 100, Path.Direction.CW);

			pathDst = new Path();
			matrix = new Matrix();
		}

		@Override
		protected void onDraw(Canvas canvas) {
			canvas.drawARGB(80, 102, 204, 255);

			rectfDst.set(500, 50, 800, 150);

			// снеговик
			p.setColor(Color.BLUE);
			canvas.drawPath(path, p);

			// граница снеговика
			path.computeBounds(rectfBounds, true);
			p.setColor(Color.GREEN);
			canvas.drawRect(rectfBounds, p);

			// START – в левой (или верхней) стороне
			drawSnowmanInRect(canvas, Matrix.ScaleToFit.START);

			// смещаем координаты прямоугольника (rectfDst) вниз на 150
			rectfDst.offset(0, 150);

			// CENTER – в центре
			drawSnowmanInRect(canvas, Matrix.ScaleToFit.CENTER);

			// смещаем координаты прямоугольника (rectfDst) вниз на 150
			rectfDst.offset(0, 150);

			// END – в правой (или нижней) стороне
			drawSnowmanInRect(canvas, Matrix.ScaleToFit.END);

			// смещаем координаты прямоугольника (rectfDst) вниз на 150
			rectfDst.offset(0, 150);

			// FILL – не сохранять соотношение сторон, а растянуть первый
			// прямоугольник так, чтобы он полностью заполнил второй
			drawSnowmanInRect(canvas, Matrix.ScaleToFit.FILL);

		}

		// START – в левой (или верхней) стороне
		// CENTER – в центре
		// END – в правой (или нижней) стороне
		// FILL – не сохранять соотношение сторон, а растянуть первый
		// прямоугольник так, чтобы он полностью заполнил второй
		private void scaleToFit(final Path pathDst, final Matrix matrix,
				final RectF rectfBounds, final RectF rectfDst,
				final Matrix.ScaleToFit stf) {
			// очищаем матрицу
			matrix.reset();
			// настраиваем матрицу так, чтобы rectfBounds поместился в rectfDst
			matrix.setRectToRect(rectfBounds, rectfDst, stf);
		}

		private void drawSnowmanInRect(Canvas canvas,
				final Matrix.ScaleToFit stf) {
			// рамка
			p.setColor(Color.BLACK);
			canvas.drawRect(rectfDst, p);

			scaleToFit(pathDst, matrix, rectfBounds, rectfDst, stf);
			path.transform(matrix, pathDst);

			// снеговик
			p.setColor(Color.BLUE);
			canvas.drawPath(pathDst, p);
		}

	}

}
