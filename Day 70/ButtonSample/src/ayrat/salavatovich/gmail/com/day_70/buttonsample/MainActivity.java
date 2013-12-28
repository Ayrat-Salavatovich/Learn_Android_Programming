package ayrat.salavatovich.gmail.com.day_70.buttonsample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textView = (TextView) findViewById(R.id.textView);

		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				show((Button) v);
			}
		});

		Button button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(button2_onClick);

		Button button3 = (Button) findViewById(R.id.button3);
		button3.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button3:
			show((Button) v);
			break;

		default:
			break;
		}
	}

	public void button4_onClick(View v) {
		show((Button) v);
	}

	private void show(Button btn) {
		textView.setText(getString(R.string.show, btn.getText().toString()));
	}

	private OnClickListener button2_onClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			show((Button) v);
		}
	};

}
