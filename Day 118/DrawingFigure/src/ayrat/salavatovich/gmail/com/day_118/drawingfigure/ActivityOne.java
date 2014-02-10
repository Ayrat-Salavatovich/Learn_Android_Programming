package ayrat.salavatovich.gmail.com.day_118.drawingfigure;

import android.os.Bundle;
import android.app.Activity;

public class ActivityOne extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new DrawViewOne(this));
	}

}
