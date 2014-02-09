package ayrat.salavatovich.gmail.com.day_117.canvasview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;

public class DrawView extends View {

	public DrawView(Context context) {
		super(context);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.BLUE);
	}

}
