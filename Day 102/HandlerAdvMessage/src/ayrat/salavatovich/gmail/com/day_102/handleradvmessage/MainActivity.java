package ayrat.salavatovich.gmail.com.day_102.handleradvmessage;

import java.util.Random;

import android.app.Activity;
import android.content.Context;
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
	final int STATUS_DOWNLOAD_START = 5;
	final int STATUS_DOWNLOAD_FILE = 6;
	final int STATUS_DOWNLOAD_END = 7;
	final int STATUS_DOWNLOAD_NONE = 8;

	private Context context;
	private Handler handler;
	private Thread thread;
	private TextView textView;
	private Button buttonConnect, buttonStopConnecting;
	private ProgressBar progressBarConnecting, progressBarDownload;

	private final int SECOND = 1000;
	private final int MAX_COUNT_DOWNLOAD_FILES = 10;
	private final int MEGABYTE = 1024;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		context = this;
		textView = (TextView) findViewById(R.id.textView);
		buttonConnect = (Button) findViewById(R.id.buttonConnect);
		buttonStopConnecting = (Button) findViewById(R.id.buttonStopConnecting);
		progressBarConnecting = (ProgressBar) findViewById(R.id.progressBarConnecting);
		progressBarDownload = (ProgressBar) findViewById(R.id.progressBarDownload);

		handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case STATUS_NOT_CONNECTED:
					setEnableButtonConnect(true);
					setEnableButtonStopConnecting(false);
					setShowConnectingProcess(false);
					setShowDownloadProgress(false);
					outputStatus(R.string.status_not_connected);

					break;

				case STATUS_CONNECTING:
					setEnableButtonConnect(false);
					setEnableButtonStopConnecting(true);
					setShowConnectingProcess(true);
					setShowDownloadProgress(false);
					outputStatus(R.string.status_connecting);

					break;

				case STATUS_OK:
					setEnableButtonConnect(false);
					setEnableButtonStopConnecting(true);
					setShowConnectingProcess(false);
					setShowDownloadProgress(false);
					outputStatus(R.string.status_ok);

					break;

				case STATUS_STOPPED:
					setEnableButtonConnect(true);
					setEnableButtonStopConnecting(false);
					setShowConnectingProcess(false);
					setShowDownloadProgress(false);
					outputStatus(R.string.status_stopped);

					break;

				case STATUS_FAILED:
					setEnableButtonConnect(true);
					setEnableButtonStopConnecting(false);
					setShowConnectingProcess(false);
					setShowDownloadProgress(false);
					outputStatus(R.string.status_failed);

					break;

				case STATUS_DOWNLOAD_START:
					outputStatus(R.string.status_download_start, msg.arg1);
					setEnableButtonConnect(false);
					setEnableButtonStopConnecting(true);
					setShowConnectingProcess(false);
					changeDownloadProgress(msg.arg1, msg.arg2);
					setShowDownloadProgress(true);

					break;

				case STATUS_DOWNLOAD_FILE:
					outputStatus(R.string.status_download_file, msg.arg2);
					setEnableButtonConnect(false);
					setEnableButtonStopConnecting(true);
					setShowConnectingProcess(false);
					changeDownloadProgress(msg.arg1);
					saveFile((byte[]) msg.obj);
					setShowDownloadProgress(true);

					break;

				case STATUS_DOWNLOAD_END:
					outputStatus(R.string.status_download_end);
					setEnableButtonConnect(false);
					setEnableButtonStopConnecting(true);
					setShowConnectingProcess(false);
					setShowDownloadProgress(false);

					break;

				case STATUS_DOWNLOAD_NONE:
					outputStatus(R.string.status_download_none);
					setEnableButtonConnect(false);
					setEnableButtonStopConnecting(true);
					setShowConnectingProcess(false);
					setShowDownloadProgress(false);

					break;

				default:
					break;
				}
			}
		};

		setStatusNotConnected();
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

	private void setStatusDownloadStart(final int countFiles) {
		Message message = handler.obtainMessage(STATUS_DOWNLOAD_START,
				countFiles, 0);
		handler.sendMessage(message);
	}

	private void setStatusDownloadFile(final int numberFile,
			final int leftCountFiles, byte[] file) {
		Message message = handler.obtainMessage(STATUS_DOWNLOAD_FILE,
				numberFile, leftCountFiles, file);
		handler.sendMessage(message);
	}

	private void setStatusDownloadEnd() {
		handler.sendEmptyMessage(STATUS_DOWNLOAD_END);
		SystemClock.sleep(SECOND);
	}

	private void setStatusDownloadNone() {
		handler.sendEmptyMessage(STATUS_DOWNLOAD_NONE);
		SystemClock.sleep(SECOND);
	}

	private void setEnableButtonConnect(boolean enabled) {
		buttonConnect.setEnabled(enabled);
	}

	private void setEnableButtonStopConnecting(boolean enabled) {
		buttonStopConnecting.setEnabled(enabled);
	}

	private void changeDownloadProgress(final int max, int progress) {
		progressBarDownload.setMax(max);
		progressBarDownload.setProgress(progress);
	}

	private void changeDownloadProgress(int progress) {
		progressBarDownload.setProgress(progress);
	}

	private void setShowDownloadProgress(boolean show) {
		if (show)
			progressBarDownload.setVisibility(View.VISIBLE);
		else
			progressBarDownload.setVisibility(View.GONE);
	}

	private void setShowConnectingProcess(boolean show) {
		if (show)
			progressBarConnecting.setVisibility(View.VISIBLE);
		else
			progressBarConnecting.setVisibility(View.GONE);
	}

	private void outputStatus(int resId, Object... formatArgs) {
		textView.setText(getString(resId, formatArgs));
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
					startDownload();
					setStatusStopped();
				}
			}
		});

		thread.start();
	}

	private void startDownload() {
		final Random random = new Random();
		final int countFiles = random.nextInt(MAX_COUNT_DOWNLOAD_FILES);
		if (countFiles == 0) {
			setStatusDownloadNone();
			return;
		}

		setStatusDownloadStart(countFiles);

		byte[] file = null;
		for (int i = 1; i <= countFiles; i++) {
			file = fetchFile();
			setStatusDownloadFile(i, (countFiles - i), file);
		}

		setStatusDownloadEnd();
	}

	byte[] fetchFile() {
		final Random random = new Random();
		SystemClock.sleep(SECOND * 2);
		return new byte[random.nextInt(MEGABYTE)];
	}

	private void saveFile(byte[] file) {
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
