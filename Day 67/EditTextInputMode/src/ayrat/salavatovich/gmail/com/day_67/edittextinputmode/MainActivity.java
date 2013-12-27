package ayrat.salavatovich.gmail.com.day_67.edittextinputmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void adjust(View v) {
		switch (v.getId()) {
		case R.id.buttonAdjustResize:
			startActivity(new Intent(this, AdjustResizeActivity.class));
			break;
		case R.id.buttonAdjustPan:
			startActivity(new Intent(this, AdjustPanActivity.class));
			break;
		case R.id.buttonAdjustUnspecified:
			startActivity(new Intent(this, AdjustUnspecifiedActivity.class));
			break;
		case R.id.buttonAdjustNothing:
			startActivity(new Intent(this, AdjustNothingActivity.class));
			break;
		default:
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
