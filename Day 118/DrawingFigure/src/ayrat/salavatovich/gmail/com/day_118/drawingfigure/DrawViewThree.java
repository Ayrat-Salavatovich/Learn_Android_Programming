package ayrat.salavatovich.gmail.com.day_118.drawingfigure;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;

public class DrawViewThree extends DrawSurfaceView {

	public DrawViewThree(Context context) {
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
		private StringBuilder stringBuilder;

		public MyThread(SurfaceHolder surfaceHolder) {
			super(surfaceHolder);
			this.paint = new Paint();
			this.rect = new Rect(100, 200, 200, 300);
			this.stringBuilder = new StringBuilder();
		}

		public void doDraw(Canvas canvas) {
			// заливка канвы цветом
			canvas.drawARGB(80, 177, 99, 163);

			// настройка цвета кисти
			paint.setARGB(80, 1, 152, 117);

			// толщина линии
			paint.setStrokeWidth(1);

			// настраиваем размер текста = 30
			paint.setTextSize(30);

			// создаем строку с значениями ширины и высоты канвы
			stringBuilder.setLength(0);
			stringBuilder.append("width = ").append(canvas.getWidth())
					.append(", height = ").append(canvas.getHeight());

			// рисуем текст в точке (100, 100)
			canvas.drawText(stringBuilder.toString(), 100, 100, paint);

			// задаем координаты прямоугольника, левая верхняя точка (200, 150),
			// нижняя правая (400, 200)
			rect.set(100, 200, 200, 300);

			// толщина линии
			paint.setStrokeWidth(10);

			// перенастраивам кисть на заливку
			paint.setStyle(Paint.Style.FILL);

			// рисуем прямоугольник левая верхняя точка (100, 200), нижняя
			// правая (200, 300)
			canvas.drawRect(rect, paint);

			// перенастраивам кисть на контуры
			paint.setStyle(Paint.Style.STROKE);

			// смещаем коорднаты rect на 150 вправо
			rect.offset(150, 0);

			// рисуем прямоугольник левая верхняя точка (250, 200), нижняя
			// правая (350, 300)
			canvas.drawRect(rect, paint);

			// перенастраивам кисть на заливку + контуры
			paint.setStyle(Paint.Style.FILL_AND_STROKE);

			// смещаем коорднаты rect на 150 вправо
			rect.offset(150, 0);

			// рисуем прямоугольник левая верхняя точка (400, 200), нижняя
			// правая (500, 300)
			canvas.drawRect(rect, paint);
		}

	}
}
