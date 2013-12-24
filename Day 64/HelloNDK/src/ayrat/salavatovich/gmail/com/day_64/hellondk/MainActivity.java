package ayrat.salavatovich.gmail.com.day_64.hellondk;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		TextView textViewHelloNDK = (TextView) findViewById(R.id.textViewHelloNDK);
		textViewHelloNDK.setText(naGetHelloNDKStr());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public native String naGetHelloNDKStr();
	static {
		System.loadLibrary("hello");
	}

}
