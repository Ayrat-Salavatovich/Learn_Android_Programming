package ayrat.salavatovich.gmail.com.day_117.canvassurfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DrawSurfaceView extends SurfaceView {

	private DrawThread drawThread;

	public DrawSurfaceView(Context context) {
		super(context);
		// Указываем, что сами будем обрабатывать события SurfaceHolder
		getHolder().addCallback(callback);
	}

	private SurfaceHolder.Callback callback = new SurfaceHolder.Callback() {

		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {
			// Был изменен формат или размер SurfaceView
		}

		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			// SurfaceView создан и готов к отображению информации
			drawThread = new DrawThread(getHolder());
			drawThread.setRunning(true);
			drawThread.start();
		}

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			// Вызывается перед тем, как SurfaceView будет уничтожен
			drawThread.setRunning(false);

			// Ждем, пока завершит работу поток прорисовки
			boolean retry = true;
			while (retry) {
				try {
					drawThread.join();
					retry = false;
				} catch (InterruptedException ignore) {
				}
			}
		}
	};

	private static final class DrawThread extends Thread {

		private boolean running;
		private SurfaceHolder surfaceHolder;

		public DrawThread(SurfaceHolder surfaceHolder) {
			this.surfaceHolder = surfaceHolder;
		}

		public void setRunning(boolean running) {
			this.running = running;
		}

		@Override
		public void run() {
			Canvas canvas = null;

			while (running) {
				canvas = null;
				try {
					canvas = surfaceHolder.lockCanvas(null);

					synchronized (surfaceHolder) {
						if (canvas != null)
							doDraw(canvas);
					}
				} finally {
					if (canvas != null)
						surfaceHolder.unlockCanvasAndPost(canvas);
				}
			}
		}

		private void doDraw(Canvas canvas) {
			canvas.drawColor(Color.GREEN);
		}

	}

}
