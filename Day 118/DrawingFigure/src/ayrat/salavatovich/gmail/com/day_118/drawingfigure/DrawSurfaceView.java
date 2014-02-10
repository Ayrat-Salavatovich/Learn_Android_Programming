package ayrat.salavatovich.gmail.com.day_118.drawingfigure;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

abstract public class DrawSurfaceView extends SurfaceView {

	protected DrawThread drawThread;

	public DrawSurfaceView(Context context) {
		super(context);
		// Указываем, что сами будем обрабатывать события SurfaceHolder
		getHolder().addCallback(getCallback());
	}

	protected abstract SurfaceHolder.Callback getCallback();
}
