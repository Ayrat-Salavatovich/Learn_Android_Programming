package ayrat.salavatovich.gmail.com.day_118.drawingfigure;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;

public class DrawViewOne extends DrawSurfaceView {

	public DrawViewOne(Context context) {
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
		private Rect rect;

		public MyThread(SurfaceHolder surfaceHolder) {
			super(surfaceHolder);
			this.paint = new Paint();
			this.rect = new Rect();
		}

		public void doDraw(Canvas canvas) {
			// заливка канвы цветом
			canvas.drawARGB(80, 177, 99, 163);

			// настройка цвета кисти
			paint.setARGB(80, 1, 152, 117);

			// толщина линии
			paint.setStrokeWidth(10);

			// рисуем точку
			canvas.drawPoint(50, 50, paint);

			// рисуем линию от (100, 100) до (500, 50)
			canvas.drawLine(100, 100, 500, 50, paint);

			// рисуем круг с центром в (100, 200), радиус = 50
			canvas.drawCircle(100, 200, 50, paint);

			// рисуем прямоугольник левая верхняя точка (200, 150), нижняя
			// правая (400, 200)
			canvas.drawRect(200, 150, 400, 200, paint);

			// настройка объекта Rect левая верхняя точка (250, 300), нижняя
			// правая (350, 500)
			rect.set(250, 300, 350, 500);

			// рисуем прямоугольник из объекта rect
			canvas.drawRect(rect, paint);
		}

	}
}
