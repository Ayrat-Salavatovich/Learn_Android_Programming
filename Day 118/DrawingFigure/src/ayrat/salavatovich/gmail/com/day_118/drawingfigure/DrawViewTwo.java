package ayrat.salavatovich.gmail.com.day_118.drawingfigure;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.SurfaceHolder;

public class DrawViewTwo extends DrawSurfaceView {

	public DrawViewTwo(Context context) {
		super(context);
	}

	protected SurfaceHolder.Callback getCallback() {
		return new SurfaceHolder.Callback() {

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
				// Был изменен формат или размер SurfaceView
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				// SurfaceView создан и готов к отображению информации
				drawThread = new MyThread(getHolder());
				drawThread.setRunning(true);
				drawThread.start();
			}

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				// Вызывается перед тем, как SurfaceView будет уничтожен
				drawThread.setRunning(false);

				// Ждем, пока завершит работу поток прорисовки
				boolean done = false;
				while (!done) {
					try {
						drawThread.join();
						done = true;
					} catch (InterruptedException ignore) {
					}
				}
			}
		};
	}

	private static final class MyThread extends DrawThread {

		private Paint paint;
		private RectF rectf;
		private float[] points;
		private float[] points1;

		public MyThread(SurfaceHolder surfaceHolder) {
			super(surfaceHolder);
			this.paint = new Paint();
			this.rectf = new RectF(700, 100, 800, 150);
			this.points = new float[] { 100, 50, 150, 100, 150, 200, 50, 200,
					50, 100 };
			this.points1 = new float[] { 300, 200, 600, 200, 300, 300, 600,
					300, 400, 100, 400, 400, 500, 100, 500, 400 };
		}

		public void doDraw(Canvas canvas) {
			// заливка канвы цветом
			canvas.drawARGB(80, 177, 99, 163);

			// настройка цвета кисти
			paint.setARGB(80, 1, 152, 117);

			// толщина линии
			paint.setStrokeWidth(10);

			// рисуем точки их массива points
			canvas.drawPoints(points, paint);

			// рисуем линии по точкам из массива points1
			canvas.drawLines(points1, paint);

			// перенастраиваем кисть на зеленый цвет
			paint.setColor(Color.GREEN);

			// задаем координаты прямоугольника, левая верхняя точка (700, 100),
			// нижняя правая (800, 150)
			rectf.set(700, 100, 800, 150);

			// рисуем закругленный прямоугольник по координатам из rectf радиусы
			// закругления = 20
			canvas.drawRoundRect(rectf, 20, 20, paint);

			// смещаем коорднаты rectf на 150 вниз
			rectf.offset(0, 150);

			// рисуем овал внутри прямоугольника rectf
			canvas.drawOval(rectf, paint);

			// смещаем rectf в (900,100) (левая верхняя точка)
			rectf.offsetTo(900, 100);

			// увеличиваем rectf по вертикали на 25 вниз и вверх
			rectf.inset(0, -25);

			// рисуем дугу внутри прямоугольника rectf с началом в 90, и длиной
			// 270 соединение крайних точек через центр
			canvas.drawArc(rectf, 90, 270, true, paint);

			// смещаем коорднаты rectf на 150 вниз
			rectf.offset(0, 150);

			// рисуем дугу внутри прямоугольника rectf с началом в 90, и длиной
			// 270 соединение крайних точек напрямую
			canvas.drawArc(rectf, 90, 270, false, paint);

			// перенастраиваем кисть на толщину линии = 3
			paint.setStrokeWidth(3);

			// рисуем линию (150, 450) - (150, 600)
			canvas.drawLine(150, 450, 150, 600, paint);

			// перенастраиваем кисть на синий цвет
			paint.setColor(Color.BLUE);

			// настраиваем размер текста = 30
			paint.setTextSize(30);

			// настраиваем выравнивание текста на правое
			paint.setTextAlign(Paint.Align.RIGHT);

			// рисуем текст в точке (150, 500)
			canvas.drawText("text left", 150, 500, paint);

			// настраиваем выравнивание текста на центр
			paint.setTextAlign(Paint.Align.CENTER);

			// рисуем текст в точке (150, 525)
			canvas.drawText("text center", 150, 525, paint);

			// настраиваем выравнивание текста на левое
			paint.setTextAlign(Paint.Align.LEFT);

			// рисуем текст в точке (150, 550)
			canvas.drawText("text right", 150, 550, paint);
		}

	}
}
