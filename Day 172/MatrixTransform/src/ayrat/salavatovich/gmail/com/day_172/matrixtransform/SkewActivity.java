package ayrat.salavatovich.gmail.com.day_172.matrixtransform;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;

public class SkewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new DrawView(this));
	}

	class DrawView extends View {

		Paint p;
		Path path;
		Path pathDst;
		Matrix matrix;

		public DrawView(Context context) {
			super(context);
			p = new Paint();
			p.setStrokeWidth(3);
			p.setStyle(Paint.Style.STROKE);

			path = new Path();
			pathDst = new Path();
			path.addRect(100, 100, 200, 200, Path.Direction.CW);

			matrix = new Matrix();
		}

		@Override
		protected void onDraw(Canvas canvas) {
			canvas.drawARGB(80, 102, 204, 255);

			p.setColor(Color.BLACK);
			canvas.drawPath(path, p);

			p.setColor(Color.GREEN);

			// перемещение на 200 вправо
			// и наклон по вертикали на 0.5
			// точка наклона - слева
			matrix.reset();
			matrix.setTranslate(200, 0);
			matrix.postSkew(0.0f, 0.5f, 300, 100);
			path.transform(matrix, pathDst);
			canvas.drawPath(pathDst, p);
			canvas.drawCircle(300, 100, 5, p);

			// перемещение на 400 вправо
			// и наклон по вертикали на 0.5
			// точка наклона - справа
			matrix.reset();
			matrix.setTranslate(400, 0);
			matrix.postSkew(0.0f, 0.5f, 600, 100);
			path.transform(matrix, pathDst);
			canvas.drawPath(pathDst, p);
			canvas.drawCircle(600, 100, 5, p);

			p.setColor(Color.BLUE);

			// перемещение на 150 вниз
			// и наклон по горизонтали на 0.5
			// точка наклона - сверху
			matrix.reset();
			matrix.setTranslate(0, 150);
			matrix.postSkew(0.5f, 0.0f, 100, 250);
			path.transform(matrix, pathDst);
			canvas.drawPath(pathDst, p);
			canvas.drawCircle(100, 250, 5, p);

			// перемещение на 300 вниз
			// и наклон по горизонтали на 0.5
			// точка наклона - снизу
			matrix.reset();
			matrix.setTranslate(0, 300);
			matrix.postSkew(0.5f, 0.0f, 100, 500);
			path.transform(matrix, pathDst);
			canvas.drawPath(pathDst, p);
			canvas.drawCircle(100, 500, 5, p);
		}

	}

}
