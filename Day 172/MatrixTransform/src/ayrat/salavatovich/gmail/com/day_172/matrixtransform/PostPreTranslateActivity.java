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

public class PostPreTranslateActivity extends Activity {

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
			matrix = new Matrix();
		}

		@Override
		protected void onDraw(Canvas canvas) {
			canvas.drawARGB(80, 102, 204, 255);

			p.setColor(Color.BLACK);
			canvas.drawCircle(400, 200, 10, p);

			// прямоугольник
			path.reset();
			path.addRect(300, 100, 500, 300, Path.Direction.CW);
			canvas.drawPath(path, p);

			// перемещение после поворота
			matrix.reset();
			matrix.setRotate(45, 400, 200);
			matrix.postTranslate(500, 0);
			path.transform(matrix, pathDst);
			p.setColor(Color.GREEN);
			canvas.drawPath(pathDst, p);

			// перемещение до поворота
			matrix.reset();
			matrix.setRotate(45, 400, 200);
			matrix.preTranslate(500, 0);
			path.transform(matrix, pathDst);
			p.setColor(Color.RED);
			canvas.drawPath(pathDst, p);
		}

	}

}
