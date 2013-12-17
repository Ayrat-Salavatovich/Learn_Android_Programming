package ayrat.salavatovich.gmail.com.day_55.layoutinflater;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	private View textViewLayoutForLinearLayout,
			textViewLayoutForRelativeLayout;
	private LinearLayout linearLayout;
	private RelativeLayout relativeLayout;
	private SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
		relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
	}

	public void showView(boolean root, boolean attachToRoot) {
		LayoutInflater layoutInflater = getLayoutInflater();
		ViewGroup rootViewGroup = null;

		if (root)
			rootViewGroup = (LinearLayout) findViewById(R.id.linearLayout);
		textViewLayoutForLinearLayout = layoutInflater.inflate(R.layout.text,
				rootViewGroup, attachToRoot);
		if (!attachToRoot)
			linearLayout.addView(textViewLayoutForLinearLayout);

		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		layoutParams.addRule(RelativeLayout.RIGHT_OF,
				R.id.textViewRelativeLayout);
		if (root)
			rootViewGroup = (RelativeLayout) findViewById(R.id.relativeLayout);
		textViewLayoutForRelativeLayout = layoutInflater.inflate(R.layout.text,
				rootViewGroup, attachToRoot);
		if (!attachToRoot)
			relativeLayout.addView(textViewLayoutForRelativeLayout,
					layoutParams);
	}

	private String out() {
		final String lineSeparator = "\n";
		LayoutParams layoutParams = null;
		StringBuilder sb = new StringBuilder();
		
		// textViewLayoutForLinearLayout
		sb.append(
				"Class of textViewLayoutForLinearLayout: "
						+ textViewLayoutForLinearLayout.getClass().toString())
				.append(lineSeparator);
		layoutParams = textViewLayoutForLinearLayout.getLayoutParams();
		if (layoutParams == null)
			sb.append("LayoutParams of textViewLayoutForLinearLayout is null")
					.append(lineSeparator);
		else
			sb.append(
					"Class of layoutParams of textViewLayoutForLinearLayout: "
							+ layoutParams.getClass().toString()).append(
					lineSeparator);
		if (textViewLayoutForLinearLayout.getClass() == TextView.class)
			sb.append(
					"Text of textViewLayoutForLinearLayout: "
							+ ((TextView) textViewLayoutForLinearLayout)
									.getText()).append(lineSeparator);

		// textViewLayoutForRelativeLayout
		sb.append(
				"Class of textViewLayoutForRelativeLayout: "
						+ textViewLayoutForRelativeLayout.getClass().toString())
				.append(lineSeparator);
		layoutParams = textViewLayoutForRelativeLayout.getLayoutParams();
		if (layoutParams == null)
			sb.append("LayoutParams of textViewLayoutForRelativeLayout is null")
					.append(lineSeparator);
		else
			sb.append(
					"Class of layoutParams of textViewLayoutForRelativeLayout: "
							+ layoutParams.getClass().toString()).append(
					lineSeparator);
		if (textViewLayoutForRelativeLayout.getClass() == TextView.class)
			sb.append(
					"Text of textViewLayoutForRelativeLayout: "
							+ ((TextView) textViewLayoutForRelativeLayout)
									.getText()).append(lineSeparator);
		return sb.toString();
	}

	private boolean isRoot() {
		sharedPreferences = getSharedPreferences(MenuActivity.class.getSimpleName(), MODE_PRIVATE);

		return sharedPreferences.getBoolean(MenuActivity.ROOT, false);
	}

	private boolean isAttachToRoot() {
		sharedPreferences = getSharedPreferences(MenuActivity.class.getSimpleName(), MODE_PRIVATE);

		return sharedPreferences.getBoolean(MenuActivity.ATTACH_TO_ROOT, false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = null;

		switch (item.getItemId()) {
		case R.id.action_settings:
			intent = new Intent(this, MenuActivity.class);
			startActivity(intent);

			return false;
		case R.id.action_show:
			showView(isRoot(), isAttachToRoot());
			out();

			return false;
		case R.id.action_log:
			intent = new Intent(this, OutputActivity.class);
			intent.putExtra(OutputActivity.OUT, out());
			startActivity(intent);

			return false;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
