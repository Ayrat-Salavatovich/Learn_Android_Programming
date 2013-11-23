package ayrat.salavatovich.gmail.com.day_30.logandmess;

import android.app.Activity;
import android.content.res.Resources.NotFoundException;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private TextView textViewOut;
	private Priority priority = Priority.INFO;
	private RadioGroup radioGroupPriority, radioGroupDuration;
	private int duration = Toast.LENGTH_SHORT;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textViewOut = (TextView) findViewById(R.id.textViewOut);
		print("");

		radioGroupPriority = (RadioGroup) findViewById(R.id.radioGroupPriority);
		radioGroupDuration = (RadioGroup) findViewById(R.id.radioGroupDuration);

		setOnCheckedChangeListenerForRadioGroups();
	}

	private void setOnCheckedChangeListenerForRadioGroups() {
		radioGroupPriority
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						switch (checkedId) {
						case R.id.radioButtonPriorityDebug:
							priority = Priority.DEBUG;

							break;
						case R.id.radioButtonPriorityError:
							priority = Priority.ERROR;

							break;
						case R.id.radioButtonPriorityInfo:
							priority = Priority.INFO;

							break;
						case R.id.radioButtonPriorityVerbose:
							priority = Priority.VERBOSE;

							break;
						case R.id.radioButtonPriorityWarn:
							priority = Priority.WARN;

							break;
						default:
							break;
						}
					}
				});

		radioGroupDuration
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						switch (checkedId) {
						case R.id.radioButtonDurationShort:
							duration = Toast.LENGTH_SHORT;

							break;
						case R.id.radioButtonDurationLong:
							duration = Toast.LENGTH_LONG;

							break;
						default:
							break;
						}
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonCancel:
			notifyMessage(getString(R.string.print, getString(R.string.cancel)));

			break;
		case R.id.buttonOk:
			notifyMessage(getString(R.string.print, getString(R.string.ok)));

			break;
		default:
			break;
		}
	}

	private void notifyMessage(final int resId) {
		log(resId);
		print(resId);
		toast(resId);
	}

	private void notifyMessage(final String text) {
		log(text);
		print(text);
		toast(text);
	}

	private void toast(final int resId) {
		Toast.makeText(getApplicationContext(), resId, duration).show();
	}

	private void toast(final String text) {
		Toast.makeText(getApplicationContext(), text, duration).show();
	}

	private void log(final int id) {
		try {
			if (id > 0) {
				log(getResources().getString(id));
			}
		} catch (NotFoundException ignore) {
		}
	}

	private void log(final String message) {
		if (priority == Priority.DEBUG)
			Log.d(message);
		else if (priority == Priority.ERROR)
			Log.e(message);
		else if (priority == Priority.INFO)
			Log.i(message);
		else if (priority == Priority.VERBOSE)
			Log.v(message);
		else if (priority == Priority.WARN)
			Log.w(message);
	}

	private void print(final String text) {
		if (textViewOut != null && text != null && text.length() > 0)
			textViewOut.setText(text);
	}

	private void print(final int resId) {
		try {
			if (textViewOut != null && resId > 0)
				textViewOut.setText(resId);
		} catch (NotFoundException ignore) {
		}
	}

}
