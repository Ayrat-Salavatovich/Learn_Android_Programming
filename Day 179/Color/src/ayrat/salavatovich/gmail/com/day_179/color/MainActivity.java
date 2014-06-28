package ayrat.salavatovich.gmail.com.day_179.color;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewParent;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView textViewColorText;
	private TextView textViewColorBackground;
	private TextView title;
	private int colorText;
	private int colorBackground;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		init();
		repaintTitle();
	}

	private void init() {
		setContentView(R.layout.main);
		title = (TextView) getWindow().findViewById(android.R.id.title);
		textViewColorText = (TextView) findViewById(R.id.textViewColorText);
		textViewColorBackground = (TextView) findViewById(R.id.textViewColorBackground);
		colorBackground = getResources().getColor(R.color.green);
		colorText = getResources().getColor(R.color.black);
	}

	public void onClick(View v) {
		OnColorChangedListener listener = null;
		int color = 0;

		switch (v.getId()) {
		case R.id.buttonColorText:
			listener = new OnColorChangedListener() {

				@Override
				public void colorChanged(int color) {
					colorText = color;
					repaintTitle();
				}

			};
			color = colorText;

			break;

		case R.id.buttonColorBackground:
			listener = new OnColorChangedListener() {

				@Override
				public void colorChanged(int color) {
					colorBackground = color;
					repaintTitle();
				}

			};
			color = colorBackground;

			break;

		default:
			break;
		}
		if (listener != null)
			new ColorPickerDialog(this, listener, color).show();
	}

	private void repaintTitle() {
		textViewColorText.setBackgroundColor(colorText);
		textViewColorBackground.setBackgroundColor(colorBackground);
		if (title != null) {
			title.setTextColor(colorText);

			ViewParent parent = title.getParent();
			if (parent != null && (parent instanceof View)) {
				View parentView = (View) parent;
				parentView.setBackgroundColor(colorBackground);
			}
		} else {
			int titleId = getResources().getIdentifier("action_bar_title",
					"id", "android");
			TextView abTitle = (TextView) findViewById(titleId);
			abTitle.setTextColor(colorText);
			getActionBar().setBackgroundDrawable(
					new ColorDrawable(colorBackground));
		}
	}

}
