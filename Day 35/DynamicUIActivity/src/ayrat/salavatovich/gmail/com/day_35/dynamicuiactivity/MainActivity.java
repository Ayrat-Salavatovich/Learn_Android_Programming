package ayrat.salavatovich.gmail.com.day_35.dynamicuiactivity;

import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	private final int LINEAR_LAYOUT_ID = 0;
	private final int BUTTON_FIRST_ID = 1;
	private final int BUTTON_SECOND_ID = 2;
	private final int TEXT_VIEW_ID = 3;
	private final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;
	private final int WRAP_CONTENT = LinearLayout.LayoutParams.WRAP_CONTENT;

	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		LinearLayout linearLayout = new LinearLayout(getApplicationContext());
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		linearLayout.setId(LINEAR_LAYOUT_ID);
		LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);

		Button buttonFirst = new Button(getApplicationContext());
		buttonFirst.setId(BUTTON_FIRST_ID);
		buttonFirst.setText(R.string.button_first);
		buttonFirst.setOnClickListener(button_onClick);
		Button buttonSecond = new Button(getApplicationContext());
		buttonSecond.setId(BUTTON_SECOND_ID);
		buttonSecond.setText(R.string.button_second);
		buttonSecond.setOnClickListener(button_onClick);
		LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
				MATCH_PARENT, WRAP_CONTENT);
		linearLayout.addView(buttonFirst, buttonParams);
		linearLayout.addView(buttonSecond, buttonParams);

		textView = new TextView(getApplicationContext());
		textView.setGravity(Gravity.CENTER);
		textView.setId(TEXT_VIEW_ID);
		LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(
				MATCH_PARENT, WRAP_CONTENT);
		linearLayout.addView(textView, textViewParams);

		addContentView(linearLayout, linearLayoutParams);
	}

	private void echo(Button btn) {
		textView.setText(getString(R.string.echo_click, btn.getText()));
	}

	private OnClickListener button_onClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case BUTTON_FIRST_ID:
			case BUTTON_SECOND_ID:
				echo((Button) v);

				break;

			default:
				break;
			}
		}
	};

}
