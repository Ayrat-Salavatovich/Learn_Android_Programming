package ayrat.salavatovich.gmail.com.day_3.trafficlight;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	private TextView textViewInfo;
	private RelativeLayout relativeLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
		textViewInfo = (TextView) findViewById(R.id.textViewInfo);

		((Button) findViewById(R.id.buttonRed)).setOnClickListener(this);
		((Button) findViewById(R.id.buttonYellow)).setOnClickListener(this);
		((Button) findViewById(R.id.buttonGreen)).setOnClickListener(this);

		setColorTrafficLight(R.color.white);

	}

	private void setColorTrafficLight(final int color) {
		int resColor = color;
		int resInfo = R.string.white;
		switch (color) {
		case R.color.red:
			resInfo = R.string.red;

			break;
		case R.color.yellow:
			resInfo = R.string.yellow;

			break;
		case R.color.green:
			resInfo = R.string.green;

			break;

		default:
			break;
		}

		setInfo(resInfo);
		setColor(resColor);
	}

	private void setInfo(final int resid) {
		textViewInfo.setText(resid);
	}

	private void setColor(final int color) {
		relativeLayout.setBackgroundResource(color);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void buttonWhite_onClick(View v) {
		setColorTrafficLight(R.color.white);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonRed:
			setColorTrafficLight(R.color.red);

			break;
		case R.id.buttonYellow:
			setColorTrafficLight(R.color.yellow);

			break;
		case R.id.buttonGreen:
			setColorTrafficLight(R.color.green);

			break;

		default:
			break;
		}
	}

}
