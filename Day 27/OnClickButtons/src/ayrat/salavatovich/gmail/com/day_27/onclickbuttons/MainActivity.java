package ayrat.salavatovich.gmail.com.day_27.onclickbuttons;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView textViewOut;
	private Button buttonCancel, buttonOk;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textViewOut = (TextView) findViewById(R.id.textViewOut);

		outPrint("");

		buttonCancel = (Button) findViewById(R.id.buttonCancel);
		buttonOk = (Button) findViewById(R.id.buttonOk);

		OnClickListener onClickListenerButtonCancel = new OnClickListener() {

			@Override
			public void onClick(View v) {
				outPrint(R.string.on_click_button_cancel);
			}
		};

		buttonCancel.setOnClickListener(onClickListenerButtonCancel);

		OnClickListener onClickListenerButtonOk = new OnClickListener() {

			@Override
			public void onClick(View v) {
				outPrint(R.string.on_click_button_ok);
			}
		};

		buttonOk.setOnClickListener(onClickListenerButtonOk);
	}

	private void outPrint(final CharSequence text) {
		textViewOut.setText(text);
	}

	private void outPrint(final int resid) {
		textViewOut.setText(resid);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
