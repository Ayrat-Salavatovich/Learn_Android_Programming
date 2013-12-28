package ayrat.salavatovich.gmail.com.day_70.checkboxsample;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
		checkBox.setOnCheckedChangeListener(checkBox_onCheckedChange);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private OnCheckedChangeListener checkBox_onCheckedChange = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			if (isChecked)
				buttonView.setText(R.string.check_box_on);
			else
				buttonView.setText(R.string.check_box_off);
		}
	};

}
