package ayrat.salavatovich.gmail.com.day_70.togglebuttonsample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textView = (TextView) findViewById(R.id.textView);
		ToggleButton toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
		toggleButton.setOnCheckedChangeListener(toggleButton_onCheckedChange);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private OnCheckedChangeListener toggleButton_onCheckedChange = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			textView.setText(isChecked ? R.string.toggle_button_checked
					: R.string.toggle_button_unchecked);
		}
	};

}
