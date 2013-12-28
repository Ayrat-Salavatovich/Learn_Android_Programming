package ayrat.salavatovich.gmail.com.day_70.imagebuttonsample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity {

	private boolean status = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClick(View v) {
		status = !status;
		if (status)
			((ImageButton) v).setImageResource(R.drawable.button_up);
		else
			((ImageButton) v).setImageResource(R.drawable.button_down);
	}

}
