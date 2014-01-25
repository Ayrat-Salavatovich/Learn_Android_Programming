package ayrat.salavatovich.gmail.com.day_101.handlersimplemessage;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

	final int STATUS_NOT_CONNECTED = 0;
	final int STATUS_CONNECTING = 1;
	final int STATUS_OK = 2;
	final int STATUS_STOPPED = 3;
	final int STATUS_FAILED = 4;

	private Handler handler;
	private TextView textView;
	private Button buttonConnect, buttonStopConnecting;
	private ProgressBar progressBar;
	private Thread thread;
	private final int SECOND = 1000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textView = (TextView) findViewById(R.id.textView);
		buttonConnect = (Button) findViewById(R.id.buttonConnect);
		buttonStopConnecting = (Button) findViewById(R.id.buttonStopConnecting);
		progressBar = (ProgressBar) findViewById(R.id.progressBar);

		handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case STATUS_NOT_CONNECTED:
					setEnableButtonConnect(true);
					setEnableButtonStopConnecting(false);
					setShowConnectingProcess(false);
					outputStatus(R.string.status_not_connected);

					break;

				case STATUS_CONNECTING:
					setEnableButtonConnect(false);
					setEnableButtonStopConnecting(true);
					setShowConnectingProcess(true);
					outputStatus(R.string.status_connecting);

					break;

				case STATUS_OK:
					setEnableButtonConnect(false);
					setEnableButtonStopConnecting(true);
					setShowConnectingProcess(false);
					outputStatus(R.string.status_ok);

					break;

				case STATUS_STOPPED:
					setEnableButtonConnect(true);
					setEnableButtonStopConnecting(false);
					setShowConnectingProcess(false);
					outputStatus(R.string.status_stopped);

					break;

				case STATUS_FAILED:
					setEnableButtonConnect(true);
					setEnableButtonStopConnecting(false);
					setShowConnectingProcess(false);
					outputStatus(R.string.status_failed);

					break;

				default:
					break;
				}
			}
		};

		setStatusNotConnected();
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonConnect:
			startConnecting();

			break;

		case R.id.buttonStopConnecting:
			setStatusStopped();

			break;

		default:
			break;
		}
	}

	private void startConnecting() {
		thread = new Thread(new Runnable() {

			@Override
			public void run() {
				setStatusConnecting();

				if (Math.random() > .8)
					setStatusFailed();
				else {
					setStatusOk();
					setStatusStopped();
				}
			}
		});

		thread.start();
	}

	private void setStatusConnecting() {
		handler.sendEmptyMessage(STATUS_CONNECTING);
		SystemClock.sleep(SECOND * 2);
	}

	private void setStatusNotConnected() {
		handler.sendEmptyMessage(STATUS_NOT_CONNECTED);
	}

	private void setStatusOk() {
		handler.sendEmptyMessage(STATUS_OK);
		SystemClock.sleep(SECOND * 3);
	}

	private void setStatusStopped() {
		handler.sendEmptyMessage(STATUS_STOPPED);
		if (thread != null)
			thread.interrupt();
	}

	private void setStatusFailed() {
		handler.sendEmptyMessage(STATUS_FAILED);
		if (thread != null)
			thread.interrupt();
	}

	private void outputStatus(int status) {
		textView.setText(status);
	}

	private void setShowConnectingProcess(boolean show) {
		if (show)
			progressBar.setVisibility(View.VISIBLE);
		else
			progressBar.setVisibility(View.GONE);
	}

	private void setEnableButtonConnect(boolean enabled) {
		buttonConnect.setEnabled(enabled);
	}

	private void setEnableButtonStopConnecting(boolean enabled) {
		buttonStopConnecting.setEnabled(enabled);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
