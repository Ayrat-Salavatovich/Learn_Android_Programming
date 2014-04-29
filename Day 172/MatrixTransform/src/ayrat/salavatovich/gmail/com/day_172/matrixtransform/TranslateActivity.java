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

public class TranslateActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new DrawView(this));
	}

	class DrawView extends View {

		Paint p;
		Path path;
		Matrix matrix;

		public DrawView(Context context) {
			super(context);
			p = new Paint();
			p.setStrokeWidth(3);
			p.setStyle(Paint.Style.STROKE);

			path = new Path();
			matrix = new Matrix();
		}

		@Override
		protected void onDraw(Canvas canvas) {
			canvas.drawARGB(80, 102, 204, 255);

			// очищаем path
			path.reset();

			// создаем крест в path
			path.addRect(350, 100, 400, 250, Path.Direction.CCW);
			path.addRect(300, 150, 450, 200, Path.Direction.CW);

			// рисуем path зеленым
			p.setColor(Color.RED);
			canvas.drawPath(path, p);

			// очищаем матрицу
			matrix.reset();

			// настраиваем матрицу на перемещение на 300 вправо и 200 вниз
			matrix.setTranslate(300, 200);

			// применяем матрицу к path
			path.transform(matrix);

			// рисуем path синим
			p.setColor(Color.BLUE);
			canvas.drawPath(path, p);

		}

	}

}
