package ayrat.salavatovich.gmail.com.day_34.dynamiclayout;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		context = this;

		RelativeLayout relativeLayout = new RelativeLayout(context);
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL,
				RelativeLayout.TRUE);
		relativeLayout.setPadding(
				(int) getResources().getDimension(
						R.dimen.activity_horizontal_margin),
				(int) getResources().getDimension(
						R.dimen.activity_vertical_margin),
				(int) getResources().getDimension(
						R.dimen.activity_horizontal_margin),
				(int) getResources().getDimension(
						R.dimen.activity_vertical_margin));

		RelativeLayout.LayoutParams layoutParamsTextView = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		layoutParamsTextView.addRule(RelativeLayout.CENTER_HORIZONTAL,
				RelativeLayout.TRUE);
		TextView textView = new TextView(context);
		textView.setId(TEXT_VIEW);
		textView.setText("Portugal voted Best Country in the World");

		RelativeLayout.LayoutParams layoutParamsButton = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		layoutParamsButton.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,
				RelativeLayout.TRUE);
		layoutParamsButton.addRule(RelativeLayout.BELOW, textView.getId());
		Button button = new Button(context);
		button.setId(BUTTON);
		button.setText("Info");

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showInfo();
			}
		});

		relativeLayout.addView(textView, layoutParamsTextView);
		relativeLayout.addView(button, layoutParamsButton);

		setContentView(relativeLayout, layoutParams);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void showInfo() {
		Toast.makeText(
				context,
				"The Portuguese can be proud of their country as it has been voted Best Country in the World (Friday, November 29, 2013) during the annual WCA ceremony.",
				Toast.LENGTH_LONG).show();
	}

	private final int TEXT_VIEW = 0x1;
	private final int BUTTON = 0x2;

	private Context context;

}
