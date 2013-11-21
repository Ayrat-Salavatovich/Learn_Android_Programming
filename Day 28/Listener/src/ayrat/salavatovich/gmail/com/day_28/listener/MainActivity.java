package ayrat.salavatovich.gmail.com.day_28.listener;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	private Button buttonCancel, buttonOk;
	private TextView textViewOut;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textViewOut = (TextView) findViewById(R.id.textViewOut);
		buttonCancel = (Button) findViewById(R.id.buttonCancel);
		buttonOk = (Button) findViewById(R.id.buttonOk);

		print("");

		buttonCancel.setOnClickListener(this);
		buttonOk.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClickStart(View v) {
		onClick(v);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonCancel:
			print(R.string.out_click_button_cancel);

			break;
		case R.id.buttonOk:
			print(R.string.out_click_button_ok);

			break;
		case R.id.buttonStart:
			print(R.string.out_click_button_start);

			break;

		default:
			break;
		}
	}

	private void print(final CharSequence text) {
		if (textViewOut != null && text != null && text.length() > 0)
			textViewOut.setText(text);
	}

	private void print(final int resid) {
		try {
			if (textViewOut != null && resid > 0)
				textViewOut.setText(resid);
		} catch (Resources.NotFoundException ignore) {
		}
	}

}
