package ayrat.salavatovich.gmail.com.day_103.handlermessagemanage;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	private final StringBuilder stringBuilder = new StringBuilder();
	private final Handler.Callback callback = new Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			stringBuilder.append(getString(R.string.message, msg.what)).append(
					System.lineSeparator());
			outputMessages(stringBuilder.toString());
			return false;
		}
	};
	private final Handler handler = new Handler(callback);
	private final int MIN_COUNT_MESSAGES = 10;
	private final int SECOND = 1000;
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textView = (TextView) findViewById(R.id.textView);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button:
			stringBuilder.setLength(0);
			sendMessages();

			break;

		default:
			break;
		}
	}

	private void sendMessages() {
		stringBuilder.append(getString(R.string.send_messages)).append(
				System.lineSeparator());
		Random random = new Random();

		for (int i = 1; i <= MIN_COUNT_MESSAGES; i++) {
			sendMessage(i, i);
			sendMessage(random.nextInt(i) + 1, random.nextInt(i) + 1);
		}

		for (int i = 1; i <= random.nextInt(MIN_COUNT_MESSAGES) + 1; i++)
			removeMessage(random.nextInt(MIN_COUNT_MESSAGES) + 1);
	}

	private void sendMessage(int what, int delaySeconds) {
		handler.sendEmptyMessageDelayed(what, delaySeconds * SECOND);
	}

	private void removeMessage(int what) {
		stringBuilder.append(getString(R.string.remove_message, what)).append(
				System.lineSeparator());
		handler.removeMessages(what);
	}

	private void outputMessages(String messages) {
		textView.setText(messages);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
