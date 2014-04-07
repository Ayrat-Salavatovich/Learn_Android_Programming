package ayrat.salavatovich.gmail.com.day_143.circletext;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonCircleText:
			startActivity(new Intent(this, CircleTextActivity.class));

			break;

		case R.id.buttonCurvedText:
			startActivity(new Intent(this, CurvedTextActivity.class));

			break;

		default:
			break;
		}
	}

}
