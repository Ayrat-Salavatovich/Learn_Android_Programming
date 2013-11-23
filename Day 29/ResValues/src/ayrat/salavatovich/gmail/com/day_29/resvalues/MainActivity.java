package ayrat.salavatovich.gmail.com.day_29.resvalues;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	private LinearLayout linearLayoutTop, linearLayoutBottom;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		linearLayoutTop = (LinearLayout) findViewById(R.id.linearLayoutTop);
		linearLayoutBottom = (LinearLayout) findViewById(R.id.linearLayoutBottom);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void changeColor(View v) {
		switch (v.getId()) {
		case R.id.buttonTop:
			changeColorFor(linearLayoutTop);

			break;
		case R.id.buttonBottom:
			changeColorFor(linearLayoutBottom);

			break;

		default:
			break;
		}
	}

	protected void changeColorFor(View v) {
		if (v == null)
			return;
		if (v instanceof LinearLayout) {
			if (((ColorDrawable) ((LinearLayout) v).getBackground()).getColor() == Color.BLUE)
				((LinearLayout) v).setBackgroundResource(R.color.green);
			else
				((LinearLayout) v).setBackgroundResource(R.color.blue);

			for (int i = 0; i < ((ViewGroup) v).getChildCount(); i++)
				changeColorFor(((ViewGroup) v).getChildAt(i));
		} else if (v instanceof Button) {
			if (((Button) v).getText().toString()
					.equals(getResources().getText(R.string.button_top_text)))
				((Button) v).setText(R.string.button_bottom_text);
			else
				((Button) v).setText(R.string.button_top_text);
		} else if (v instanceof TextView) {
			if (((TextView) v).getText().toString()
					.equals(getResources().getText(R.string.textview_top_text)))
				((TextView) v).setText(R.string.textview_bottom_text);
			else
				((TextView) v).setText(R.string.textview_top_text);
		}
	}
}
