package ayrat.salavatovich.gmail.com.day_118.drawingfigure;

import android.os.Bundle;
import android.app.Activity;

public class ActivityTwo extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new DrawViewTwo(this));
	}

}
