package ayrat.salavatovich.gmail.com.day_45.activityresult;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	public static String COLOR = "ayrat.salavatovich.gmail.com.day_45.activityresult.COLOR";
	public static String GRAVITY = "ayrat.salavatovich.gmail.com.day_45.activityresult.GRAVITY";
	private final int REQUEST_CODE_COLOR = 1;
	private final int REQUEST_CODE_GRAVITY = 2;
	private TextView textViewText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textViewText = (TextView) findViewById(R.id.textViewText);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void setColor(View v) {
		Intent intent = new Intent(this, ColorActivity.class);
		startActivityForResult(intent, REQUEST_CODE_COLOR);
	}

	public void setGravity(View v) {
		Intent intent = new Intent(this, GravityActivity.class);
		startActivityForResult(intent, REQUEST_CODE_GRAVITY);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != RESULT_OK)
			return;

		switch (requestCode) {
		case REQUEST_CODE_COLOR:
			int color = data.getIntExtra(COLOR, Color.BLACK);
			textViewText.setTextColor(color);

			break;
		case REQUEST_CODE_GRAVITY:
			int gravity = data.getIntExtra(GRAVITY, Gravity.LEFT);
			textViewText.setGravity(gravity);

			break;

		default:
			super.onActivityResult(requestCode, resultCode, data);

			break;
		}
	}

}
