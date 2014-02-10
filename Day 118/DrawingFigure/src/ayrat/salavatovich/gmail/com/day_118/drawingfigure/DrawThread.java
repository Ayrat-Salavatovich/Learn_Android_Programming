package ayrat.salavatovich.gmail.com.day_118.drawingfigure;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

abstract public class DrawThread extends Thread implements Draw {

	private boolean running;
	private final SurfaceHolder surfaceHolder;

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
}