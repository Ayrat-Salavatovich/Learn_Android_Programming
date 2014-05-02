package ayrat.salavatovich.gmail.com.day_173.polytopoly;

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
		private Paint pBlack;
		private Paint pGray;
		private Paint pWhite;
		private Path path;
		private Path pathDst;
		private Matrix matrix;
		private RectF rectfOne;
		private float[] srcOne;
		private float[] dstOne;
		private RectF rectfTwo;
		private float[] srcTwo;
		private float[] dstTwo;
		private float[] dst2Two;
		private RectF rectfThree;
		private float[] srcThree;
		private float[] dstThree;
		private float[] dst2Three;
		private RectF rectfFour;
		private float[] srcFour;
		private float[] dstFour;
		private float[] dst2Four;

		public DrawView(Context context) {
			super(context);

			p = new Paint();
			p.setStrokeWidth(3);
			p.setStyle(Paint.Style.STROKE);

			pBlack = new Paint();
			pBlack.setColor(Color.BLACK);
			pBlack.setStrokeWidth(3);

			pGray = new Paint();
			pGray.setColor(Color.GRAY);
			pGray.setStrokeWidth(3);

			pWhite = new Paint();
			pWhite.setColor(Color.WHITE);
			pWhite.setStrokeWidth(3);

			path = new Path();
			pathDst = new Path();
			matrix = new Matrix();

			rectfOne = new RectF(100, 100, 200, 200);
			srcOne = new float[] { 100, 100 };
			dstOne = new float[] { 150, 120 };

			rectfTwo = new RectF(400, 100, 500, 200);
			srcTwo = new float[] { 400, 100, 500, 200 };
			dstTwo = new float[] { 350, 300, 550, 500 };
			dst2Two = new float[] { 100, 400, 250, 400 };

			rectfThree = new RectF(650, 100, 750, 200);
			srcThree = new float[] { 650, 100, 750, 200, 750, 100 };
			dstThree = new float[] { 600, 300, 800, 500, 770, 350 };
			dst2Three = new float[] { 850, 200, 950, 200, 890, 100 };

			rectfFour = new RectF(1050, 100, 1150, 200);
			srcFour = new float[] { 1050, 100, 1150, 200, 1150, 100, 1050, 200 };
			dstFour = new float[] { 1000, 300, 1250, 500, 1230, 350, 990, 550 };
			dst2Four = new float[] { 1250, 200, 1350, 200, 1290, 100, 1290, 230 };
		}

		@Override
		protected void onDraw(Canvas canvas) {
			canvas.drawARGB(80, 102, 204, 255);

			useOnePoint(canvas);
			useTwoPoint(canvas);
			useThreePoint(canvas);
			useFourPoint(canvas);
		}

		/**
		 * задает перемещение
		 * 
		 * @param canvas
		 */
		private void useOnePoint(Canvas canvas) {
			int points = 1;
			// зеленый квадрат
			path.reset();
			path.addRect(rectfOne, Path.Direction.CW);
			p.setColor(Color.GREEN);
			canvas.drawPath(path, p);

			// преобразование
			polyToPoly(srcOne, 0, dstOne, 0, points);
			path.transform(matrix, pathDst);

			// синий квадрат
			p.setColor(Color.BLUE);
			canvas.drawPath(pathDst, p);
		}

		/**
		 * задает перемещение, поворот и изменение размера
		 * 
		 * @param canvas
		 */
		private void useTwoPoint(Canvas canvas) {
			int points = 2;
			// зеленый квадрат
			path.reset();
			path.addRect(rectfTwo, Path.Direction.CW);
			p.setColor(Color.GREEN);
			canvas.drawPath(path, p);
			canvas.drawLine(srcTwo[0], srcTwo[1], srcTwo[2], srcTwo[3], pBlack);

			// синий квадрат
			// преобразование
			polyToPoly(srcTwo, 0, dstTwo, 0, points);
			path.transform(matrix, pathDst);
			// рисование
			p.setColor(Color.BLUE);
			canvas.drawPath(pathDst, p);
			canvas.drawLine(dstTwo[0], dstTwo[1], dstTwo[2], dstTwo[3], pBlack);

			// красный квадрат
			// преобразование
			polyToPoly(srcTwo, 0, dst2Two, 0, points);
			path.transform(matrix, pathDst);
			// рисование
			p.setColor(Color.RED);
			canvas.drawPath(pathDst, p);
			canvas.drawLine(dst2Two[0], dst2Two[1], dst2Two[2], dst2Two[3],
					pBlack);
		}

		/**
		 * задает перемещение, поворот, изменение размера и наклон
		 * 
		 * @param canvas
		 */
		private void useThreePoint(Canvas canvas) {
			int points = 3;
			// зеленый квадрат
			path.reset();
			path.addRect(rectfThree, Path.Direction.CW);
			p.setColor(Color.GREEN);
			canvas.drawPath(path, p);
			canvas.drawLine(srcThree[0], srcThree[1], srcThree[2], srcThree[3],
					pBlack);
			canvas.drawLine(srcThree[0], srcThree[1], srcThree[4], srcThree[5],
					pGray);

			// синий квадрат
			// преобразование
			polyToPoly(srcThree, 0, dstThree, 0, points);
			path.transform(matrix, pathDst);
			// рисование
			p.setColor(Color.BLUE);
			canvas.drawPath(pathDst, p);
			canvas.drawLine(dstThree[0], dstThree[1], dstThree[2], dstThree[3],
					pBlack);
			canvas.drawLine(dstThree[0], dstThree[1], dstThree[4], dstThree[5],
					pGray);

			// красный квадрат
			// преобразование
			polyToPoly(srcThree, 0, dst2Three, 0, points);
			path.transform(matrix, pathDst);
			// рисование
			p.setColor(Color.RED);
			canvas.drawPath(pathDst, p);
			canvas.drawLine(dst2Three[0], dst2Three[1], dst2Three[2],
					dst2Three[3], pBlack);
			canvas.drawLine(dst2Three[0], dst2Three[1], dst2Three[4],
					dst2Three[5], pGray);
		}

		/**
		 * задает перемещение, поворот, изменение размера, наклон и перспективу
		 * 
		 * @param canvas
		 */
		private void useFourPoint(Canvas canvas) {
			int points = 4;
			// зеленый квадрат
			path.reset();
			path.addRect(rectfFour, Path.Direction.CW);
			p.setColor(Color.GREEN);
			canvas.drawPath(path, p);
			canvas.drawLine(srcFour[0], srcFour[1], srcFour[2], srcFour[3],
					pBlack);
			canvas.drawLine(srcFour[0], srcFour[1], srcFour[4], srcFour[5],
					pGray);
			canvas.drawLine(srcFour[0], srcFour[1], srcFour[6], srcFour[7],
					pWhite);

			// синий квадрат
			// преобразование
			matrix.setPolyToPoly(srcFour, 0, dstFour, 0, points);
			path.transform(matrix, pathDst);
			// рисование
			p.setColor(Color.BLUE);
			canvas.drawPath(pathDst, p);
			canvas.drawLine(dstFour[0], dstFour[1], dstFour[2], dstFour[3],
					pBlack);
			canvas.drawLine(dstFour[0], dstFour[1], dstFour[4], dstFour[5],
					pGray);
			canvas.drawLine(dstFour[0], dstFour[1], dstFour[6], dstFour[7],
					pWhite);

			// красный квадрат
			// преобразование
			matrix.setPolyToPoly(srcFour, 0, dst2Four, 0, points);
			path.transform(matrix, pathDst);
			// рисование
			p.setColor(Color.RED);
			canvas.drawPath(pathDst, p);
			canvas.drawLine(dst2Four[0], dst2Four[1], dst2Four[2], dst2Four[3],
					pBlack);
			canvas.drawLine(dst2Four[0], dst2Four[1], dst2Four[4], dst2Four[5],
					pGray);
			canvas.drawLine(dst2Four[0], dst2Four[1], dst2Four[6], dst2Four[7],
					pWhite);
		}

		/**
		 * Позволяет задать операции перемещение, изменение размера, наклон,
		 * поворот, перспективу
		 * 
		 * @param src
		 *            - массив исходных координат
		 * @param srcIndex
		 *            - позиция элемента в массиве исходных координат, с
		 *            которого начинаем формировать точки
		 * @param dst
		 *            - массив целевых координат
		 * @param dstIndex
		 *            - позиция элемента в массиве целевых координат, с которого
		 *            начинаем формировать точки
		 * @param pointCount
		 *            - кол-во точек, которые метод polyToPoly возьмет из
		 *            массивов и использует для настройки матрицы
		 */
		private void polyToPoly(float[] src, int srcIndex, float[] dst,
				int dstIndex, int pointCount) {
			matrix.setPolyToPoly(src, srcIndex, dst, dstIndex, pointCount);
		}
	}

}
