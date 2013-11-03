package ayrat.salavatovich.gmail.com.day_9.showtoastwithimage;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void showToast(View v) {
		Toast toast = Toast.makeText(getApplicationContext(), R.string.hi,
				Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER, 0, 0);
		LinearLayout toastLinearLayout = (LinearLayout) toast.getView();
		ImageView imageView = new ImageView(getApplicationContext());
		imageView.setImageResource(R.drawable.miss_portugal);
		toastLinearLayout.addView(imageView, 0);
		toast.show();
	}

}
