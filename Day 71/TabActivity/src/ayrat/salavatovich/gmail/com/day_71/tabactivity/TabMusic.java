package ayrat.salavatovich.gmail.com.day_71.tabactivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public class TabMusic extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		TextView textView = new TextView(getApplicationContext());
		textView.setText(R.string.content_music);
		textView.setGravity(Gravity.CENTER);
		setContentView(textView, new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
	}

}
