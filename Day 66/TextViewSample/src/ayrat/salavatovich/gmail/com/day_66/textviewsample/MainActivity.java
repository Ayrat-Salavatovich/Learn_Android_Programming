package ayrat.salavatovich.gmail.com.day_66.textviewsample;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	final String portugal = "Portugal is the best country in the world!";
	char[] array = portugal.toCharArray();
	Timer myTimer = new Timer();
	final Handler myHandler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		TextView textView3, textView4;
		final TextView textView5 = (TextView) findViewById(R.id.textView5);
		textView3 = (TextView) findViewById(R.id.textView3);
		textView3.setText(portugal);
		textView4 = (TextView) findViewById(R.id.textView4);
		textView4.setText(R.string.portugal);

		myTimer.schedule(new TimerTask() {

			@Override
			public void run() {
				char[] temp = new char[array.length];
				System.arraycopy(array, 0, temp, 1, array.length - 1);
				temp[0] = array[array.length - 1];
				array = temp;
				myHandler.post(new Runnable() {

					@Override
					public void run() {
						if (String.valueOf(array).equals(portugal)) {
							textView5.setText(String.valueOf(array));
							textView5.setTextSize(16);
						} else {
							textView5.setText(String.valueOf(array));
							textView5.setTextSize(14);
						}
					}
				});
			}
		}, 0, 1000);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
